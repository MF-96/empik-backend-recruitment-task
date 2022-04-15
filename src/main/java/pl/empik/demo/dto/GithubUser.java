package pl.empik.demo.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GithubUser {

  @JsonProperty("login")
  private String login;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("node_id")
  private String nodeId;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("gravatar_id")
  private String gravatar_id;

  @JsonProperty("url")
  private String url;

  @JsonProperty("html_url")
  private String html_url;

  @JsonProperty("followers_url")
  private String followers_url;

  @JsonProperty("following_url")
  private String following_url;

  @JsonProperty("gists_url")
  private String gists_url;

  @JsonProperty("starred_url")
  private String starred_url;

  @JsonProperty("subscriptions_url")
  private String subscriptions_url;

  @JsonProperty("organizations_url")
  private String organizations_url;

  @JsonProperty("repos_url")
  private String repos_url;

  @JsonProperty("events_url")
  private String events_url;

  @JsonProperty("received_events_url")
  private String received_events_url;

  @JsonProperty("type")
  private String type;

  @JsonProperty("site_admin")
  private Boolean site_admin;

  @JsonProperty("name")
  private String name;

  @JsonProperty("company")
  private String company;

  @JsonProperty("blog")
  private String blog;

  @JsonProperty("location")
  private String location;

  @JsonProperty("email")
  private String email;

  @JsonProperty("hireable")
  private String hireable;

  @JsonProperty("bio")
  private String bio;

  @JsonProperty("twitter_username")
  private String twitter_username;

  @JsonProperty("public_repos")
  private Integer public_repos;

  @JsonProperty("public_gists")
  private Integer public_gists;

  @JsonProperty("followers")
  private Integer followers;

  @JsonProperty("following")
  private Integer following;

  @JsonProperty("created_at")
  private LocalDateTime created_at;

  @JsonProperty("updated_at")
  private LocalDateTime updated_at;
}
