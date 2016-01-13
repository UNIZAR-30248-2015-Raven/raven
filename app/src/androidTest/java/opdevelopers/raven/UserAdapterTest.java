package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class UserAdapterTest extends InstrumentationTestCase {
    public static boolean peticionCreacionAceptada = false;
    public static boolean peticionInicioSesionAceptada = false;
    public static boolean peticionModificarAceptada = false;
    public static boolean peticionBorrarAceptada = false;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        try {
            createUser();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    /**
     * Debe de crear un nuevo usuario
     *
     * @throws Throwable
     */
    public void createUser() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);

        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, true) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    User usuario = new User("Nombre", "Apellido", "jjj@jjj.com", "1994", "776131311",
                            "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654320");
                    UserAdapterTest.peticionCreacionAceptada = adaptadorUsuarios.enviarPeticionRegistrar(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });
        
        assertTrue(peticionCreacionAceptada);
    }


    /**
     * Debe iniciar sesión de un usuario
     *
     * @throws Throwable
     */
    public void testLoginUser() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);

        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, false) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    User usuario = new User("jjj@jjj.com", "prueba");
                    UserAdapterTest.peticionInicioSesionAceptada = adaptadorUsuarios.enviarPeticionSesion(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        assertTrue(peticionInicioSesionAceptada);
    }


    /**
     * Debe modificar un usuario
     *
     * @throws Throwable
     */
    public void testModifyUser() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);

        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.MODIFY_USER, false, "jjj@jjj.com") {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    User usuario = new User("Modificado", "Modificado", "jjj@jjj.com", "1994", "776131311",
                            "Modificado", "Modificado", "prueba", "Modificado", "Modificado", "678654320");
                    UserAdapterTest.peticionModificarAceptada = adaptadorUsuarios.enviarPeticionRegistrar(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        assertTrue(peticionModificarAceptada);
    }


    /**
     * Debe de borrar un usuario
     *
     * @throws Throwable
     */
    public void deleteUser() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);

        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.DELETE_USER, true) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                UserAdapterTest.peticionBorrarAceptada = adaptadorUsuarios
                        .enviarPeticionBorrar("jjj@jjj.com", "prueba");
            }
        });

        assertTrue(peticionBorrarAceptada);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        try {
            deleteUser();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}