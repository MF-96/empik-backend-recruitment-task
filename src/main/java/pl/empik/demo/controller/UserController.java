package pl.empik.demo.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import pl.empik.demo.dto.user.*;
import pl.empik.demo.service.user.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{login}")
  public UserDataDTO getUser(@PathVariable String login) {
    return userService.getUserData(login);
  }
}
