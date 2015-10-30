package opdevelopers.raven;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 30/10/2015.
 */
public class UserTest {

    @Test(expected=ErrorException.class)
    public void testEmailNull() throws ErrorException {
        User usuario = new User(null, "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testEmailVacio() throws ErrorException {
        User usuario = new User("", "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoArroba() throws ErrorException {
        User usuario = new User("correoexample.com", "contrasenya");
    }

    @Test(expected=ErrorException.class)
    public void testEmailNoPunto() throws ErrorException {
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

}
