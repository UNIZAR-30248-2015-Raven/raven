package opdevelopers.raven;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
    StringBuilder result = new StringBuilder();
    public boolean TEST = false;

    public UserAdapter (int typeRequest, boolean nuevoUsuario, String... getParams) {
        try {
            switch (typeRequest) {
                case Constants.CREATE_USER:
                    URL url = null;
                    if (nuevoUsuario) {
                        url = new URL("http://raven-sirbargus.rhcloud.com/createUser");
                    } else {
                        url = new URL("http://raven-sirbargus.rhcloud.com/loginUser");
                    }
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    break;
                case Constants.FETCH_USER:
                    URL url2 = url2 = new URL("http://raven-sirbargus.rhcloud.com/findUser/" + getParams[0]);
                    conn = (HttpURLConnection) url2.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    rd.close();
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User jsonFormatter(String json) {
        User usuario = null;
        try {
            JSONObject jsonOb = new JSONObject(json);
            usuario = new User(jsonOb.getString("nombre"), jsonOb.getString("apellido"),
                    jsonOb.getString("email"), jsonOb.getString("nacimiento"), jsonOb.getString("tlf"),
                    jsonOb.getString("info"), jsonOb.getString("residencia"), jsonOb.getString("pass"),
                    jsonOb.getString("contactoNombre"), jsonOb.getString("contactoApellido"),
                    jsonOb.getString("contactoTelefono"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (ErrorException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public User peticionFetchUsuario() {
        return this.jsonFormatter(result.toString());
    }

    public boolean enviarPeticionRegistrar(User usuario) {
        postDataParams = new HashMap<>();
        postDataParams.put("tlf", usuario.getTelefono());
        postDataParams.put("email", usuario.getEmail());
        postDataParams.put("pass", usuario.getContrasenya());
        postDataParams.put("nombre", usuario.getNombre());
        postDataParams.put("apellido", usuario.getApellido());
        postDataParams.put("info", usuario.getInfoMedica());
        postDataParams.put("residencia", usuario.getResidencia());
        postDataParams.put("nacimiento", usuario.getAnyoNacimiento());
        postDataParams.put("contactoNombre", usuario.getNombreContacto());
        postDataParams.put("contactoApellido", usuario.getApellidoContacto());
        postDataParams.put("contactoTelefono", usuario.getTelefonoContacto());

        doInBackground();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean enviarPeticionSesion(User usuario) {
        postDataParams = new HashMap<>();
        postDataParams.put("email", usuario.getEmail());
        postDataParams.put("pass", usuario.getContrasenya());

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
            if (!TEST) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
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

    public void onPostExecute() {
    }
}