package testing;

import org.junit.*;
import githubapi.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {
    private Contributor contributor;

    @Before
    public void init() {
        contributor = new Contributor();
        contributor.setUser("test");
    }

    @Test
    public void setId() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        int id = 1;
        repository.setId(id);
        final Field field = repository.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(repository), id);
    }

    @Test
    public void getId() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("id");
        field.setAccessible(true);
        int id = 1;
        field.set(repository, id);
        assertEquals(repository.getId(), id);
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String name = "test";
        field.set(repository, name);
        assertEquals(repository.getName(), name);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String name = "test";
        repository.setName(name);
        final Field field = repository.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(repository), name);
    }

    @Test
    public void getOwner() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("owner");
        field.setAccessible(true);
        String owner = "test";
        field.set(repository, owner);
        assertEquals(repository.getOwner(), owner);
    }

    @Test
    public void setOwner() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String owner = "test";
        repository.setOwner(owner);
        final Field field = repository.getClass().getDeclaredField("owner");
        field.setAccessible(true);
        assertEquals(field.get(repository), owner);
    }

    @Test
    public void getDescription() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("description");
        field.setAccessible(true);
        String description = "test";
        field.set(repository, description);
        assertEquals(repository.getDescription(), description);
    }

    @Test
    public void setDescription() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String description = "test";
        repository.setDescription(description);
        final Field field = repository.getClass().getDeclaredField("description");
        field.setAccessible(true);
        assertEquals(field.get(repository), description);
    }

    @Test
    public void getLanguage() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("language");
        field.setAccessible(true);
        String description = "test";
        field.set(repository, description);
        assertEquals(repository.getLanguage(), description);
    }

    @Test
    public void setLanguage() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String language = "test";
        repository.setLanguage(language);
        final Field field = repository.getClass().getDeclaredField("language");
        field.setAccessible(true);
        assertEquals(field.get(repository), language);
    }

    @Test
    public void getStars() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("stars");
        field.setAccessible(true);
        int stars = 1;
        field.set(repository, stars);
        assertEquals(repository.getStars(), stars);
    }

    @Test
    public void setStars() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        int stars = 1;
        repository.setStars(stars);
        final Field field = repository.getClass().getDeclaredField("stars");
        field.setAccessible(true);
        assertEquals(field.get(repository), stars);
    }

    @Test
    public void getContributors() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("contributors");
        field.setAccessible(true);
        ArrayList<Contributor> contributors = new ArrayList<>();
        contributors.add(contributor);
        field.set(repository, contributors);
        assertEquals(repository.getContributors(), contributors);
    }

    @Test
    public void setContributors() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        ArrayList<Contributor> contributors = new ArrayList<>();
        contributors.add(contributor);
        repository.setContributors(contributors);
        final Field field = repository.getClass().getDeclaredField("contributors");
        field.setAccessible(true);
        assertEquals(field.get(repository), contributors);
    }

    @Test
    public void addContribution() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("contributionCount");
        field.setAccessible(true);
        int contributionCount = 0;
        field.set(repository, contributionCount);
        repository.addContribution(1);
        assertEquals(1, repository.getContributionCount());
    }
}
