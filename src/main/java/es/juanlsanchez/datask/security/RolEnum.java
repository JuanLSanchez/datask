package es.juanlsanchez.datask.security;

public enum RolEnum {
  ADMIN(Roles.ADMIN), DEVELOPER(Roles.DEVELOPER), //
  MANAGER(Roles.MANAGER), COMPANY(Roles.COMPANY), ANONYMOUS(Roles.ANONYMOUS);

  RolEnum(String rol) {
    this.rol = rol;
  }

  private String rol;

  public static String[] roles() {
    RolEnum[] roles = RolEnum.values();
    String[] result = new String[roles.length];
    int i = 0;
    for (RolEnum rol : roles) {
      result[i] = rol.anyRole();
      i++;
    }
    return result;
  }

  public String anyRole() {
    return "" + rol;
  }

  public final String role() {
    return rol;
  }

  public static class Roles {

    public static final String ANONYMOUS = "ANONYMOUS";
    public static final String ADMIN = "ADMIN";
    public static final String DEVELOPER = "DEVELOPER";
    public static final String MANAGER = "MANAGER";
    public static final String COMPANY = "COMPANY";
  }

}
