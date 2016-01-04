package opdevelopers.raven;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * @author Daniel Uroz
 *
 * Pantalla que se encarga de establecer un contador
 */
public class ContadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        setTitle();
    }


    /**
     * Establece el título del Activity
     */
    private void setTitle() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.contador);
        }
    }
}
