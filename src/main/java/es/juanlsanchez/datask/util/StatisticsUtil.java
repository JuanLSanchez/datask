package es.juanlsanchez.datask.util;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class StatisticsUtil {


  public <T> Map<LocalDate, T> fillInterval(Map<LocalDate, T> interval, LocalDate start,
      LocalDate end, T defaultValue, long amountToAdd, TemporalUnit unitToAdd) {
    Map<LocalDate, T> result = Maps.newHashMap();
    LocalDate index = LocalDate.ofEpochDay(start.toEpochDay());
    while (index.isBefore(end)) {
      if (interval.containsKey(index)) {
        result.put(index, interval.get(index));
      } else {
        result.put(index, defaultValue);
      }
      index = index.plus(amountToAdd, unitToAdd);
    }
    return result;
  }
}
