package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.DevoirDAO;

public class Agenda extends AppCompatActivity {

    protected ListView vueListeDevoir;
    protected List<HashMap<String, String>> listeDevoir;
    protected DevoirDAO accesseurDevoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_agenda);

        accesseurDevoir = DevoirDAO.getInstance();
        vueListeDevoir = (ListView) findViewById(R.id.vue_agenda_liste_devoir);

        listeDevoir = accesseurDevoir.recupererListeDevoir();

        SimpleAdapter adapteurVueListeDevoir = new SimpleAdapter(
                this,
                listeDevoir,
                android.R.layout.two_line_list_item,
                new String[] {"matière", "sujet"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeDevoir.setAdapter(adapteurVueListeDevoir);
    }
}
