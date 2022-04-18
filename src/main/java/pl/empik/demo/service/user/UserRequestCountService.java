package pl.empik.demo.service.user;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import pl.empik.demo.exception.*;
import pl.empik.demo.model.*;
import pl.empik.demo.repository.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRequestCountService {

  private final UserRequestCountRepository userRequestCountRepository;

  @Transactional(noRollbackFor = ExternalConnectionException.class)
  public void updateLoginSearch(String login) {
    log.info("Updating login search for: {}", login);
    userRequestCountRepository.findById(login)
            .ifPresentOrElse(this::incrementLoginSearch, () -> insertNewLoginSearch(login));
  }

  private void incrementLoginSearch(UserRequestCountEntity userRequestCountEntity) {
    log.info("Incrementing login search for: {}", userRequestCountEntity.getLogin());
    userRequestCountEntity.setRequestCount(userRequestCountEntity.getRequestCount() + 1);
    userRequestCountRepository.save(userRequestCountEntity);
  }

  private void insertNewLoginSearch(String login) {
    log.info("Inserting new login search for: {}", login);
    UserRequestCountEntity userRequestCountEntity = UserRequestCountEntity.builder()
            .login(login)
            .requestCount(1L)
            .build();

    userRequestCountRepository.save(userRequestCountEntity);
  }
}
