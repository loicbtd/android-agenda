package ca.qc.cgmatane.devoir_android_2019_loicbtd.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.devoir_android_2019_loicbtd.R;
import ca.qc.cgmatane.devoir_android_2019_loicbtd.donnee.VoitureDAO;

public class Garage extends AppCompatActivity {

    protected ListView vueListeVoiture;
    protected List<HashMap<String, String>> listeVoiture;
    protected VoitureDAO accesseurVoiture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_garage);

        accesseurVoiture = VoitureDAO.getInstance();
        vueListeVoiture = (ListView) findViewById(R.id.vue_garage_liste_voiture);

        listeVoiture = accesseurVoiture.recupererListeVoiture();

        SimpleAdapter adapteurVueListeVoiture = new SimpleAdapter(
                this,
                listeVoiture,
                android.R.layout.two_line_list_item,
                new String[] {"modèle", "puissance"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeVoiture.setAdapter(adapteurVueListeVoiture);
    }
}
