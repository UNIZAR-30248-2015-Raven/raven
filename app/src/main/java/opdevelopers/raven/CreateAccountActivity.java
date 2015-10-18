package opdevelopers.raven;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Eduardo on 14/10/2015.
 */
public class CreateAccountActivity extends AppCompatActivity {

    private static final int ACTIVITY_CLIENTE = 1;
    private static final String USUARIO = "CorreoUsuario";

    private EditText mNombreText;           // Nombre del usuario
    private EditText mApellidoText;         // Apellido del usuario
    private EditText mEmailText;            // Email del usuario
    private EditText mAnyoNacimientoText;   // Año de nacimiento del usuario
    private EditText mTelefonoText;         // Teléfono del usuario
    private EditText mInfoMedicaText;       // Información médica del usuario
    private EditText mResidenciaText;       // Lugar de residencia del usuario
    private EditText mContrasenyaText;      // Contraseña médica del usuario

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);
        setTitle(R.string.registrarse);

        mNombreText = (EditText) findViewById(R.id.nombre);
        mApellidoText = (EditText) findViewById(R.id.apellido);
        mEmailText = (EditText) findViewById(R.id.email);
        mAnyoNacimientoText = (EditText) findViewById(R.id.anyo_nacimiento);
        mTelefonoText = (EditText) findViewById(R.id.telefono);
        mInfoMedicaText = (EditText) findViewById(R.id.info_medica);
        mResidenciaText = (EditText) findViewById(R.id.residencia);
        mContrasenyaText = (EditText) findViewById(R.id.contrasenya);

        Button botonSeguir = (Button) findViewById(R.id.seguir);

        botonSeguir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean registrado = registrarUsuario();
                if (registrado) {
                    actualizarPrefsUsuario();
                    Intent i = new Intent(CreateAccountActivity.this, CompleteAccountActivity.class);
                    CreateAccountActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                }
            }

        });
    }

    private void actualizarPrefsUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        SharedPreferences.Editor editor = prefsCorreo.edit();
        editor.putString("email", email);
        editor.commit();
    }

    /*
     * Envía una petición http con los datos introducidos por el usuario para registrarlos en la
     * base de datos del servidor.
     */
    public boolean registrarUsuario() {
        String nombre = mNombreText.getText().toString();
        String apellido = mApellidoText.getText().toString();
        email = mEmailText.getText().toString();
        String anyoNacimiento = mAnyoNacimientoText.getText().toString();
        String telefono = mTelefonoText.getText().toString();
        String infoMedica = mInfoMedicaText.getText().toString();
        String residencia = mResidenciaText.getText().toString();
        String contrasenya = mContrasenyaText.getText().toString();

        UserAdapter adaptadorUsuarios = new UserAdapter();
        boolean peticionAceptada = adaptadorUsuarios.enviarPeticion(nombre, apellido, email,
                anyoNacimiento, telefono, infoMedica, residencia, contrasenya);
        return peticionAceptada;
    }

}