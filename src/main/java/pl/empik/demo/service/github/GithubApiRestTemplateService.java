package pl.empik.demo.service.github;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import pl.empik.demo.dto.github.*;
import pl.empik.demo.exception.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubApiRestTemplateService implements GithubApiService {

  @Value("${github-api.base-url}")
  private String githubApiBaseURL;

  @Value("${github-api.users-endpoint}")
  private String githubApiUsersEndpoint;

  private final RestTemplate restTemplate;

  public GithubUserDTO getGithubUser(String login) {
    return get(String.format("%s/%s/%s", githubApiBaseURL, githubApiUsersEndpoint, login), GithubUserDTO.class);
  }

  private <T> T get(String url, Class<T> clazz) {
    try {
      return restTemplate.getForObject(url, clazz);
    } catch (RestClientException exception) {
      log.error("Error during request to {}: {}", url, exception.getMessage());
      throw new ExternalConnectionException(String.format("Error during request to %s: %s", url, exception.getMessage()));
    }
  }
}
