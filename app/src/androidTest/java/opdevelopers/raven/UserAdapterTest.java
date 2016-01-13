package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class UserAdapterTest extends InstrumentationTestCase {
    public static boolean peticionCreacionAceptada = false;
    public static boolean peticionInicioSesionAceptada = false;
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

        final UserAdapter adaptadorUsuarios1 = new UserAdapter(Constants.CREATE_USER, true) {

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
                    UserAdapterTest.peticionCreacionAceptada = adaptadorUsuarios1.enviarPeticionRegistrar(usuario);
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

        final UserAdapter adaptadorUsuarios2 = new UserAdapter(Constants.CREATE_USER, false) {

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
                    UserAdapterTest.peticionInicioSesionAceptada = adaptadorUsuarios2.enviarPeticionSesion(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        assertTrue(peticionInicioSesionAceptada);
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


    /**
     * Debe de borrar un usuario
     *
     * @throws Throwable
     */
    public void deleteUser() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);

        final UserAdapter adaptadorUsuarios3 = new UserAdapter(Constants.DELETE_USER, true) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                UserAdapterTest.peticionBorrarAceptada = adaptadorUsuarios3
                        .enviarPeticionBorrar("jjj@jjj.com", "prueba");
            }
        });

        assertTrue(peticionBorrarAceptada);
    }
}