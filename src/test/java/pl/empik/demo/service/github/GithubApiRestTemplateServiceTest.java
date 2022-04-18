package pl.empik.demo.service.github;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GithubApiRestTemplateServiceTest {

  @Mock
  private GithubApiRestTemplateService githubApiRestTemplateService;

  @Test
  public void testGetGithubUserInvoke() {
    githubApiRestTemplateService.getGithubUser("test");

    verify(githubApiRestTemplateService, times(1)).getGithubUser("test");
  }

}