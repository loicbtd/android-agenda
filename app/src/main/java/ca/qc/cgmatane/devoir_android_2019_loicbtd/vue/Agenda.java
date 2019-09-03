package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;

public class Agenda extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_DEVOIR = 1;
    static final public int ACTIVITE_MODIFIER_DEVOIR = 2;

    protected ListView vueAgendaListeDevoir;
    protected List<HashMap<String, String>> listeDevoirPourAdapteur;

    protected DevoirDAO accesseurDevoir;

    protected Intent intentionNaviguerAjouterDevoir;
    protected Intent intentionNaviguerModifierDevoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_agenda);

        afficherTousLesDevoirs();

        vueAgendaListeDevoir.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View vue,
                            int positionDansAdapteur,
                            long positionItem) {
                        Log.d("Agenda", "onItemClick");
                        ListView vueAgendaListeDevoirOnClick = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String, String> devoir =
                                (HashMap<String, String>)
                                        vueAgendaListeDevoir.getItemAtPosition((int)positionItem);

                        Intent intentionNaviguerModifierDevoir = new Intent(
                                Agenda.this,
                                ModifierDevoir.class
                        );
                        intentionNaviguerModifierDevoir.putExtra("id_devoir",
                                devoir.get("id_devoir"));

                        startActivityForResult(intentionNaviguerModifierDevoir,
                                ACTIVITE_MODIFIER_DEVOIR);
                    }
                }
        );

        intentionNaviguerAjouterDevoir = new Intent(this, AjouterDevoir.class);

        Button vueAgendaActionNaviguerAjouterDevoir =
                (Button)findViewById(R.id.vue_agenda_action_ajouter_devoir);

        vueAgendaActionNaviguerAjouterDevoir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivityForResult(intentionNaviguerAjouterDevoir, Agenda.ACTIVITE_AJOUTER_DEVOIR);
                    }
                }
        );
    }

    protected void afficherTousLesDevoirs() {
        accesseurDevoir = DevoirDAO.getInstance();

        vueAgendaListeDevoir = (ListView) findViewById(R.id.vue_agenda_liste_devoir);

        listeDevoirPourAdapteur = accesseurDevoir.recupererListeDevoirPourAdapteur();

        SimpleAdapter adapteurVueListeDevoir = new SimpleAdapter(
                this,
                listeDevoirPourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"matiere", "sujet"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueAgendaListeDevoir.setAdapter(adapteurVueListeDevoir);
    }

    @Override
    protected void onActivityResult(int activite, int resultat, @Nullable Intent donnees) {
        switch (activite) {
            case ACTIVITE_AJOUTER_DEVOIR:
                afficherTousLesDevoirs();
                break;

            case ACTIVITE_MODIFIER_DEVOIR:
                afficherTousLesDevoirs();
                break;
        }
    }
}
