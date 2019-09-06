package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class DevoirDAO {

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

    public List<Devoir> listerDevoir() {
        String LISTER_DEVOIRS = "select * from devoir";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase()
                .rawQuery(LISTER_DEVOIRS, null);
        this.listeDevoir.clear();
        Devoir devoir;

        int indexId_devoir = curseur.getColumnIndex("id_devoir");
        int indexMatiere = curseur.getColumnIndex("matiere");
        int indexSujet = curseur.getColumnIndex("sujet");

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_devoir = curseur.getInt(indexId_devoir);
            String matiere = curseur.getString(indexMatiere);
            String sujet = curseur.getString(indexSujet);
            devoir = new Devoir(matiere, sujet, id_devoir);
            this.listeDevoir.add(devoir);
        }
        return listeDevoir;
    }

    public List<HashMap<String, String>> recupererListeDevoirPourAdapteur() {
        List<HashMap<String, String>> listeDevoirPourAdapteur =
                new ArrayList<>();

        listerDevoir();

        for (Devoir devoir : listeDevoir) {
            listeDevoirPourAdapteur.add(devoir.obtenirDevoirPourAdapteur());
        }
        return listeDevoirPourAdapteur;
    }

    public void ajouterDevoir(Devoir devoir) {
        String INSERT = "insert into devoir(matiere, sujet) VALUES(?,?)";
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(INSERT);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());
        sqLiteStatement.execute();
    }

    public Devoir chercherDevoirParIdDevoir(int id_devoir) {
        for(Devoir devoirRecherche : this.listeDevoir) {
            if(devoirRecherche.getId_devoir() == id_devoir) return devoirRecherche;
        }
        return null;
    }

    public void modifierDevoir(Devoir devoir) {
        String INSERT = "update devoir set matiere = ?, sujet = ? where id_devoir = ?";
        SQLiteDatabase sqLiteDatabase = accesseurBaseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(INSERT);
        sqLiteStatement.bindString(1, devoir.getMatiere());
        sqLiteStatement.bindString(2, devoir.getSujet());
        sqLiteStatement.bindString(3, String.valueOf(devoir.getId_devoir()));
        sqLiteStatement.execute();
    }
}
