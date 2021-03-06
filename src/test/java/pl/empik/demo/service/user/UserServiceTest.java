package pl.empik.demo.service.user;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit.jupiter.*;
import pl.empik.demo.*;
import pl.empik.demo.dto.user.*;
import pl.empik.demo.exception.*;
import pl.empik.demo.factory.user.*;
import pl.empik.demo.repository.*;
import pl.empik.demo.service.github.*;

import java.time.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class UserServiceTest {

  @Mock
  private GithubApiService githubApiService;

  @Mock
  private UserRequestCountService userRequestCountService;

  @Autowired
  private UserRequestCountRepository userRequestCountRepository;

  private UserService userService;

  @BeforeAll
  public void setup() {
    UserDataFactory userDataFactory = new UserDataFactory();
    this.userService = new UserService(githubApiService, userDataFactory, userRequestCountService);
  }

  @AfterEach
  public void resetInvocations() {
    Mockito.clearInvocations(userRequestCountService);
  }

  @Test
  public void testGetUserData() {
    when(githubApiService.getGithubUser(any(String.class))).thenReturn(UtilsTest.buildTestGithubUserDTO(1, 10));

    UserDataDTO userData = userService.getUserData("test");

    assertThat(userData).isNotNull();
    assertThat(userData).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactly(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), 72.0
            );

    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataNullFollowers() {
    when(githubApiService.getGithubUser(any(String.class))).thenReturn(UtilsTest.buildTestGithubUserDTO(null, 10));

    UserDataDTO userData = userService.getUserData("test");

    assertThat(userData).isNotNull();
    assertThat(userData).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactly(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataZeroFollowers() {
    when(githubApiService.getGithubUser(any(String.class))).thenReturn(UtilsTest.buildTestGithubUserDTO(0, 10));

    UserDataDTO userData = userService.getUserData("test");

    assertThat(userData).isNotNull();
    assertThat(userData).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactly(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataNullPublicRepos() {
    when(githubApiService.getGithubUser(any(String.class))).thenReturn(UtilsTest.buildTestGithubUserDTO(1, null));

    UserDataDTO userData = userService.getUserData("test");

    assertThat(userData).isNotNull();
    assertThat(userData).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactly(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), null
            );
    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataZeroPublicRepos() {
    when(githubApiService.getGithubUser(any(String.class))).thenReturn(UtilsTest.buildTestGithubUserDTO(1, 0));

    UserDataDTO userData = userService.getUserData("test");

    assertThat(userData).isNotNull();
    assertThat(userData).extracting(UserDataDTO::getId, UserDataDTO::getName, UserDataDTO::getType,
                    UserDataDTO::getAvatarUrl, UserDataDTO::getCreatedAt, UserDataDTO::getCalculations)
            .containsExactly(
                    1L, "GITHUB TEST NAME", "TEST TYPE", "TEST URL",
                    LocalDateTime.of(2022, 1, 1, 12, 0, 0), 12.0
            );
    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataNullLogin() {
    InputValidationException exception = assertThrows(InputValidationException.class, () -> userService.getUserData(null));

    assertThat(exception.getMessage()).isEqualTo("Login cannot be empty");
    verify(userRequestCountService, times(0)).updateLoginSearch("test");
  }

  @Test
  public void testGetUserDataEmptyLogin() {
    InputValidationException exception = assertThrows(InputValidationException.class, () -> userService.getUserData(""));

    assertThat(exception.getMessage()).isEqualTo("Login cannot be empty");
    verify(userRequestCountService, times(0)).updateLoginSearch("");
  }

  @Test
  public void testGetUserConnectionError() {
    when(githubApiService.getGithubUser("test")).thenThrow(ExternalConnectionException.class);

    assertThrows(ExternalConnectionException.class, () -> userService.getUserData("test"));
    verify(userRequestCountService, times(1)).updateLoginSearch("test");
  }

}