package opdevelopers.raven;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class UserAdapter {

    HttpClient httpClient = null;
    HttpPost request = null;

    public UserAdapter() {
        httpClient = HttpClientBuilder.create().build();
        request = new HttpPost("mongodb://192.168.1.108/raven");
    }

    public boolean enviarPeticion(String nombre, String apellido, String email, String anyoNacimiento,
                                  String telefono, String infoMedica, String residencia, String contrasenya,
                                  String nombreContacto, String apellidoContacto, String telefonoContacto) {
        try {
            String details = "details={\"tlf\":\"" + telefono + "\",\"email\":\"" + email + "\","
                    + "\"nombre\":\"" + nombre + "\",\"apellido\":\"" + apellido + "\","
                    + "\"pass\":\"" + contrasenya + "\",\"nacimiento\":\"" + anyoNacimiento + "\","
                    + "\"info\":\"" + infoMedica + "\",\"residencia\":\"" + residencia + "\","
                    + "\"contacto\": { \"nombre\":\"" + nombreContacto + "\",\"apellido\":\"" + apellidoContacto + "\","
                    + "\"tlf\":\"" + telefonoContacto + "\" } }";
            StringEntity params = new StringEntity(details);
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            if (responseBody.contains("200")) {
                return true;
            }
            else if (responseBody.contains("400")) {
                return false;
            }
            else {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
    }
}