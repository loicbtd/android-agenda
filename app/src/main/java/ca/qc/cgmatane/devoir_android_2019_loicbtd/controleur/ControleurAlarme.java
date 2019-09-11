package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.content.Context;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAlarme;

public class ControleurAlarme implements Controleur {

    private VueAlarme vue;
    private Devoir devoir;
    private DevoirDAO accesseurDevoir;

    public ControleurAlarme(VueAlarme vue) {
        this.vue = vue;
    }

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
