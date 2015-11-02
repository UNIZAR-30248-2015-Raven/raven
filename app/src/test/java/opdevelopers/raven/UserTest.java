package opdevelopers.raven;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 30/10/2015.
 */
public class UserTest {

    @Test(expected=ErrorException.class)
    public void testCorreoNull() throws ErrorException {
        User usuario = new User(null, "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testCorreoVacio() throws ErrorException {
        User usuario = new User("", "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testCorreoNoArroba() throws ErrorException {
        User usuario = new User("correoexample.com", "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testCorreoNoPunto() throws ErrorException {
        User usuario = new User("correo@examplecom", "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testContrasenyaNull() throws ErrorException {
        User usuario = new User("correo@example.com", null);
    }

    @Test(expected=ErrorException.class)
    public void testContrasenyaVacio() throws ErrorException {
        User usuario = new User("correo@example.com", "");
    }

    @Test(expected=ErrorException.class)
    public void testNombreNull() throws ErrorException {
        User usuario = new User(null, "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testNombreVacio() throws ErrorException {
        User usuario = new User("", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testApellidoNull() throws ErrorException {
        User usuario = new User("Nombre", null, "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testApellidoVacio() throws ErrorException {
        User usuario = new User("Nombre", "", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", null, "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testEmailVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoArroba() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correoexample.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoPunto() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@examplecom", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testAnyoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", null, "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testAnyoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testAnyoCuatroCifras() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "20015", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testAnyoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "-168", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testAnyoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "Doce", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", null,
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "-976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "Nueve siete seis",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testInfoMedicaNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                null, "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testInfoMedicaVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testResidenciaNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", null, "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testResidenciaVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testPasswordNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", null, "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testPasswordVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "", "NombreContacto", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testNombreContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", null, "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testNombreContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "", "ApellidoContacto", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testApellidoContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", null, "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testApellidoContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "", "678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", null);
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoContactoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "-678654321");
    }

    @Test(expected=ErrorException.class)
    public void testTelefonoContactoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "Seis siete ocho");
    }
}
