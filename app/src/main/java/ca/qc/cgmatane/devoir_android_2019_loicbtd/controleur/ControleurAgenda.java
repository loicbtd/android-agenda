package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAgenda;

// TODO: OK
public class ControleurAgenda implements Controleur {

    static final public int ACTIVITE_AJOUTER_DEVOIR = 1;
    static final public int ACTIVITE_MODIFIER_DEVOIR = 2;

    private VueAgenda vue;
    private DevoirDAO accesseurDevoir;

    public ControleurAgenda(VueAgenda vue) {
        this.vue = vue;
    }

    public void actionNaviguerAjouterDevoir() {
        vue.naviguerAjouterDevoir();
    }

    public void actionNaviguerModifierDevoir(String idDevoir) {
        vue.naviguerModifierDevoir(idDevoir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurDevoir = DevoirDAO.getInstance();
        vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
        vue.afficherTousLesDevoirs();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int activite) {
        switch (activite) {
            case ACTIVITE_AJOUTER_DEVOIR:
                vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
                vue.afficherTousLesDevoirs();
                break;

            case ACTIVITE_MODIFIER_DEVOIR:
                vue.setListeDevoir(accesseurDevoir.recupererListeDevoir());
                vue.afficherTousLesDevoirs();
                break;
        }
    }
}
