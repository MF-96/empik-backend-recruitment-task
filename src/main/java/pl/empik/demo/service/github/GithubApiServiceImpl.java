package pl.empik.demo.service.github;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import pl.empik.demo.dto.*;
import pl.empik.demo.exception.*;

@Service
@AllArgsConstructor
@Slf4j
public class GithubApiServiceImpl implements GithubApiService {

  private final RestTemplate restTemplate;

  public GithubUser getGithubUser(String login) {
    try {
      return restTemplate.getForObject("https://api.github.com/users/" + login, GithubUser.class);
    } catch (RestClientException exception) {
      log.error("Error during request to github API /users/{login}: {}", exception.getMessage());
      throw new ExternalConnectionException(String.format("Error during request to github API /users/%s", login));
    }
  }
}
