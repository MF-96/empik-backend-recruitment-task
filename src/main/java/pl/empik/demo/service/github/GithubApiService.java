package pl.empik.demo.service.github;

import pl.empik.demo.dto.github.*;

public interface GithubApiService {

  GithubUserDTO getGithubUser(String login);
}
