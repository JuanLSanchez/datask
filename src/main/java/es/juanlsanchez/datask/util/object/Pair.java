package es.juanlsanchez.datask.util.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<T1, T2> {

  private T1 first;
  private T2 second;

}
