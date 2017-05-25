package es.juanlsanchez.datask.web.dto;

import java.time.Instant;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  @NotNull
  @Size(min = 1, max = 50)
  @Column(length = 50, unique = true, nullable = false)
  private String login;

  private String surname;

  @Past
  private Instant creationMoment;

  @NotNull
  @Column(nullable = false)
  private boolean activated;

}
