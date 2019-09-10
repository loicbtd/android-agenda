package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

// TODO : OK
public interface VueAgenda {
    void setListeDevoir(List<Devoir> listeDevoir);
    void afficherTousLesDevoirs();
    void naviguerAjouterDevoir();
    void naviguerModifierDevoir(String idDevoir);
}
