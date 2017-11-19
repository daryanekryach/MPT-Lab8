package githubapi;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.*;
import java.util.Comparator;

/**
 * Class for getting data of GitHub repositories with GitHub API and HTTPClient.
 */
public class Github {
    private CloseableHttpClient httpclient;
    private ArrayList<Repository> mostStarredRepositories;
    private ArrayList<Repository> mostCommittedRepositories;
    private JSONParser parser;
    private final String TOKEN = "9129bb1665168c2dbb12a04c6a2074efb181c630";

    public Github() {
        httpclient = HttpClients.createDefault();
        mostCommittedRepositories = new ArrayList<>();
        mostStarredRepositories = new ArrayList<>();
        parser = new JSONParser();
    }

    /**
     * Method executes request for getting most starred repositories.
     * @return CloseableHttpResponse response
     * @throws IOException
     */
    private CloseableHttpResponse requestMostStarred() throws IOException {
        URIBuilder URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPath("/search/repositories")
                .addParameter("q", "stars:>1")
                .addParameter("sort", "stars")
                .addParameter("order", "desc")
                .addParameter("per_page", "10");
        HttpGet httpGet = new HttpGet(URI.toString());
        httpGet.addHeader("Authorization", "token " + TOKEN);
        return httpclient.execute(httpGet);
    }

    /**
     * Method that reads response the from most starred request into Repository instance.
     * @param responseRepository - request response
     * @return ArrayList<Repository> with parsed repositories
     * @throws IOException
     */
    private ArrayList<Repository> getMostStarred(CloseableHttpResponse responseRepository)
            throws IOException {
        try {
            BufferedReader brRepository = new BufferedReader(new InputStreamReader(responseRepository
                    .getEntity().getContent()));
            String repositoryData = brRepository.readLine();
            mostStarredRepositories = parser.parseRepositoryJson(repositoryData);
            for (int i = 0; i < mostStarredRepositories.size(); i++) {
                getRepositoryCommits(requestRepositoryCommits(mostStarredRepositories.get(i)));
                mostStarredRepositories.get(i).
                        setContributors(getRepositoryCommits(requestRepositoryCommits(mostStarredRepositories.get(i))));
            }
        } finally {
            responseRepository.close();
        }
        return mostStarredRepositories;
    }

    /**
     * Method that prints info for each starred repository.
     */
    private void printMostStarred() {
        System.out.println("***************MOST STARRED REPOSITORIES***************");
        for (Repository repo : mostStarredRepositories) {
            System.out.println("\n#" + (mostStarredRepositories.indexOf(repo) + 1));
            System.out.println("NAME: " + repo.getName());
            System.out.println("OWNER: " + repo.getOwner());
            System.out.println("DESCRIPTION: " + repo.getDescription());
            System.out.println("LANGUAGE: " + repo.getLanguage());
            System.out.println("STARS: " + repo.getStars());
            System.out.println("USERS WITH MOST COMMITS:");
            for (int i = 0; i < 5; i++) {
                System.out.println("\t---> " + repo.getContributors().get(i).getContributionCount() +
                        " by " + repo.getContributors().get(i).getUser());
            }
        }
    }

    /**
     * Method executes request for getting repository commits.
     *
     * @param repository
     * @return
     * @throws IOException
     */
    private CloseableHttpResponse requestRepositoryCommits(Repository repository)
            throws IOException {
        URIBuilder URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPath("/repos/" + repository.getOwner() + "/" + repository.getName() + "/contributors")
                .addParameter("per_page", "100");
        HttpGet httpGet = new HttpGet(URI.toString());
        httpGet.addHeader("Authorization", "token " + TOKEN);
        return httpclient.execute(httpGet);
    }

    /**
     * Method that reads the response from request into Contributor instance.
     * @param response - request response
     * @return ArrayList<Contributor> with parsed contributors.
     * @throws IOException
     */
    private ArrayList<Contributor> getRepositoryCommits(CloseableHttpResponse response)
            throws IOException {
        ArrayList<Contributor> contributors;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            String data = br.readLine();
            contributors = parser.parseRepositoryContributionJson(data);
        } finally {
            response.close();
        }
        return contributors;
    }

    /**
     * Method that reads response from the most committed request into Repository instance.
     * @param response - request response
     * @return ArrayList<Repository> with parsed repositories
     * @throws IOException
     */
    private ArrayList<Repository> getMostCommitted(CloseableHttpResponse response) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            String repositoryData = br.readLine();
            ArrayList<Repository> allRepos = parser.parseRepositoryJson(repositoryData);
            for (int i = 0; i < allRepos.size(); i++) {
                getRepositoryCommits(requestRepositoryCommits(allRepos.get(i)));
                allRepos.get(i).
                        setContributors(getRepositoryCommits(requestRepositoryCommits(allRepos.get(i))));
            }
            for (int i = 0; i < allRepos.size(); i++) {
                for (Contributor contributor : allRepos.get(i).getContributors()) {
                    allRepos.get(i).addContribution(contributor.getContributionCount());
                }
            }
            allRepos.sort(Comparator.comparing(Repository::getContributionCount).reversed());
            for (int i = 0; i < 10; i++)
                mostCommittedRepositories.add(allRepos.get(i));
        } finally {
            response.close();
        }
        return mostStarredRepositories;
    }

    /**
     * Method executes request for getting most committed repositories.
     * @return CloseableHttpResponse response
     * @throws IOException
     */
    private CloseableHttpResponse requestMostCommitted(String dateFrom, String dateTo) throws IOException {
        URIBuilder URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPath("/search/repositories")
                .setParameter("q", "created:" + dateFrom + ".." + dateTo);
        HttpGet httpGet = new HttpGet(URI.toString());
        httpGet.addHeader("Authorization", "token " + TOKEN);
        httpGet.addHeader("Accept", "application/vnd.github.cloak-preview+json");
        return httpclient.execute(httpGet);
    }

    /**
     * Method that prints info for each most commited repository.
     */
    private void printMostCommitted() {
        System.out.println("\n***************MOST COMMITTED REPOSITORIES***************");
        for (Repository repo : mostCommittedRepositories) {
            System.out.println("\n#" + (mostCommittedRepositories.indexOf(repo) + 1));
            System.out.println("NAME: " + repo.getName());
            System.out.println("OWNER: " + repo.getOwner());
            System.out.println("DESCRIPTION: " + repo.getDescription());
            System.out.println("LANGUAGE: " + repo.getLanguage());
            System.out.println("NUMBER OF CONTRIBUTIONS: " + repo.getContributionCount());
            System.out.println("USERS WITH MOST COMMITS:");
            if (repo.getContributors().size() < 5) {
                for (int i = 0; i < repo.getContributors().size(); i++) {
                    System.out.println("\t---> " + repo.getContributors().get(i).getContributionCount() +
                            " by " + repo.getContributors().get(i).getUser());
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    System.out.println("\t---> " + repo.getContributors().get(i).getContributionCount() +
                            " by " + repo.getContributors().get(i).getUser());
                }
            }
        }
    }

    /**
     * Mwthod that executes other methods in order to get the most starred repositories.
     * @return
     * @throws IOException
     */
    public boolean getMostStarredRepositoriesData() throws IOException {
        getMostStarred(requestMostStarred());
        printMostStarred();
        return true;
    }

    /**
     * Method that executes other methods in order to get the most committed repositories.
     * @param dateFrom - start date
     * @param dateTo - end date
     * @return
     * @throws IOException
     */
    public boolean getMostCommittedRepositoriesData(String dateFrom, String dateTo) throws IOException {
        getMostCommitted(requestMostCommitted(dateFrom, dateTo));
        printMostCommitted();
        return true;
    }
}
