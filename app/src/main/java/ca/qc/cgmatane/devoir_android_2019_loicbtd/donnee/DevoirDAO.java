package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class DevoirDAO {

    private static DevoirDAO instance = null;
    protected List<Devoir> listeDevoir;

    public static DevoirDAO getInstance() {
        if (null == instance) {
            instance = new DevoirDAO();
        }
        return instance;
    }

    public DevoirDAO() {
        listeDevoir = new ArrayList<Devoir>();
        preparerListeDevoir();
    }

    public List<Devoir> recupererListeDevoir() {
        return listeDevoir;
    }

    public List<HashMap<String, String>> recupererListeDevoirPourAdapteur() {
        List<HashMap<String, String>> listeDevoirPourAdapteur =
                new ArrayList<HashMap<String, String>>();

        for (Devoir devoir : listeDevoir) {
            listeDevoirPourAdapteur.add(devoir.obtenirDevoirPourAdapteur());
        }
        return listeDevoirPourAdapteur;
    }

    public void ajouterDevoir(Devoir devoir) {
        listeDevoir.add(devoir);
    }

    private void preparerListeDevoir() {
        listeDevoir.add(new Devoir("Anglais", "Faire article de journal", 1));
        listeDevoir.add(new Devoir("Android", "Faire une release", 2));
        listeDevoir.add(new Devoir("Mondes virtuels", "Finir le blueprint", 3));
    }
}
