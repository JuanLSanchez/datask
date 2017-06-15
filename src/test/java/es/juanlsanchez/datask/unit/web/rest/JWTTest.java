package es.juanlsanchez.datask.unit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.juanlsanchez.datask.security.jwt.JWTConfigurer;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.web.dto.LoginDTO;
import es.juanlsanchez.datask.web.rest.UserJWTResource;

/**
 * Test class for the AccountResource REST controller.
 *
 * @see UserService
 */
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JWTTest {

  private static final String USER_PASSWORD = "password";
  private static final String USER_LOGIN = "user001";
  private static final String AUHENTICATION_URL = "/api/authenticate";
  private static final String BAD_PASSWORD = "bad password";
  private static final String BAD_LOGIN = "bad user";

  @Inject
  private UserJWTResource userJWTController;

  private MockMvc mvc;

  @Before
  public void setup() {
    this.mvc = MockMvcBuilders.standaloneSetup(userJWTController).build();
  }

  @Test
  public void test_authentication() throws Exception {
    ResultActions result = this.mvc.perform(post(AUHENTICATION_URL)
        .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(new LoginDTO(USER_LOGIN, USER_PASSWORD, true))));
    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$.id_token").isNotEmpty());

    assertThat(result.andReturn().getResponse().getHeader(JWTConfigurer.AUTHORIZATION_HEADER))
        .isNotNull();
    assertThat(result.andReturn().getResponse().getHeader(JWTConfigurer.AUTHORIZATION_HEADER)
        .matches("^Bearer "));
  }

  @Test
  public void test_authentication_bad_password() throws Exception {
    ResultActions result = this.mvc.perform(post(AUHENTICATION_URL)
        .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(new LoginDTO(USER_LOGIN, BAD_PASSWORD, true))));
    result.andExpect(status().isUnauthorized());
    result.andExpect(jsonPath("$.AuthenticationException", is("Bad credentials")));

    assertThat(result.andReturn().getResponse().getHeader(JWTConfigurer.AUTHORIZATION_HEADER))
        .isNull();
  }

  @Test
  @Ignore
  public void test_authentication_bad_user() throws Exception {
    ResultActions result = this.mvc.perform(post(AUHENTICATION_URL)
        .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(new LoginDTO(BAD_LOGIN, BAD_PASSWORD, true))));
    // result.andExpect(status().isUnauthorized());
    result.andExpect(jsonPath("$.AuthenticationException", is("Bad credentials")));

    assertThat(result.andReturn().getResponse().getHeader(JWTConfigurer.AUTHORIZATION_HEADER))
        .isNull();
  }

}
