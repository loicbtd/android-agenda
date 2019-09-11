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
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.controleur.ControleurModifierDevoir;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

//public class ModifierDevoir extends AppCompatActivity implements View.OnClickListener {
public class ModifierDevoir extends AppCompatActivity implements VueModifierDevoir {

    protected Devoir devoir;
    protected EditText vueModifierDevoirChampSujet;
    protected EditText vueModifierDevoirChampMatiere;
    protected LocalDateTime horaire;
    protected ControleurModifierDevoir controleurModifierDevoir = new ControleurModifierDevoir(this);
    protected String idDevoirParametre;



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enregistrerDevoir() {
        devoir.setMatiere(vueModifierDevoirChampMatiere.getText().toString());
        devoir.setSujet(vueModifierDevoirChampSujet.getText().toString());
        horaire = LocalDateTime.now();
        devoir.setHoraire(horaire);
        controleurModifierDevoir.actionEnregistrerDevoir(devoir);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afficherDevoir() {
        vueModifierDevoirChampMatiere = (EditText)findViewById(R.id.vue_modifier_devoir_champ_matiere);
        vueModifierDevoirChampSujet = (EditText)findViewById(R.id.vue_modifier_devoir_champ_sujet);
        vueModifierDevoirChampMatiere.setText(devoir.getMatiere());
        vueModifierDevoirChampSujet.setText(devoir.getSujet());

        Button vueModifierDevoirActionEnregistrer =
                (Button)findViewById(R.id.vue_modifier_devoir_action_enregistrer);

        vueModifierDevoirActionEnregistrer.setOnClickListener(arg0 ->
                enregistrerDevoir());
    }

    @Override
    public void setDevoir(Devoir devoir) {
        this.devoir = devoir;
    }

    @Override
    public String getIdDevoirParametre() {
        return idDevoirParametre;
    }

    @Override
    public void naviguerAgenda() {
        this.finish();
    }

    @Override
    public void afficherErreur() {
        Toast message = Toast.makeText(this.getApplicationContext(),
                "Le devoir n'est pas valide.",
                Toast.LENGTH_SHORT);
        message.show();
    }
}