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

    @Test
    public void testEventoCorrecto1() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        Event prueba = new Event("", "prueba@test.com", "Mensaje", "1994-12-12", "22:00", "");
        prueba.setEmail("correo@example.com");
        prueba.setMensaje("Texto");
        prueba.setDate("2015-01-01");
        prueba.setTime("11:00");
        assertEquals(evento.toString(), prueba.toString());
    }

    @Test
    public void testEventoCorrecto2() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        Event prueba = new Event("", "prueba@test.com", "Mensaje", "1994-12-12", "22:00", "");
        prueba.setEmail("correo@example.com");
        prueba.setMensaje("Texto");
        prueba.setTime("11:00");
        prueba.setPeriodicidad("L M X J V S D");
        assertEquals(evento.toString(), prueba.toString());
    }

    @Test
    public void testEventoCorrecto3() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        Event prueba = new Event(evento.getId(), evento.getEmail(), evento.getMensaje(),
                evento.getDate(), evento.getTime(), evento.getPeriodicidad());
        assertEquals(evento.toString(), prueba.toString());
    }

    @Test
    public void testEventoCorrecto4() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        Event prueba = new Event(evento.getId(), evento.getEmail(), evento.getMensaje(),
                evento.getDate(), evento.getTime(), evento.getPeriodicidad());
        assertEquals(evento.toString(), prueba.toString());
    }

    @Test(expected=ErrorException.class)
    public void testSetIdNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setId(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setEmail(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        evento.setEmail("");
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNoArroba() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setEmail("correoexample.com");
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNoPunto() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setEmail("correo@examplecom");
    }

    @Test(expected=ErrorException.class)
    public void testSetMensajeNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setMensaje(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetMensajeVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        evento.setMensaje("");
    }

    @Test(expected=ErrorException.class)
    public void testSetFechaNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setDate(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetPeriodicidadNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        evento.setPeriodicidad(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetFechaVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setDate("");
    }

    @Test(expected=ErrorException.class)
    public void testSetPeriodicidadVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X J V S D");
        evento.setPeriodicidad("");
    }

    @Test(expected=ErrorException.class)
    public void testSetFechaConPeriodicidad() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "", "11:00", "L M X");
        evento.setPeriodicidad("L M X J V S D");
        evento.setDate("2015-01-01");
    }

    @Test(expected=ErrorException.class)
    public void testSetPeriodicidadConFecha() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2010-10-10", "11:00", "");
        evento.setDate("2015-01-01");
        evento.setPeriodicidad("L M X J V S D");
    }

    @Test(expected=ErrorException.class)
    public void testSetHoraNull() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setTime(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetHoraVacio() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setTime("");
    }

    @Test(expected=ErrorException.class)
    public void testSetHoraNoDosPuntos() throws ErrorException {
        Event evento = new Event("", "correo@example.com", "Texto", "2015-01-01", "11:00", "");
        evento.setTime("1100");
    }
}
