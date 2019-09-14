package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueModifierDevoir;

// TODO: NOTOK
public class ControleurModifierDevoir implements Controleur {

    private VueModifierDevoir vue;
    private Devoir devoir;
    private DevoirDAO accesseurDevoir;

    public ControleurModifierDevoir(VueModifierDevoir vue) {
        this.vue = vue;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void actionEnregistrerDevoir(Devoir devoir) {
        if(devoir.isValide()) {
            accesseurDevoir = DevoirDAO.getInstance();
            accesseurDevoir.modifierDevoir(devoir);
            vue.naviguerAgenda();
        }
        else {
            vue.afficherErreur();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        int idDevoir = Integer.parseInt(vue.getIdDevoirParametre());
        this.accesseurDevoir = DevoirDAO.getInstance();
        devoir = accesseurDevoir.chercherDevoirParIdDevoir(idDevoir);
        vue.setDevoir(devoir);
        vue.afficherDevoir();
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

    @Override
    public void onActivityResult(int activite) {
    }
}
