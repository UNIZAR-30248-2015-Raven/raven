package opdevelopers.raven;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 23/11/2015.
 */
public class EventTest {

    @Test(expected=ErrorException.class)
    public void testIdNull() throws ErrorException {
        Event evento = new Event(null, "correo@example.com", "Texto", "2015-01-01", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNull() throws ErrorException {
        Event evento = new Event("", null, "Texto", "2015-01-01", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testEmailVacio() throws ErrorException {
        Event evento = new Event("", "", "Texto", "", "11:00", "L M X J V S D");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoArroba() throws ErrorException {
        Event evento = new Event("", "correoexample.com", "Texto", "2015-01-01", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoPunto() throws ErrorException {
        Event evento = new Event("", "correo@examplecom", "Texto", "2015-01-01", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testMensajeNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", null, "2015-01-01", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testMensajeVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "", "", "11:00", "L M X J V S D");
    }

    @Test(expected=ErrorException.class)
    public void testFechaNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", null, "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testPeriodicidadNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", null);
    }

    @Test(expected=ErrorException.class)
    public void testFechaPeriodicidadVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "");
    }

    @Test(expected=ErrorException.class)
    public void testFechaPeriodicidadRelleno() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "L M X J V S D");
    }

    @Test(expected=ErrorException.class)
    public void testHoraNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", null, "");
    }

    @Test(expected=ErrorException.class)
    public void testHoraVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "", "");
    }

    @Test(expected=ErrorException.class)
    public void testHoraNoDosPuntos() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "1100", "");
    }
}
