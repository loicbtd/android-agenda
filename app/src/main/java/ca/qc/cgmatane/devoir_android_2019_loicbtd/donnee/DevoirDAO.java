package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DevoirDAO {

    private static DevoirDAO instance = null;
    protected List<HashMap<String, String>> listeDevoir;

    public static DevoirDAO getInstance() {
        if (null == instance) {
            instance = new DevoirDAO();
        }
        return instance;
    }

    public DevoirDAO() {
        listeDevoir = new ArrayList<HashMap<String, String>>();
        preparerListeDevoir();
    }

    public List<HashMap<String, String>> recupererListeDevoir() {
        return listeDevoir;
    }

    private void preparerListeDevoir() {

        HashMap<String, String> devoir;

        devoir = new HashMap<String, String>();
        devoir.put("matière", "Anglais");
        devoir.put("sujet", "Faire article de journal");
        listeDevoir.add(devoir);

        devoir = new HashMap<String, String>();
        devoir.put("matière", "Android");
        devoir.put("sujet", "Faire une release");
        listeDevoir.add(devoir);

        devoir = new HashMap<String, String>();
        devoir.put("matière", "Mondes virtuels");
        devoir.put("sujet", "Finir l'animation");
        listeDevoir.add(devoir);
    }
}
