package opdevelopers.raven;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;


/**
 * Created by agustin on 25/11/15.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAdapterTest {
    @Test
    public void aCreateUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, true);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        adapter.TEST = true;
        assertEquals(true, adapter.enviarPeticionRegistrar(user));
    }
    @Test
    public void bCantcreateUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, true);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        adapter.TEST = true;
        assertEquals(false, adapter.enviarPeticionRegistrar(user));
    }
    @Test
    public void cLogin() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.CREATE_USER, false);
        User user = new User("dummy", "dummy", "dummy@dummy.com", "2010", "999999999", "dummy",
                "dummy","dummy", "dummy", "dummy", "999999999");
        adapter.TEST = true;
        assertEquals(true, adapter.enviarPeticionSesion(user));
    }
    @Test(expected = ErrorException.class)
    public void dFetchUser() throws ErrorException{
        UserAdapter adapter = new UserAdapter(Constants.FETCH_USER, false, "dummy");
        User user = new User("dummy", "dummy", "dummy@dummy.com", "1-01-2010", "1111111111", "dummy",
                "dummy","dummy", "dummy", "dummy", "dummy");
        adapter.TEST = true;
        assertEquals(user, adapter.peticionFetchUsuario());
    }
}
