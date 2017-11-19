package testing;

import org.junit.*;
import githubapi.*;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class ContributorTest {

    @Test
    public void getUser() throws NoSuchFieldException, IllegalAccessException {
        final Contributor contributor = new Contributor();
        final Field field = contributor.getClass().getDeclaredField("user");
        String login = "test";
        field.setAccessible(true);
        field.set(contributor, login);
        assertEquals(contributor.getUser(), login);
    }

    @Test
    public void setUser() throws NoSuchFieldException, IllegalAccessException {
        final Contributor contributor = new Contributor();
        String login = "test";
        contributor.setUser(login);
        final Field field = contributor.getClass().getDeclaredField("user");
        field.setAccessible(true);
        assertEquals(field.get(contributor), login);
    }

    @Test
    public void getContributionCount() throws NoSuchFieldException, IllegalAccessException {
        final Contributor contributor = new Contributor();
        final Field field = contributor.getClass().getDeclaredField("contributionCount");
        field.setAccessible(true);
        int contributionCount = 1;
        field.set(contributor, contributionCount);
        assertEquals(contributor.getContributionCount(), contributionCount);
    }

    @Test
    public void setContributionCount() throws NoSuchFieldException, IllegalAccessException {
        final Contributor contributor = new Contributor();
        int contributionCount = 1;
        contributor.setContributionCount(contributionCount);
        final Field field = contributor.getClass().getDeclaredField("contributionCount");
        field.setAccessible(true);
        assertEquals(field.get(contributor), contributionCount);
    }

}
