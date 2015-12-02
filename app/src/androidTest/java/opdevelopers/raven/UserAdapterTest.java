package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class UserAdapterTest extends InstrumentationTestCase {

    public void testUserAdapterLogin() throws Throwable {
        final CountDownLatch signalUser1 = new CountDownLatch(1);
        final CountDownLatch signalUser2 = new CountDownLatch(1);
        final boolean[] peticionUsuarioAceptada1 = {true};
        final boolean[] peticionUsuarioAceptada2 = {true};

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
                    User usuario = new User("Nombre", "Apellido", "email@example.com", "1994", "976543210",
                            "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
                    peticionUsuarioAceptada1[0] = adaptadorUsuarios1.enviarPeticionRegistrar(usuario);

                }
                catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser1.await(30, TimeUnit.SECONDS);

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
                    User usuario = new User("email@example.com", "prueba");
                    peticionUsuarioAceptada2[0] = adaptadorUsuarios2.enviarPeticionSesion(usuario);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser2.await(30, TimeUnit.SECONDS);

        assertEquals(peticionUsuarioAceptada1[0], false);
        assertEquals(peticionUsuarioAceptada2[0], true);
    }
}
