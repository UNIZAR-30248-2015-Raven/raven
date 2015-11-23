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

    public Event(String id, String email, String mensaje, String date, String time, String periodicidad)
            throws ErrorException {
        if (id == null) {
            throw new ErrorException();
        }
        else {
            this.id = id;
        }
        if (email == null || email.equals("") || !email.contains("@") || !email.contains(".")) {
            throw new ErrorException();
        }
        else {
            this.email = email;
        }
        if (mensaje == null || mensaje.equals("")) {
            throw new ErrorException();
        }
        else {
            this.mensaje = mensaje;
        }
        if (date == null || periodicidad == null) {
            throw new ErrorException();
        }
        else {
            if ((date.equals("") && periodicidad.equals("")) || (!date.equals("") && !periodicidad.equals(""))) {
                throw new ErrorException();
            }
            else {
                this.date = date;
                this.periodicidad = periodicidad;
            }
        }
        if (time == null || time.equals("") || !time.contains(":")) {
            throw new ErrorException();
        }
        else {
            this.time = time;
        }
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

    @Override
    public String toString() {
        String cadena = "INFO EVENTO\n";
        cadena += "===========\n";
        cadena += "id_event -> " + this.id + "\n";
        cadena += "email -> " + this.email + "\n";
        cadena += "mensaje -> " + this.mensaje + "\n";
        cadena += "dia -> " + this.date + "\n";
        cadena += "hora -> " + this.time + "\n";
        cadena += "periodicidad -> " + this.periodicidad + "\n\n";
        return cadena;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}