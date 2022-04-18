package pl.empik.demo.repository;

import org.springframework.data.jpa.repository.*;
import pl.empik.demo.model.*;

public interface UserRequestCountRepository extends JpaRepository<UserRequestCountEntity, String> {
}
