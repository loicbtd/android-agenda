package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

// TODO: OK
public interface VueModifierDevoir {
    void afficherDevoir();
    void setDevoir(Devoir devoir);
    String getIdDevoirParametre();
    void naviguerAgenda();
    void afficherErreur();
}
