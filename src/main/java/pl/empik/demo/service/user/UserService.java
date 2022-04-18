package pl.empik.demo.service.user;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import pl.empik.demo.dto.github.*;
import pl.empik.demo.dto.user.*;
import pl.empik.demo.exception.*;
import pl.empik.demo.factory.user.*;
import pl.empik.demo.service.github.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

  private final GithubApiService githubApiService;
  private final UserDataFactory userDataFactory;

  public UserDataDTO getUserData(String login) {
    log.info("Getting user data for login: {}", login);
    validateLogin(login);
    GithubUserDTO githubUser = githubApiService.getGithubUser(login);
    return userDataFactory.toUserData(githubUser);
  }


  private void validateLogin(String login) {
    log.info("Validating login: {}", login);
    if (!StringUtils.hasText(login)) {
      throw new InputValidationException("Login cannot be empty");
    }
  }
}
