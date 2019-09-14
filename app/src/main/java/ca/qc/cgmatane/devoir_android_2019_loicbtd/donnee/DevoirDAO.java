package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Devoir> recupererListeDevoir() {
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_DEVOIRS, null);
        this.listeDevoir.clear();

        Devoir devoir;

        int indexId_devoir = curseur.getColumnIndex(Devoir.CLE_ID_DEVOIR);
        int indexMatiere = curseur.getColumnIndex(Devoir.CLE_MATIERE);
        int indexSujet = curseur.getColumnIndex(Devoir.CLE_SUJET);
        int indexHoraire = curseur.getColumnIndex(Devoir.CLE_HORAIRE);
        int indexAlarmeActive = curseur.getColumnIndex(Devoir.CLE_ALARME_ACTIVE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_devoir = curseur.getInt(indexId_devoir);
            String matiere = curseur.getString(indexMatiere);
            String sujet = curseur.getString(indexSujet);
            LocalDateTime horaire = LocalDateTime.parse(
                    curseur.getString(indexHoraire),
                    Devoir.FORMAT_DATE_STOCKAGE
            );
            boolean alarme_active = curseur.getInt(indexAlarmeActive) != 0;
            devoir = new Devoir(id_devoir, matiere, sujet, horaire, alarme_active);
            this.listeDevoir.add(devoir);
        }
        return listeDevoir;
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public List<HashMap<String, String>> recupererListeDevoirPourAdapteur() {
//        List<HashMap<String, String>> listeDevoirPourAdapteur =
//                new ArrayList<>();
//
//        recupererListeDevoir();
//
//        for (Devoir devoir : listeDevoir) {
//            listeDevoirPourAdapteur.add(devoir.obtenirDevoirPourAdapteur());
//        }
//        return listeDevoirPourAdapteur;
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ajouterDevoir(Devoir devoir) {
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_DEVOIR);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());
        sqLiteStatement.bindString(3,
                devoir.getHoraire().format(Devoir.FORMAT_DATE_STOCKAGE));
        sqLiteStatement.bindString(4, ""+(devoir.isAlarme_active() ? 1 : 0));
        sqLiteStatement.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Devoir chercherDevoirParIdDevoir(int id_devoir) {
        for(Devoir devoirRecherche : this.listeDevoir) {
            if(devoirRecherche.getId_devoir() == id_devoir) return devoirRecherche;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void modifierDevoir(Devoir devoir) {
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_DEVOIR);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());
        sqLiteStatement.bindString(3,
                devoir.getHoraire().format(Devoir.FORMAT_DATE_STOCKAGE));
        sqLiteStatement.bindString(4, ""+(devoir.isAlarme_active() ? 1 : 0));
        sqLiteStatement.bindString(5, ""+devoir.getId_devoir());
        sqLiteStatement.execute();
    }
}
