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

    @Test
    public void testCorreoContrasenyaCorrecto() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        User prueba = new User("prueba@test.com", "password");
        prueba.setEmail("correo@example.com");
        prueba.setContrasenya("contrasenya");
        assertEquals(usuario.toString(), prueba.toString());
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

    @Test
    public void testUsuarioCorrecto1() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        User prueba = new User("Name", "Surname", "prueba@test.com", "1989", "976012345",
                "Enfermo", "Teruel", "test", "Name2", "Surname2", "678123456");
        prueba.setNombre("Nombre");
        prueba.setApellido("Apellido");
        prueba.setEmail("correo@example.com");
        prueba.setAnyoNacimiento("1994");
        prueba.setTelefono("976543210");
        prueba.setInfoMedica("Sano");
        prueba.setResidencia("Zaragoza");
        prueba.setContrasenya("prueba");
        prueba.setNombreContacto("NombreContacto");
        prueba.setApellidoContacto("ApellidoContacto");
        prueba.setTelefonoContacto("678654321");
        assertEquals(usuario.toString(), prueba.toString());
    }

    @Test
    public void testUsuarioCorrecto2() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        User prueba = new User(usuario.getNombre(), usuario.getApellido(), usuario.getEmail(),
                usuario.getAnyoNacimiento(), usuario.getTelefono(), usuario.getInfoMedica(),
                usuario.getResidencia(), usuario.getContrasenya(), usuario.getNombreContacto(),
                usuario.getApellidoContacto(), usuario.getTelefonoContacto());
        assertEquals(usuario.toString(), prueba.toString());
    }

    @Test(expected=ErrorException.class)
    public void testSetCorreoNull() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setEmail(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetCorreoVacio() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setEmail("");
    }

    @Test(expected=ErrorException.class)
    public void testSetCorreoNoArroba() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setEmail("correoexample.com");
    }

    @Test(expected=ErrorException.class)
    public void testSetCorreoNoPunto() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setEmail("correo@examplecom");
    }

    @Test(expected=ErrorException.class)
    public void testSetContrasenyaNull() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setContrasenya(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetContrasenyaVacio() throws ErrorException {
        User usuario = new User("correo@example.com", "contrasenya");
        usuario.setContrasenya("");
    }

    @Test(expected=ErrorException.class)
    public void testSetNombreNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setNombre(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetNombreVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setNombre("");
    }

    @Test(expected=ErrorException.class)
    public void testSetApellidoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setApellido(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetApellidoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setApellido("");
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setEmail(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setEmail("");
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNoArroba() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setEmail("correoexample.com");
    }

    @Test(expected=ErrorException.class)
    public void testSetEmailNoPunto() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setEmail("correo@examplecom");
    }

    @Test(expected=ErrorException.class)
    public void testSetAnyoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setAnyoNacimiento(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetAnyoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setAnyoNacimiento("");
    }

    @Test(expected=ErrorException.class)
    public void testSetAnyoCuatroCifras() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setAnyoNacimiento("19994");
    }

    @Test(expected=ErrorException.class)
    public void testSetAnyoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setAnyoNacimiento("-1994");
    }

    @Test(expected=ErrorException.class)
    public void testSetAnyoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setAnyoNacimiento("Dos mil quince");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefono(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefono("");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefono("-976543210");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefono("Nueve siete seis");
    }

    @Test(expected=ErrorException.class)
    public void testSetInfoMedicaNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setInfoMedica(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetInfoMedicaVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setInfoMedica("");
    }

    @Test(expected=ErrorException.class)
    public void testSetResidenciaNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setResidencia(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetResidenciaVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setResidencia("");
    }

    @Test(expected=ErrorException.class)
    public void testSetPasswordNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setContrasenya(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetPasswordVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setContrasenya("");
    }

    @Test(expected=ErrorException.class)
    public void testSetNombreContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setNombreContacto(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetNombreContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setNombreContacto("");
    }

    @Test(expected=ErrorException.class)
    public void testSetApellidoContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setApellido(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetApellidoContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setApellido("");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoContactoNull() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefonoContacto(null);
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoContactoVacio() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefonoContacto("");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoContactoPositivo() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefonoContacto("-678654321");
    }

    @Test(expected=ErrorException.class)
    public void testSetTelefonoContactoEntero() throws ErrorException {
        User usuario = new User("Nombre", "Apellido", "correo@example.com", "1994", "976543210",
                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
        usuario.setTelefonoContacto("Seis siete ocho");
    }
}
