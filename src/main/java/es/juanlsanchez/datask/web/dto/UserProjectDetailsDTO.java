package es.juanlsanchez.datask.web.dto;

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
public class UserProjectDetailsDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("relationType")
  private EnumProjectUser relationType;

  @JsonProperty("projectId")
  private Long projectId;

  @JsonProperty("userId")
  private Long userId;

}
