package pl.empik.demo.dto.user;

import lombok.*;

import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDataDTO {

  private Long id;
  private String login;
  private String name;
  private String type;
  private String avatarUrl;
  private LocalDateTime createdAt;
  private Double calculations;

}
