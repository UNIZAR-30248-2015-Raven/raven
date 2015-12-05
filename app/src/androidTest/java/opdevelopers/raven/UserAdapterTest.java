package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class UserAdapterTest extends InstrumentationTestCase {

    public static boolean peticionUsuarioAceptada1 = false;
    public static boolean peticionUsuarioAceptada2 = false;

    public void testUserAdapterLogin() throws Throwable {
        final CountDownLatch signalUser1 = new CountDownLatch(1);
        final CountDownLatch signalUser2 = new CountDownLatch(1);


        final UserAdapter adaptadorUsuarios1 = new UserAdapter(Constants.CREATE_USER, true) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser1.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    User usuario = new User("Nombre", "Apellido", "ejemplo1@example.com", "1994", "976797979",
                            "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
                    UserAdapterTest.peticionUsuarioAceptada1 = adaptadorUsuarios1.enviarPeticionRegistrar(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser1.await(35, TimeUnit.SECONDS);

        final UserAdapter adaptadorUsuarios2 = new UserAdapter(Constants.CREATE_USER, false) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalUser2.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    User usuario = new User("ejemplo1@example.com", "prueba");
                    UserAdapterTest.peticionUsuarioAceptada2 = adaptadorUsuarios2.enviarPeticionSesion(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser2.await(35, TimeUnit.SECONDS);

        assertTrue(peticionUsuarioAceptada1);
        assertTrue(peticionUsuarioAceptada2);
    }
}
