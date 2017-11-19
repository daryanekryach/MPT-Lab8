package githubapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class that provides parsing(or deserialization) of JSON data.
 */
public class JSONParser {

    /**
     * Method that parses JSON with repository info from request.
     * @param repoJson - JSON info of repository.
     * @return ArrayList<Repository> with parsed repositories.
     */
    public ArrayList<Repository> parseRepositoryJson(String repoJson) {
        JSONObject object = new JSONObject(repoJson);
        JSONArray items = object.getJSONArray("items");
        ArrayList<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            Repository repository = new Repository();
            JSONObject repositoryObject = items.getJSONObject(i);
            repository.setName(repositoryObject.getString("name"));
            repository.setDescription(repositoryObject.getString("description"));
            if (!(repositoryObject.get("language") instanceof String))
                repository.setLanguage("none");
            else repository.setLanguage(repositoryObject.getString("language"));
            repository.setStars(repositoryObject.getInt("stargazers_count"));
            JSONObject ownerObject = repositoryObject.getJSONObject("owner");
            repository.setOwner(ownerObject.getString("login"));
            repositories.add(repository);
        }
        return repositories;
    }

    /**
     * Method that parses JSON with contribution info from request.
     * @param json - JSON info of contribution.
     * @return ArrayList<Contributor> with parsed contributors.
     */
    public ArrayList<Contributor> parseRepositoryContributionJson(String json) {
        JSONArray object = new JSONArray(json);
        ArrayList<Contributor> contributors = new ArrayList<>();
        for (int i = 0; i < object.length(); i++) {
            Contributor contributor = new Contributor();
            JSONObject contributionObject = object.getJSONObject(i);
            contributor.setUser(contributionObject.getString("login"));
            contributor.setContributionCount(contributionObject.getInt("contributions"));
            contributors.add(contributor);
        }
        return contributors;
    }


}
