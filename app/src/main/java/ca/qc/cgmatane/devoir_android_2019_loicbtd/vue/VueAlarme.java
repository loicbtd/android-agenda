package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public interface VueAlarme {
    void afficherDevoir();
    String getIdDevoirParametre();
    void setDevoir(Devoir devoir);
    void naviguerAgenda();
}
