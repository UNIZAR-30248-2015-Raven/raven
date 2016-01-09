package opdevelopers.raven;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import opdevelopers.raven.calendario.CalendarioActivity;

/**
 * Created by Eduardo on 18/10/2015.
 */
public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_CALENDARIO = 1;
    private static final int ACTIVITY_CLIENTE = 1;
    private static final String USUARIO = "CorreoUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(R.string.menu);

        ImageButton botonCalendario = (ImageButton) findViewById(R.id.BotonCalendario);
        botonCalendario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CalendarioActivity.class);
                MainActivity.this.startActivityForResult(i, ACTIVITY_CALENDARIO);
            }
        });

        ImageButton botonContador = (ImageButton) findViewById(R.id.botonContador);
        botonContador.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContadorActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSimon = (ImageButton) findViewById(R.id.ibtnSimon);
        botonSimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                logOut();
                return true;
            case R.id.cuenta_usuario:
                verCuentaUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        SharedPreferences.Editor editor = prefsCorreo.edit();
        editor.clear();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void verCuentaUsuario() {
        Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
        MainActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
    }

}
