package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper implements BaseDeDonneesSQL {

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DETRUIRE_TABLE_DEVOIR);
        db.execSQL(SQL_CREER_TABLE_DEVOIR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL(SQL_DETRUIRE_TABLE_DEVOIR);
        db.execSQL(SQL_CREER_TABLE_DEVOIR);
    }
}
