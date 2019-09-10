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

public class ModifierDevoir extends AppCompatActivity implements View.OnClickListener {

    protected Button vueModifierDevoirActionChoisirHoraire;
    protected EditText vueModifierDevoirChampSujet;
    protected EditText vueModifierDevoirChampMatiere;
    protected TimePickerDialog selectionneurHoraire;
    protected DatePickerDialog selectionneurDate;

    protected DevoirDAO accesseurDevoir;
    protected Devoir devoir;

    protected LocalDateTime horaire;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_devoir);

        Bundle parametres = this.getIntent().getExtras();
        String parametreId_devoir = (String) parametres.get("id_devoir");
        int idDevoir = Integer.parseInt(parametreId_devoir);

        this.accesseurDevoir = DevoirDAO.getInstance();

        devoir = accesseurDevoir.chercherDevoirParIdDevoir(idDevoir);
        horaire = devoir.getHoraire();

        vueModifierDevoirChampSujet = (EditText)findViewById(R.id.vue_modifier_devoir_champ_sujet);
        vueModifierDevoirChampMatiere = (EditText)findViewById(R.id.vue_modifier_devoir_champ_matiere);
        vueModifierDevoirChampSujet.setText(devoir.getSujet());
        vueModifierDevoirChampMatiere.setText(devoir.getMatiere());

        Button vueModifierLivreActionEnregistrerLivre =
                (Button)findViewById(R.id.vue_modifier_devoir_action_enregistrer);

        vueModifierLivreActionEnregistrerLivre.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enregistrerDevoir();
                    }
                }
        );

        vueModifierDevoirActionChoisirHoraire =
                (Button)findViewById(R.id.vue_modifier_devoir_action_choisir_horaire);
        vueModifierDevoirActionChoisirHoraire.setOnClickListener(this);

        DateTimeFormatter formateur =
                DateTimeFormatter.ofPattern("dd/MM/YYYY à HH:mm");
        vueModifierDevoirActionChoisirHoraire.setText(devoir.getHoraire().format(formateur));
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
                                DateTimeFormatter.ofPattern("dd/MM/YYYY à HH:mm:ss");
                        vueModifierDevoirActionChoisirHoraire.setText(horaire.format(formateur));
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

    private void enregistrerDevoir() {

        devoir.setMatiere(vueModifierDevoirChampMatiere.getText().toString());
        devoir.setSujet(vueModifierDevoirChampSujet.getText().toString());
        devoir.setHoraire(horaire);

        accesseurDevoir.modifierDevoir(devoir);

        naviguerRetourAgenda();
    }

    private void naviguerRetourAgenda() {
        this.finish();
    }
}