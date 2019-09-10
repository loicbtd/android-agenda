package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

public interface BaseDeDonneesSQL {
    String SQL_CREER_TABLE_DEVOIR = "CREATE TABLE IF NOT EXISTS devoir(" +
            "id_devoir INTEGER PRIMARY KEY, " +
            "matiere TEXT, " +
            "sujet TEXT, " +
            "horaire TEXT" +
            ")";
    String SQL_DETRUIRE_TABLE_DEVOIR = "DROP TABLE IF EXISTS devoir";
    String SQL_DECRIRE_TABLE_DEVOIR = "PRAGMA table_info(devoir);";
}
