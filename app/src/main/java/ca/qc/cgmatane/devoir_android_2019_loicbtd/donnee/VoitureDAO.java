package ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VoitureDAO {

    private static VoitureDAO instance = null;
    protected List<HashMap<String, String>> listeVoiture;

    public static VoitureDAO getInstance() {
        if (null == instance) {
            instance = new VoitureDAO();
        }
        return instance;
    }

    public VoitureDAO() {
        listeVoiture = new ArrayList<HashMap<String, String>>();
        preparerListeVoiture();
    }

    public List<HashMap<String, String>> recupererListeVoiture() {
        return listeVoiture;
    }

    private void preparerListeVoiture() {

        HashMap<String, String> voiture;

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Chiron");
        voiture.put("puissance", "1500ch");
        listeVoiture.add(voiture);

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Divo");
        voiture.put("puissance", "1500ch");
        listeVoiture.add(voiture);

        voiture = new HashMap<String, String>();
        voiture.put("modèle", "Bugatti Veyron");
        voiture.put("puissance", "1001ch");
        listeVoiture.add(voiture);
    }
}
