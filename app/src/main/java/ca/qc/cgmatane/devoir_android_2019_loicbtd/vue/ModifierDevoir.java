package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.modele.Devoir;

public class ModifierDevoir extends AppCompatActivity {

    protected DevoirDAO accesseurDevoir;
    protected Devoir devoir;
    protected EditText vueModifierDevoirChampSujet;
    protected EditText vueModifierDevoirChampMatiere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_devoir);

        Bundle parametres = this.getIntent().getExtras();
        String parametreId_devoir = (String) parametres.get("id_devoir");
        int idDevoir = Integer.parseInt(parametreId_devoir);

        this.accesseurDevoir = DevoirDAO.getInstance();

        devoir = accesseurDevoir.chercherDevoirParIdDevoir(idDevoir);

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
                        enregistrerLivre();
                    }
                }
        );
    }

    private void enregistrerLivre() {

        devoir.setMatiere(vueModifierDevoirChampMatiere.getText().toString());
        devoir.setSujet(vueModifierDevoirChampSujet.getText().toString());

        accesseurDevoir.modifierDevoir(devoir);

        naviguerRetourBibliotheque();
    }

    private void naviguerRetourBibliotheque() {
        this.finish();
    }
}