package pl.empik.demo.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import pl.empik.demo.*;
import pl.empik.demo.exception.*;
import pl.empik.demo.service.user.*;

import java.time.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void testGetUserData() throws Exception {
    when(userService.getUserData("TEST")).thenReturn(UtilsTest.buildTestUserDataDTO());

    mockMvc.perform(get("/users/TEST"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.login").value("TEST"))
            .andExpect(jsonPath("$.name").value("GITHUB TEST NAME"))
            .andExpect(jsonPath("$.type").value("TEST TYPE"))
            .andExpect(jsonPath("$.avatarUrl").value("TEST URL"))
            .andExpect(jsonPath("$.createdAt").value(LocalDateTime.of(2022, 1, 1, 12, 30, 15).toString()))
            .andExpect(jsonPath("$.calculations").value(70));
  }

  @Test
  public void testGetUserDataEmptyLogin() throws Exception {
    when(userService.getUserData(" ")).thenThrow(InputValidationException.class);

    mockMvc.perform(get("/users/ "))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetUserDataExternalApiConnectionError() throws Exception {
    when(userService.getUserData("TEST")).thenThrow(ExternalConnectionException.class);

    mockMvc.perform(get("/users/TEST"))
            .andDo(print())
            .andExpect(status().isInternalServerError());
  }

}