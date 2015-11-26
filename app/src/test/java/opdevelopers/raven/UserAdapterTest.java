package opdevelopers.raven;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by agustin on 25/11/15.
 */
public class UserAdapterTest {
    @Test
    public void createUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, true);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        assertEquals(false, adapter.enviarPeticionRegistrar(user));
    }
    @Test
    public void cantcreateUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, true);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        assertEquals(true, adapter.enviarPeticionRegistrar(user));
    }
    @Test
    public void login() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, false);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        assertEquals(true, adapter.enviarPeticionSesion(user));
    }
    @Test(expected = ErrorException.class)
    public void fetchUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.FETCH_USER, false, "dummy");
        User user = new User("dummy", "dummy", "dummy@dummy.com", "1-01-2010", "1111111111", "dummy",
                "dummy","dummy", "dummy", "dummy", "dummy");
        assertEquals(user, adapter.peticionFetchUsuario());
    }
}
