package pl.empik.demo.factory.user;

import org.springframework.stereotype.*;
import pl.empik.demo.dto.github.*;
import pl.empik.demo.dto.user.*;

@Component
public class UserDataFactory {

  public UserDataDTO toUserData(GithubUserDTO githubUser) {
    return UserDataDTO.builder()
            .id(githubUser.getId())
            .login(githubUser.getLogin())
            .name(githubUser.getName())
            .type(githubUser.getType())
            .avatarUrl(githubUser.getAvatarUrl())
            .createdAt(githubUser.getCreatedAt())
            .calculations(getCalculations(githubUser))
            .build();
  }

  private Double getCalculations(GithubUserDTO githubUser) {
    if (validateCalculations(githubUser)) {
      return null;
    }
    return 6.0 / githubUser.getFollowers() * (2 + githubUser.getPublicRepos());
  }

  private boolean validateCalculations(GithubUserDTO githubUser) {
    return githubUser.getFollowers() == null || githubUser.getFollowers() == 0 || githubUser.getPublicRepos() == null;
  }
}
