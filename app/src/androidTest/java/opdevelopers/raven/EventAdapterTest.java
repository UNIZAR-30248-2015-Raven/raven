package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class EventAdapterTest extends InstrumentationTestCase {

    public static boolean peticionUsuarioAceptada = false;
    public static boolean peticionEventoAceptada = false;

    public void testEventAdapterCreate() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);
        final CountDownLatch signalEvent = new CountDownLatch(1);

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
                    User usuario = new User("Nombre", "Apellido", "ejemplo193@example.com", "1994", "276313138",
                            "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
                    peticionUsuarioAceptada = adaptadorUsuarios.enviarPeticionRegistrar(usuario);

                }
                catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser.await(35, TimeUnit.SECONDS);

        final EventAdapter adaptadorEventos = new EventAdapter(Constants.CREATE_EVENT) {

            @Override
            public void onPostExecute() {
                super.onPostExecute();
                signalEvent.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    Event evento = new Event("", "ejemplo193@example.com", "Texto", "2015-01-01", "11:00", "");
                    peticionEventoAceptada = adaptadorEventos.enviarPeticionCrearEvento(evento);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalEvent.await(35, TimeUnit.SECONDS);

        assertTrue(peticionUsuarioAceptada);
        assertTrue(peticionEventoAceptada);
    }
}
