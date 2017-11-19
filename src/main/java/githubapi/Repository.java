package githubapi;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Repository {
    private int id;
    private String name;
    private String owner;
    private String description;
    private String language;
    private int stars;
    private ArrayList<Contributor> contributors;
    private int contributionCount;

    public Repository() {
        contributors = new ArrayList<>();
    }

    public void addContribution(int contribution) {
        contributionCount += contribution;
    }
}
