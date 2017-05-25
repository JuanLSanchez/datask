package es.juanlsanchez.datask.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Object to return as body in JWT Authentication.
 */
@Getter
@Setter
@AllArgsConstructor
public class JWTTokenDTO {

    @JsonProperty("id_token")
    private String idToken;

}
