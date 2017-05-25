package es.juanlsanchez.datask.util.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trio<T1, T2, T3> {

  private T1 first;
  private T2 second;
  private T3 third;

}
