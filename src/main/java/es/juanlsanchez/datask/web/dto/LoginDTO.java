package es.juanlsanchez.datask.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A DTO representing a user's credentials
 */
@Data
@AllArgsConstructor
public class LoginDTO {

    @Pattern(regexp = Constant.LOGIN_REGEX)
    @NotNull
    @Size(min = 1, max = 50)
    @JsonProperty("username")
    private String username;

    @NotNull
    @Size(min = Constant.PASSWORD_MIN_LENGTH, max = Constant.PASSWORD_MAX_LENGTH)
    @JsonProperty("password")
    private String password;

    @JsonProperty("rememberme")
    private Boolean rememberMe;

}
