package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAjouterDevoir;

// TODO: OK 3
public class ControleurAjouterDevoir implements Controleur {

    private VueAjouterDevoir vue;
    private DevoirDAO accesseurDevoir;

    protected TimePickerDialog selectionneurHoraire;
    protected DatePickerDialog selectionneurDate;

    protected LocalDateTime horaire;


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
//        horaire = LocalDateTime.now(); TODO
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
