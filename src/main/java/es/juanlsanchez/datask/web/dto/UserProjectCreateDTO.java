package es.juanlsanchez.datask.web.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumProjectUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProjectCreateDTO {

  @NotNull
  @JsonProperty("relationType")
  private EnumProjectUser relationType;

  @NotNull
  @JsonProperty("projectId")
  private Long projectId;

  @NotNull
  @JsonProperty("userId")
  private Long userId;

}
