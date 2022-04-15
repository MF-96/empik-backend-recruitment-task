package pl.empik.demo.service.github;

import pl.empik.demo.dto.*;

public interface GithubApiService {

  GithubUser getGithubUser(String login);
}
