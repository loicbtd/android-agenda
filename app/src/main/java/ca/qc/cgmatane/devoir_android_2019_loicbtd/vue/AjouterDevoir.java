package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import java.time.format.DateTimeFormatter;

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

    LocalDateTime horaire;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_devoir);

        horaire = LocalDateTime.now();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View vue) {

        selectionneurHoraire = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        horaire = LocalDateTime.of(horaire.getYear(),
                                horaire.getMonthValue(),
                                horaire.getDayOfMonth(),
                                h,
                                m
                        );
                        DateTimeFormatter formateur =
                                DateTimeFormatter.ofPattern("dd/MM/dd à HH:mm");
                        vueAjouterDevoirActionChoisirHoraire.setText(horaire.format(formateur));
                    }
                }, horaire.getHour(), horaire.getMinute(), false);

        selectionneurDate = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int a, int m, int j) {
                        horaire = LocalDateTime.of(a,
                                m,
                                j,
                                horaire.getHour(),
                                horaire.getMinute()
                        );

                        selectionneurHoraire.show();
                    }
                }, horaire.getYear(), horaire.getMonthValue(), horaire.getDayOfMonth());

        selectionneurDate.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enregistrerDevoir() {

        accesseurDevoir = DevoirDAO.getInstance();
        Devoir devoir = new Devoir(
                0,
                vueAjouterDevoirChampMatiere.getText().toString(),
                vueAjouterDevoirChampSujet.getText().toString(),
                horaire
        );

        accesseurDevoir.ajouterDevoir(devoir);

        naviguerRetourAgenda();
    }

    private void naviguerRetourAgenda() {
        this.finish();
    }
}
