package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {
    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte) {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance() {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "agenda", null, 1);
    }

    // TODO: faire une private static String pour les requetes CREATE_TABLE, etc
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table devoir(id_devoir INTEGER PRIMARY KEY, matiere TEXT, sujet TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        String DETRUIRE_TABLE = "drop table devoir";
//        db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table devoir(id_devoir INTEGER PRIMARY KEY, matiere TEXT, sujet TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
