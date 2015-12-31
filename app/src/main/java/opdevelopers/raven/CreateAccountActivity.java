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
                    boolean actualizado = registrarUsuario(true);
                    if (actualizado) {
                        Toast.makeText(getApplicationContext(), R.string.exito_actualizacion,
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateAccountActivity.this, MainActivity.class);
                        CreateAccountActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.error_email_tlf,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            botonAceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boolean registrado = registrarUsuario(false);
                    if (registrado) {
                        actualizarPrefsUsuario();
                        Toast.makeText(getApplicationContext(), R.string.exito_datos,
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateAccountActivity.this, MainActivity.class);
                        CreateAccountActivity.this.startActivityForResult(i, ACTIVITY_CLIENTE);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.error_email_tlf,
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
    public boolean registrarUsuario(boolean modificacion) {
        boolean peticionAceptada = false;
        String error = "";
        try {
            String nombre = mNombreText.getText().toString();
            if (nombre == null || nombre.equals("")) {
                error += "Nombre, ";
            }
            String apellido = mApellidoText.getText().toString();
            if (apellido == null || apellido.equals("")) {
                error += "Apellido, ";
            }
            email = mEmailText.getText().toString();
            if (email == null || email.equals("") || !email.contains("@") || !email.contains(".")) {
                error += "Email, ";
            }
            String anyoNacimiento = mAnyoNacimientoText.getText().toString();
            if (anyoNacimiento == null || anyoNacimiento.equals("") || anyoNacimiento.length() != 4) {
                error += "Año de nacimiento, ";
            }
            else {
                try {
                    int anyo = Integer.parseInt(anyoNacimiento);
                    if (anyo < 0) {
                        error += "Año de nacimiento, ";
                    }
                }
                catch (NumberFormatException e) {
                    throw new ErrorException();
                }
            }
            String telefono = mTelefonoText.getText().toString();
            if (telefono == null || telefono.equals("")) {
                error += "Teléfono, ";
            }
            else {
                try {
                    int tlf = Integer.parseInt(telefono);
                    if (tlf < 0) {
                        error += "Teléfono, ";
                    }
                } catch (NumberFormatException e) {
                    throw new ErrorException();
                }
            }
            String infoMedica = mInfoMedicaText.getText().toString();
            if (infoMedica == null || infoMedica.equals("")) {
                error += "Información médica, ";
            }
            String residencia = mResidenciaText.getText().toString();
            if (residencia == null || residencia.equals("")) {
                error += "Lugar de residencia, ";
            }
            String contrasenya = mContrasenyaText.getText().toString();
            if (contrasenya == null || contrasenya.equals("")) {
                error += "Contraseña, ";
            }
            String nombreContacto = mNombreContactoText.getText().toString();
            if (nombreContacto == null || nombreContacto.equals("")) {
                error += "Nombre del contacto, ";
            }
            String apellidoContacto = mApellidoContactoText.getText().toString();
            if (apellidoContacto == null || apellidoContacto.equals("")) {
                error += "Apellido del contacto, ";
            }
            String telefonoContacto = mTelefonoContactoText.getText().toString();
            if (telefonoContacto == null || telefonoContacto.equals("")) {
                error += "Teléfono del contacto, ";
            }
            else {
                try {
                    int tlfContacto = Integer.parseInt(telefonoContacto);
                    if (tlfContacto < 0) {
                        error += "Teléfono del contacto, ";
                    }
                }
                catch (NumberFormatException e) {
                    throw new ErrorException();
                }
            }

            if (error.length() > 0) {
                String advertencia = "Error en el campo: " + error.substring(0, error.length()-2) + ".";
                Toast.makeText(getApplicationContext(), advertencia, Toast.LENGTH_SHORT).show();
            }


            UserAdapter adaptadorUsuarios = null;
            if (!modificacion) {
                adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, true);
            }
            else {
                adaptadorUsuarios = new UserAdapter(Constants.MODIFY_USER, false, email);
            }
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
        mApellidoText.setText(usuario.getApellido());
        mEmailText.setText(usuario.getEmail());
        mAnyoNacimientoText.setText(usuario.getAnyoNacimiento());
        mTelefonoText.setText(usuario.getTelefono());
        mInfoMedicaText.setText(usuario.getInfoMedica());
        mResidenciaText.setText(usuario.getResidencia());
        mContrasenyaText.setText(usuario.getContrasenya());
        mNombreContactoText.setText(usuario.getNombreContacto());
        mApellidoContactoText.setText(usuario.getApellidoContacto());
        mTelefonoContactoText.setText(usuario.getTelefonoContacto());
    }

}