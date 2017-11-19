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
        github.getMostCommittedRepositoriesData("2017-05-05","2017-05-13");
        Metrics.stop();
        Metrics.getAllMetrics();
        return true;
    }
}