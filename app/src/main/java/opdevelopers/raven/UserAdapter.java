package opdevelopers.raven;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class UserAdapter extends AsyncTask<Void, Void, Void> {

    HttpURLConnection conn = null;
    HashMap<String, String> postDataParams = null;
    int responseCode = -1;

    public UserAdapter() {
        try {
            URL url = new URL("http://raven-sirbargus.rhcloud.com/createUser");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean enviarPeticion(String nombre, String apellido, String email, String anyoNacimiento,
                                  String telefono, String infoMedica, String residencia, String contrasenya,
                                  String nombreContacto, String apellidoContacto, String telefonoContacto) {
        postDataParams = new HashMap<>();
        postDataParams.put("tlf", telefono);
        postDataParams.put("email", email);
        postDataParams.put("pass", contrasenya);
        postDataParams.put("nombre", nombre);
        postDataParams.put("apellido", apellido);
        postDataParams.put("info", infoMedica);
        postDataParams.put("residencia", residencia);
        postDataParams.put("nacimiento", anyoNacimiento);
        postDataParams.put("contactoNombre", nombreContacto);
        postDataParams.put("contactoApellido", apellidoContacto);
        postDataParams.put("contactoTelefono", telefonoContacto);

        doInBackground();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Conexión con la BBDD
            conn.connect();

            OutputStream os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            responseCode = conn.getResponseCode();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry: params.entrySet()) {
            if (first) {
                first = false;
            }
            else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}