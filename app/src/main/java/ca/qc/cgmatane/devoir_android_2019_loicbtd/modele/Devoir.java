package ca.qc.cgmatane.devoir_android_2019_loicbtd.modele;

import java.util.HashMap;

public class Devoir {

    protected String matiere;
    protected String sujet;
    protected int id_devoir;

    public Devoir(String matiere, String sujet, int id_devoir) {
        this.matiere = matiere;
        this.sujet = sujet;
        this.id_devoir = id_devoir;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getId_devoir() {
        return id_devoir;
    }

    public void setId_devoir(int id_devoir) {
        this.id_devoir = id_devoir;
    }

    public HashMap<String, String> obtenirDevoirPourAdapteur() {
        HashMap<String, String> devoirPourAdapteur = new HashMap<String, String>();
        devoirPourAdapteur.put("matiere",this.matiere);
        devoirPourAdapteur.put("sujet",this.sujet);
        devoirPourAdapteur.put("id_devoir","" + this.id_devoir);
        return devoirPourAdapteur;
    }
}
