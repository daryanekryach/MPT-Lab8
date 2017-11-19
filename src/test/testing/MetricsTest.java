package testing;
import org.junit.*;

import static org.junit.Assert.*;

import githubapi.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MetricsTest {

    @Test
    public void exceptionThrowForInstanceTest() throws IllegalAccessException, InstantiationException {
        final Class<?> metricsClass = Metrics.class;
        final Constructor<?> c = metricsClass.getDeclaredConstructors()[0];
        c.setAccessible(true);

        Throwable targetException = null;
        try {
            c.newInstance((Object[])null);
        } catch (InvocationTargetException ite) {
            targetException = ite.getTargetException();
        }
        assertNotNull(targetException);
        assertEquals(targetException.getClass(), InstantiationException.class);
    }

    @Test
    public void gatherMetricsTest(){
        assertTrue(Metrics.getAllMetrics());
    }

    @Test
    public void startTest(){
        assertTrue(Metrics.start());
    }

    @Test
    public void stopTest(){
        assertTrue(Metrics.stop());
    }
}
