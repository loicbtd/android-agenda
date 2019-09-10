package ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.BaseDeDonnees;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.vue.VueAjouterDevoir;

// TODO: OK 3
public class ControleurAjouterDevoir implements Controleur  {

    private VueAjouterDevoir vue;
    private DevoirDAO accesseurDevoir;

//    protected LocalDateTime horaire; TODO


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

//    @RequiresApi(api = Build.VERSION_CODES.O) TODO
//    @Override
//    public void onClick(View vue) {
//
//        selectionneurHoraire = new TimePickerDialog(this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int h, int m) {
//                        horaire = LocalDateTime.of(horaire.getYear(),
//                                horaire.getMonthValue(),
//                                horaire.getDayOfMonth(),
//                                h,
//                                m
//                        );
//                        DateTimeFormatter formateur =
//                                DateTimeFormatter.ofPattern("dd/MM/dd à HH:mm");
//                        vueAjouterDevoirActionChoisirHoraire.setText(horaire.format(formateur));
//                    }
//                }, horaire.getHour(), horaire.getMinute(), false);
//
//        selectionneurDate = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int a, int m, int j) {
//                        horaire = LocalDateTime.of(a,
//                                m,
//                                j,
//                                horaire.getHour(),
//                                horaire.getMinute()
//                        );
//
//                        selectionneurHoraire.show();
//                    }
//                }, horaire.getYear(), horaire.getMonthValue(), horaire.getDayOfMonth());
//
//        selectionneurDate.show();
//    }
}
