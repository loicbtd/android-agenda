package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

public interface DevoirSQL {
    String SQL_INSERER_DEVOIR = "INSERT INTO devoir(matiere, sujet) VALUES(?,?)";
    String SQL_MODIFIER_DEVOIR = "UPDATE devoir SET matiere = ?, sujet = ? where id_devoir = ?";
    String SQL_LISTER_DEVOIRS = "select * from devoir";
}
