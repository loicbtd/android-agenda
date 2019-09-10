package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class DevoirDAO implements DevoirSQL {

    private static DevoirDAO instance = null;
    protected List<Devoir> listeDevoir;

    private BaseDeDonnees accesseurBaseDeDonnees;

    public static DevoirDAO getInstance() {
        if (null == instance) {
            instance = new DevoirDAO();
        }
        return instance;
    }

    public DevoirDAO() {
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeDevoir = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Devoir> listerDevoir() {
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_DEVOIRS, null);
        this.listeDevoir.clear();

        Devoir devoir;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int indexId_devoir = curseur.getColumnIndex("id_devoir");
        int indexMatiere = curseur.getColumnIndex("matiere");
        int indexSujet = curseur.getColumnIndex("sujet");
        int indexHoraire = curseur.getColumnIndex("horaire");

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_devoir = curseur.getInt(indexId_devoir);
            String matiere = curseur.getString(indexMatiere);
            String sujet = curseur.getString(indexSujet);
            LocalDateTime horaire = LocalDateTime.parse(curseur.getString(indexHoraire), formatter);
            devoir = new Devoir(id_devoir, matiere, sujet, horaire);
            this.listeDevoir.add(devoir);
        }
        return listeDevoir;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<HashMap<String, String>> recupererListeDevoirPourAdapteur() {
        List<HashMap<String, String>> listeDevoirPourAdapteur =
                new ArrayList<>();

        listerDevoir();

        for (Devoir devoir : listeDevoir) {
            listeDevoirPourAdapteur.add(devoir.obtenirDevoirPourAdapteur());
        }
        return listeDevoirPourAdapteur;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ajouterDevoir(Devoir devoir) {
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_DEVOIR);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());

        DateTimeFormatter formateur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaire = devoir.getHoraire().format(formateur);
        sqLiteStatement.bindString(3, horaire);

        sqLiteStatement.execute();
    }

    public Devoir chercherDevoirParIdDevoir(int id_devoir) {
        for(Devoir devoirRecherche : this.listeDevoir) {
            if(devoirRecherche.getId_devoir() == id_devoir) return devoirRecherche;
        }
        return null;
    }

    public void modifierDevoir(Devoir devoir) {
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_DEVOIR);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());
        sqLiteStatement.bindString(3, String.valueOf(devoir.getId_devoir()));
        sqLiteStatement.execute();
    }
}
