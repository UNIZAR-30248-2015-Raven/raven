package opdevelopers.raven;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Eduardo on 27/10/2015.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int ACTIVITY_CLIENTE = 1;
    private static final String USUARIO = "CorreoUsuario";

    private EditText mEmailText;            // Email del usuario
    private EditText mContrasenyaText;      // Contraseña del usuario

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setTitle(R.string.app_name);

        mEmailText = (EditText) findViewById(R.id.email);
        mContrasenyaText = (EditText) findViewById(R.id.contrasenya);

        Button botonIniciarSesion = (Button) findViewById(R.id.iniciar_sesion);
        Button botonRegistrar = (Button) findViewById(R.id.registrarse);

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean comprobado = comprobarUsuario();
                if (comprobado) {
                    actualizarPrefsUsuario();
                    Toast.makeText(getApplicationContext(), R.string.exito_sesion,
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_datos,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                inicializarPrefsUsuario();
                Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
                LoginActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
            }
        });
    }

    private void actualizarPrefsUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        SharedPreferences.Editor editor = prefsCorreo.edit();
        editor.putString("email", email);
        editor.commit();
    }

    private void inicializarPrefsUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        SharedPreferences.Editor editor = prefsCorreo.edit();
        editor.putString("email", "");
        editor.commit();
    }

    /*
     * Envía una petición http con los datos introducidos por el usuario para comprobar en la
     * base de datos del servidor que el usuario está registrado y, en caso afirmativo, verificar
     * que el email y la contraseña son correctos.
     */
    public boolean comprobarUsuario() {
        boolean sesionAceptada = false;
        try {
            email = mEmailText.getText().toString();
            String contrasenya = mContrasenyaText.getText().toString();

            UserAdapter adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, false);
            User usuario = new User(email, contrasenya);
            sesionAceptada = adaptadorUsuarios.enviarPeticionSesion(usuario);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        return sesionAceptada;
    }

}