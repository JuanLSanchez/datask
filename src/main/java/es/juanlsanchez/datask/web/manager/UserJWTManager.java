package es.juanlsanchez.datask.web.manager;

import org.springframework.security.core.AuthenticationException;

import es.juanlsanchez.datask.web.dto.JWTTokenDTO;
import es.juanlsanchez.datask.web.dto.LoginDTO;

public interface UserJWTManager {

    public JWTTokenDTO authorize(LoginDTO loginDTO) throws AuthenticationException;

}
