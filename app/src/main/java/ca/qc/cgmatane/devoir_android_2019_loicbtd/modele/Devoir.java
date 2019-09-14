package ca.qc.cgmatane.devoir_android_2019_loicbtd.modele;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Devoir {

    public static final DateTimeFormatter FORMAT_DATE_AFFICHAGE =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter FORMAT_DATE_STOCKAGE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static final String CLE_ID_DEVOIR = "id_devoir";
    public static final String CLE_MATIERE = "matiere";
    public static final String CLE_SUJET = "sujet";
    public static final String CLE_HORAIRE = "horaire";
    public static final String CLE_ALARME_ACTIVE = "alarme_active";


    protected int id_devoir;
    protected String matiere;
    protected String sujet;
    protected LocalDateTime horaire;
    protected boolean alarme_active;

    public Devoir(int id_devoir, String matiere, String sujet, LocalDateTime horaire, boolean alarme_active) {
        this.id_devoir = id_devoir;
        this.matiere = matiere;
        this.sujet = sujet;
        this.horaire = horaire;
        this.alarme_active = alarme_active;
    }


    public int getId_devoir() {
        return id_devoir;
    }
    public void setId_devoir(int id_devoir) {
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
    public LocalDateTime getHoraire() {
        return horaire;
    }
    public void setHoraire(LocalDateTime horaire) {
        this.horaire = horaire;
    }
    public boolean isAlarme_active() {
        return alarme_active;
    }
    public void setAlarme_active(boolean alarme_active) {
        this.alarme_active = alarme_active;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String, String> obtenirDevoirPourAdapteur() {

        HashMap<String, String> devoirPourAdapteur = new HashMap<>();

        devoirPourAdapteur.put(CLE_ID_DEVOIR,String.valueOf(this.id_devoir));
        devoirPourAdapteur.put(CLE_MATIERE,this.matiere);
        devoirPourAdapteur.put(CLE_SUJET,this.sujet);
        devoirPourAdapteur.put(CLE_HORAIRE,this.horaire.format(Devoir.FORMAT_DATE_AFFICHAGE));
        devoirPourAdapteur.put(CLE_ALARME_ACTIVE,this.alarme_active ? "OUI" : "NON");

        return devoirPourAdapteur;
    }

    public boolean isValide() {
        if(this.matiere == null || this.matiere.isEmpty()
                || this.sujet == null || this.sujet.isEmpty() ) {
            return false;
        }
        return true;
    }
}
