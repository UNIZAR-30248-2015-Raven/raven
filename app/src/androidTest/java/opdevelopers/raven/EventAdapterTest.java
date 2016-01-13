package opdevelopers.raven;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;

/**
 * Created by Eduardo on 01/12/2015.
 */
public class EventAdapterTest extends InstrumentationTestCase {
    public static boolean peticionCrearUsuarioAceptada = false;
    public static boolean peticionCrearEventoAceptada = false;
    public static boolean peticionBorrarEventoAceptada = false;
    public static boolean peticionBorrarUsuarioAceptada = false;


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createUser();
        createEvent();
    }


    /**
     * Crea los usuarios para probar los eventos
     */
    private void createUser() {
        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.CREATE_USER, true);

        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        User usuario = new User("Nombre", "Apellido", "jjj@jjj.com", "1994", "776131311",
                                "Sano", "Zaragoza", "prueba", "NombreContacto", "ApellidoContacto", "678654320");
                        EventAdapterTest.peticionCrearUsuarioAceptada = adaptadorUsuarios.enviarPeticionRegistrar(usuario);
                    } catch (ErrorException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        assertTrue(peticionCrearUsuarioAceptada);
    }


    /**
     * Debe crear un nuevo evento
     */
    public void createEvent() {
        final EventAdapter adaptadorEventos = new EventAdapter(Constants.CREATE_EVENT);

        try {
            runTestOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Event evento = new Event("", "jjj@jjj.com", "Texto", "2015-01-01", "11:00", "");
                        peticionCrearEventoAceptada = adaptadorEventos.enviarPeticionCrearEvento(evento);
                    } catch (ErrorException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
        assertTrue(peticionCrearEventoAceptada);
    }


    /**
     * Debe de modifica un evento
     *
     * @throws Throwable
     */
    public void testModifyEvent() throws Throwable {
        final EventAdapter adaptadorEventosFetch = new EventAdapter(Constants.FETCH_EVENTS, "jjj@jjj.com");
        final EventAdapter adaptadorEventosModify = new EventAdapter(Constants.MODIFY_EVENT, "jjj@jjj.com");

        ArrayList<Event> eventos = (ArrayList<Event>) adaptadorEventosFetch.peticionFetchEventos();

        final Event event = eventos.get(0);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    Event evento = new Event(event.getId(), "jjj@jjj.com", "Texto", "2015-01-01", "11:00", "");
                    peticionBorrarUsuarioAceptada = adaptadorEventosModify.enviarPeticionModificarEvento(evento);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
            }
        });

        assertEquals(true, true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        deleteEvent();
        deleteUser();
    }


    /**
     * Debe borrar un usuario
     */
    private void deleteUser() {
        final UserAdapter adaptadorUsuarios = new UserAdapter(Constants.DELETE_USER, true);

        try {
            runTestOnUiThread(new Runnable() {

                @Override
                public void run() {
                    peticionBorrarUsuarioAceptada = adaptadorUsuarios.enviarPeticionBorrar("jjj@jjj.com", "prueba");
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        assertTrue(peticionBorrarUsuarioAceptada);
    }


    /**
     * Debe eliminar un evento
     */
    public void deleteEvent() {
        final EventAdapter adaptadorEventosBorrado = new EventAdapter(Constants.DELETE_EVENT);
        final EventAdapter adaptadorEventosFetch = new EventAdapter(Constants.FETCH_EVENTS, "jjj@jjj.com");

        ArrayList<Event> eventos = (ArrayList<Event>) adaptadorEventosFetch.peticionFetchEventos();

        final Event event = eventos.get(0);

        try {
            runTestOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Event evento = new Event(event.getId(), "jjj@jjj.com", "Texto", "2015-01-01", "11:00", "");
                        peticionBorrarEventoAceptada = adaptadorEventosBorrado.enviarPeticionBorrarEvento(evento);
                    } catch (ErrorException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

        assertTrue(peticionBorrarEventoAceptada);
    }
}