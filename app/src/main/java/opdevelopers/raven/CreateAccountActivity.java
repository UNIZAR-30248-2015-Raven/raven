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
    private EditText mContrasenyaText;      // Contraseña del usuario
    private EditText mNombreContactoText;   // Nombre del contancto auxiliar
    private EditText mApellidoContactoText; // Apellido del contacto auxiliar
    private EditText mTelefonoContactoText; // Teléfono del contacto auxiliar

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email = obtenerEmailUsuario();

        setContentView(R.layout.activity_create_account);
        if (!email.equals("")) {
            setTitle(R.string.ver_cuenta);
        }
        else {
            setTitle(R.string.registrarse);
        }

        mNombreText = (EditText) findViewById(R.id.nombre);
        mApellidoText = (EditText) findViewById(R.id.apellido);
        mEmailText = (EditText) findViewById(R.id.email);
        mAnyoNacimientoText = (EditText) findViewById(R.id.anyo_nacimiento);
        mTelefonoText = (EditText) findViewById(R.id.telefono);
        mInfoMedicaText = (EditText) findViewById(R.id.info_medica);
        mResidenciaText = (EditText) findViewById(R.id.residencia);
        mContrasenyaText = (EditText) findViewById(R.id.contrasenya);
        mNombreContactoText = (EditText) findViewById(R.id.nombreContacto);
        mApellidoContactoText = (EditText) findViewById(R.id.apellidoContacto);
        mTelefonoContactoText = (EditText) findViewById(R.id.telefonoContacto);

        Button botonAceptar = (Button) findViewById(R.id.aceptar);

        if (!email.equals("")) {
            rellenarDatosUsuario();

            botonAceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(CreateAccountActivity.this, MainActivity.class);
                    CreateAccountActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                }
            });
        }
        else {
            botonAceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boolean registrado = registrarUsuario();
                    if (registrado) {
                        actualizarPrefsUsuario();
                        Toast.makeText(getApplicationContext(), R.string.exito_datos,
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateAccountActivity.this, MainActivity.class);
                        CreateAccountActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.error_datos,
                                Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }

    private void actualizarPrefsUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        SharedPreferences.Editor editor = prefsCorreo.edit();
        editor.putString("email", email);
        editor.commit();
    }

    private String obtenerEmailUsuario() {
        SharedPreferences prefsCorreo = getSharedPreferences(USUARIO, 0);
        String email = prefsCorreo.getString("email", null);
        return email;
    }

    /*
     * Envía una petición http con los datos introducidos por el usuario para registrarlos en la
     * base de datos del servidor.
     */
    public boolean registrarUsuario() {
        boolean peticionAceptada = false;
        try {
            String nombre = mNombreText.getText().toString();
            String apellido = mApellidoText.getText().toString();
            email = mEmailText.getText().toString();
            String anyoNacimiento = mAnyoNacimientoText.getText().toString();
            String telefono = mTelefonoText.getText().toString();
            String infoMedica = mInfoMedicaText.getText().toString();
            String residencia = mResidenciaText.getText().toString();
            String contrasenya = mContrasenyaText.getText().toString();
            String nombreContacto = mNombreContactoText.getText().toString();
            String apellidoContacto = mApellidoContactoText.getText().toString();
            String telefonoContacto = mTelefonoContactoText.getText().toString();

            UserAdapter adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, true);
            User usuario = new User(nombre, apellido, email, anyoNacimiento, telefono, infoMedica,
                    residencia, contrasenya, nombreContacto, apellidoContacto, telefonoContacto);
            peticionAceptada = adaptadorUsuarios.enviarPeticionRegistrar(usuario);
        }
        catch (ErrorException e) {
            e.printStackTrace();
        }
        return peticionAceptada;
    }

    private void rellenarDatosUsuario() {
        UserAdapter adaptadorUsuarios = new UserAdapter(Constants.FETCH_USER, false, email);
        User usuario = adaptadorUsuarios.peticionFetchUsuario();
        mNombreText.setText(usuario.getNombre());
        mNombreText.setEnabled(false);
        mApellidoText.setText(usuario.getApellido());
        mApellidoText.setEnabled(false);
        mEmailText.setText(usuario.getEmail());
        mEmailText.setEnabled(false);
        mAnyoNacimientoText.setText(usuario.getAnyoNacimiento());
        mAnyoNacimientoText.setEnabled(false);
        mTelefonoText.setText(usuario.getTelefono());
        mTelefonoText.setEnabled(false);
        mInfoMedicaText.setText(usuario.getInfoMedica());
        mInfoMedicaText.setEnabled(false);
        mResidenciaText.setText(usuario.getResidencia());
        mResidenciaText.setEnabled(false);
        mContrasenyaText.setText(usuario.getContrasenya());
        mContrasenyaText.setEnabled(false);
        mNombreContactoText.setText(usuario.getNombreContacto());
        mNombreContactoText.setEnabled(false);
        mApellidoContactoText.setText(usuario.getApellidoContacto());
        mApellidoContactoText.setEnabled(false);
        mTelefonoContactoText.setText(usuario.getTelefonoContacto());
        mTelefonoContactoText.setEnabled(false);
    }

}