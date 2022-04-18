package pl.empik.demo;

import pl.empik.demo.dto.github.*;
import pl.empik.demo.dto.user.*;

import java.time.*;

public class UtilsTest {

  public static GithubUserDTO buildTestGithubUserDTO(Integer followers, Integer publicRepos) {
    return GithubUserDTO.builder()
            .id(1L)
            .name("GITHUB TEST NAME")
            .type("TEST TYPE")
            .avatarUrl("TEST URL")
            .createdAt(LocalDateTime.of(2022, 1, 1, 12, 0, 0))
            .followers(followers)
            .publicRepos(publicRepos)
            .build();
  }

  public static UserDataDTO buildTestUserDataDTO() {
    return UserDataDTO.builder()
            .id(1L)
            .login("TEST")
            .name("GITHUB TEST NAME")
            .type("TEST TYPE")
            .avatarUrl("TEST URL")
            .createdAt(LocalDateTime.of(2022, 1, 1, 12, 30, 15))
            .calculations(70.0)
            .build();
  }
}
