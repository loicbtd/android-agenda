package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAjouterDevoir;

public class ControleurAjouterDevoir implements Controleur {

    private VueAjouterDevoir vue;
    private DevoirDAO accesseurDevoir;

    public ControleurAjouterDevoir(VueAjouterDevoir vue) {
        this.vue = vue;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void actionEnregistrerDevoir(Devoir devoir) {
        if(devoir.isValide()) {
            accesseurDevoir = DevoirDAO.getInstance();
            accesseurDevoir.ajouterDevoir(devoir);
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
