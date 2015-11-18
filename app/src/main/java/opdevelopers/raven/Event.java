package opdevelopers.raven;

/**
 * Created by Eduardo on 18/11/2015.
 */
public class Event {

    private String id;
    private String email;
    private String mensaje;
    private String date;
    private String time;
    private String periodicidad;

    public Event(String id, String email, String mensaje, String date, String time, String periodicidad) {
        this.id = id;
        this.email = email;
        this.mensaje = mensaje;
        this.date = date;
        this.time = time;
        this.periodicidad = periodicidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }
}