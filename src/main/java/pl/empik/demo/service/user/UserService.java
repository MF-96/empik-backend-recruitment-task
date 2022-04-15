package pl.empik.demo.service.user;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import pl.empik.demo.dto.*;
import pl.empik.demo.exception.*;
import pl.empik.demo.service.github.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

  private final GithubApiService githubApiService;

  public GithubUser getUserData(String login) {
    log.info("Getting user data for login: {}", login);
    validateLogin(login);
    return githubApiService.getGithubUser(login);
  }

  private void validateLogin(String login) {
    log.info("Validating login: {}", login);
    if (!StringUtils.hasText(login)) {
      throw new InputValidationException("Login cannot be empty");
    }
  }
}
