package es.juanlsanchez.datask.web.util;

import es.juanlsanchez.datask.web.dto.QuarterDTO;

public class FileNameUtil {

  public static String incomeBook(QuarterDTO quarterDTO) {
    return "Libro_de_ingresos_" + quarterToStringMonth(quarterDTO);
  }

  public static String invoiceBook(QuarterDTO quarterDTO) {
    return "Libro_de_gastos_" + quarterToStringMonth(quarterDTO);
  }

  private static String quarterToStringMonth(QuarterDTO quarterDTO) {
    String result;
    switch (quarterDTO.getQuarter()) {
      case 1:
        result = "Abril_Mayo_y_Junio";
        break;
      case 2:
        result = "Julio_Agosto_y_Septiembre";
        break;
      case 3:
        result = "Octubre_Noviembre_y_Diciembre";
        break;
      default:
        result = "Enero_Febrero_y_Marzo";
        break;
    }
    return result;
  }

}
