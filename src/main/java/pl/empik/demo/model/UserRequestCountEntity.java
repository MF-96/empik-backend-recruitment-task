package pl.empik.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_REQUEST_COUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestCountEntity {

  @Id
  @Column(name = "LOGIN")
  private String login;

  @Column(name = "REQUEST_COUNT")
  private Long requestCount;

}
