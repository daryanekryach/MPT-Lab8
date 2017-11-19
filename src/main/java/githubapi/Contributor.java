package githubapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contributor implements Comparable<Contributor>{
    private String user;
    private int contributionCount = 0;

    public int compareTo(Contributor commit)
    {
        return Integer.compare(commit.contributionCount,this.contributionCount);
    }
}
