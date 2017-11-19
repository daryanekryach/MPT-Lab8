package testing;

import org.junit.*;
import githubapi.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class JSONParserTest {
    private JSONParser parser;
    private String repositoryJSON;
    private String contributionJSON;

    @Before
    public void init() {
        parser = new JSONParser();
        repositoryJSON = contributionJSON = "";
        try (Stream<String> stream = Files.lines(Paths.get("testRepoJSON.txt"))) {
            stream.forEach(line -> repositoryJSON += line);
        } catch (Exception e) {
            System.out.println("Problem occurred while reading your file : " + e);
        }
        try (Stream<String> stream = Files.lines(Paths.get("testContributionJSON.txt"))) {
            stream.forEach(line -> contributionJSON += line);
        } catch (Exception e) {
            System.out.println("Problem occurred while reading your file : " + e);
        }
    }

    @Test
    public void parseRepositoryJsonTest() {
        ArrayList<Repository> repositories = new ArrayList<>();
        Repository repository = new Repository();
        repository.setName("test");
        repository.setOwner("test");
        repository.setStars(1);
        repository.setDescription("test");
        repository.setLanguage("rest");
        repositories.add(repository);
        assertEquals(repositories.get(0).getName(), parser.parseRepositoryJson(repositoryJSON).get(0).getName());
    }

    @Test
    public void parseRepositoryContributionJsonTest() {
        ArrayList<Contributor> contributors = new ArrayList<>();
        Contributor contributor = new Contributor();
        contributor.setUser("test");
        contributor.setContributionCount(1);
        contributors.add(contributor);
        assertEquals(contributors.get(0).getUser(), parser.parseRepositoryContributionJson(contributionJSON)
                .get(0).getUser());
    }
}
