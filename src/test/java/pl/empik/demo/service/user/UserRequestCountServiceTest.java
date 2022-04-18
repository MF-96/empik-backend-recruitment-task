package pl.empik.demo.service.user;

import lombok.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit.jupiter.*;
import pl.empik.demo.model.*;
import pl.empik.demo.repository.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRequestCountServiceTest {

  @Autowired
  private UserRequestCountRepository userRequestCountRepository;

  private UserRequestCountService userRequestCountService;

  @BeforeAll
  public void setup() {
    userRequestCountService = new UserRequestCountService(userRequestCountRepository);
  }

  @Test
  public void testInsertNewSearch() {
    userRequestCountService.updateLoginSearch("test");

    List<UserRequestCountEntity> userRequestCountEntityList = userRequestCountRepository.findAll();
    assertThat(userRequestCountEntityList).hasSize(1);

    UserRequestCountEntity userRequestCountEntity = userRequestCountEntityList.get(0);
    assertThat(userRequestCountEntity.getLogin()).isEqualTo("test");
    assertThat(userRequestCountEntity.getRequestCount()).isEqualTo(1L);
  }

  @Test
  public void testUpdateExistingSearch() {
    UserRequestCountEntity requestCountEntity = UserRequestCountEntity.builder().login("test").requestCount(10L).build();
    userRequestCountRepository.save(requestCountEntity);

    userRequestCountService.updateLoginSearch("test");
    userRequestCountService.updateLoginSearch("test");
    userRequestCountService.updateLoginSearch("test");

    List<UserRequestCountEntity> userRequestCountEntityList = userRequestCountRepository.findAll();
    assertThat(userRequestCountEntityList).hasSize(1);

    UserRequestCountEntity userRequestCountEntity = userRequestCountEntityList.get(0);
    assertThat(userRequestCountEntity.getLogin()).isEqualTo("test");
    assertThat(userRequestCountEntity.getRequestCount()).isEqualTo(13L);
  }

}