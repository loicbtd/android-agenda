package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.util.Calendar;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class AjouterDevoir extends AppCompatActivity implements View.OnClickListener {

    protected EditText vueAjouterDevoirChampMatiere;
    protected EditText vueAjouterDevoirChampSujet;
    protected Button vueAjouterDevoirActionChoisirHoraire;
    protected TimePickerDialog selectionneurHoraire;
    protected DatePickerDialog selectionneurDate;


    protected DevoirDAO accesseurDevoir;

    protected int annee;
    protected int mois;
    protected int jour;
    protected int heure;
    protected int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_devoir);

        vueAjouterDevoirChampMatiere = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);
        vueAjouterDevoirChampSujet = (EditText)findViewById(R.id.vue_ajouter_devoir_champ_sujet);

        Button vueAjouterDevoirActionEnregistrerDevoir =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_enregistrer);

        vueAjouterDevoirActionEnregistrerDevoir.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        enregistrerDevoir();
                    }
                }
        );

        vueAjouterDevoirActionChoisirHoraire =
                (Button)findViewById(R.id.vue_ajouter_devoir_action_choisir_horaire);
        vueAjouterDevoirActionChoisirHoraire.setText("Choisir l'horaire");
        vueAjouterDevoirActionChoisirHoraire.setOnClickListener(this);
    }

    @Override
    public void onClick(View vue) {
        final Calendar calendrier = Calendar.getInstance();
        annee = calendrier.get(Calendar.YEAR);
        mois = calendrier.get(Calendar.MONTH);
        jour = calendrier.get(Calendar.DAY_OF_MONTH);
        heure = calendrier.get(Calendar.HOUR_OF_DAY);
        minute = calendrier.get(Calendar.MINUTE);

        selectionneurHoraire = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        heure = h;
                        minute = m;
                    }
                }, heure, minute, false);

        selectionneurDate = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int a, int m, int j) {
                        annee = a;
                        mois = m;
                        jour = j;

                        selectionneurHoraire.show();
                    }
                }, annee, mois, jour);

        selectionneurDate.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enregistrerDevoir() {

        accesseurDevoir = DevoirDAO.getInstance();
        // TODO : enlever la date en dure
        Devoir devoir = new Devoir(
                0,
                vueAjouterDevoirChampMatiere.getText().toString(),
                vueAjouterDevoirChampSujet.getText().toString(),
                LocalDateTime.now()
        );

        accesseurDevoir.ajouterDevoir(devoir);

        naviguerRetourAgenda();
    }

    private void naviguerRetourAgenda() {
        this.finish();
    }
}
