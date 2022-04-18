package pl.empik.demo.factory.user;

import org.junit.jupiter.api.*;
import pl.empik.demo.*;
import pl.empik.demo.dto.github.*;
import pl.empik.demo.dto.user.*;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDataFactoryTest {

  private final UserDataFactory userDataFactory = new UserDataFactory();

  @Test
  public void testToUserData() {
    GithubUserDTO githubUserDTO = UtilsTest.buildTestGithubUserDTO(10, 20);

    UserDataDTO userDataDTO = userDataFactory.toUserData(githubUserDTO);

    assertThat(userDataDTO).isNotNull();
    assertThat(userDataDTO).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
            UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactlyInAnyOrder(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), 13.2
            );
  }

  @Test
  public void testToUserDataNoFollowers() {
    GithubUserDTO githubUserDTO = UtilsTest.buildTestGithubUserDTO(null, 20);

    UserDataDTO userDataDTO = userDataFactory.toUserData(githubUserDTO);

    assertThat(userDataDTO).isNotNull();
    assertThat(userDataDTO).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactlyInAnyOrder(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
  }

  @Test
  public void testToUserDataZeroFollowers() {
    GithubUserDTO githubUserDTO = UtilsTest.buildTestGithubUserDTO(0, 20);

    UserDataDTO userDataDTO = userDataFactory.toUserData(githubUserDTO);

    assertThat(userDataDTO).isNotNull();
    assertThat(userDataDTO).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactlyInAnyOrder(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
  }

  @Test
  public void testToUserDataNoPublicRepos() {
    GithubUserDTO githubUserDTO = UtilsTest.buildTestGithubUserDTO(10, null);

    UserDataDTO userDataDTO = userDataFactory.toUserData(githubUserDTO);

    assertThat(userDataDTO).isNotNull();
    assertThat(userDataDTO).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactlyInAnyOrder(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
  }

  @Test
  public void testToUserDataZeroPublicRepos() {
    GithubUserDTO githubUserDTO = UtilsTest.buildTestGithubUserDTO(10, 0);

    UserDataDTO userDataDTO = userDataFactory.toUserData(githubUserDTO);

    assertThat(userDataDTO).isNotNull();
    assertThat(userDataDTO).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactlyInAnyOrder(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), 1.2
            );
  }

}