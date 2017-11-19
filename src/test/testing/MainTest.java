package testing;

import githubapi.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.*;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void exceptionThrowForInstanceTest() throws IllegalAccessException, InstantiationException {
        final Class<?> mainClass = Main.class;
        final Constructor<?> c = mainClass.getDeclaredConstructors()[0];
        c.setAccessible(true);

        Throwable targetException = null;
        try {
            c.newInstance((Object[]) null);
        } catch (InvocationTargetException ite) {
            targetException = ite.getTargetException();
        }
        assertNotNull(targetException);
        assertEquals(targetException.getClass(), InstantiationException.class);
    }

    @Test
    public void getGitHubRepositoriesInfoTest() throws IOException {
        assertTrue(Main.getGitHubRepositoriesInfo());
    }
}
