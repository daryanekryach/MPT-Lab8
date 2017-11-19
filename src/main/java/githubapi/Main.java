package githubapi;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
       getGitHubRepositoriesInfo();
    }

    public static boolean getGitHubRepositoriesInfo() throws IOException{
        Github github = new Github();
        Metrics.start();
        github.getMostStarredRepositoriesData();
        Metrics.stop();
        Metrics.getAllMetrics();
        Metrics.start();
        github.getMostCommittedRepositoriesData("2017-05-01","2017-05-07");
        Metrics.stop();
        Metrics.getAllMetrics();
        return true;
    }

    private Main() throws InstantiationException
    {
        throw new InstantiationException("Instances of this type are forbidden.");
    }
}
