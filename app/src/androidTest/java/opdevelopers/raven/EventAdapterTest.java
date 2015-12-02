package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class EventAdapterTest extends InstrumentationTestCase {

    public void testEventAdapterCreate() throws Throwable {
        final CountDownLatch signalUser = new CountDownLatch(1);
        final CountDownLatch signalEvent = new CountDownLatch(1);
        final boolean[] peticionUsuarioAceptada = {false};
        final boolean[] peticionEventoAceptada = {false};

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
                    User usuario = new User("Nombre", "Apellido", "example@example.com", "1994", "976543210",
                            "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654321");
                    peticionUsuarioAceptada[0] = adaptadorUsuarios.enviarPeticionRegistrar(usuario);

                }
                catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalUser.await(30, TimeUnit.SECONDS);

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
                    Event evento = new Event("", "example@example.com", "Texto", "2015-01-01", "11:00", "");
                    peticionEventoAceptada[0] = adaptadorEventos.enviarPeticionCrearEvento(evento);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        signalEvent.await(30, TimeUnit.SECONDS);

        assertEquals(peticionUsuarioAceptada[0], true);
        assertEquals(peticionEventoAceptada[0], true);
    }
}
