package opdevelopers.raven;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class EventAdapter extends AsyncTask<Void, Void, Void> {

    HttpURLConnection conn = null;
    HashMap<String, String> postDataParams = null;
    int responseCode = -1;
    StringBuilder result = new StringBuilder();

    public EventAdapter(int typeRequest, String... getParams) {
        try {
            switch (typeRequest) {
                case Constants.CREATE_EVENT:
                    URL url = url = new URL("http://raven-sirbargus.rhcloud.com/createEvent");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    break;
                case Constants.FETCH_EVENTS:
                    URL url2 = url2 = new URL("http://raven-sirbargus.rhcloud.com/getEvents/" + getParams[0]);
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Event> jsonFormatter(String json) {
        List<Event> listaEventos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonOb = new JSONObject(jsonArray.get(i).toString());
                listaEventos.add(new Event(jsonOb.getString("id_event"),
                        jsonOb.getString("email"),
                        jsonOb.getString("texto"),
                        jsonOb.getString("day"),
                        jsonOb.getString("hour"),
                        jsonOb.getString("periodicidad")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (ErrorException e) {
            e.printStackTrace();
        }
        return listaEventos;
    }

    public List<Event> peticionFetchEventos() {
        return this.jsonFormatter(result.toString());
    }

    public boolean enviarPeticionCrearEvento(Event evento) {
        postDataParams = new HashMap<>();
        postDataParams.put("email", evento.getEmail());
        postDataParams.put("texto", evento.getMensaje());
        postDataParams.put("day", evento.getDate());
        postDataParams.put("hour", evento.getTime());
        postDataParams.put("periodicidad", evento.getPeriodicidad());

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

    public void onPostExecute() {

    }
}