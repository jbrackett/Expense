package com.expense.domain.constants;

import java.util.ArrayList;
import java.util.List;

public final class CategoryType {

  public static final String AIR = "AIR";
  public static final String CAR = "CAR";
  public static final String LODGING = "LODGING";
  public static final String STANDARD = "STANDARD";
  public static final String TELECOM = "TELECOM";

  public static Iterable<String> getTypes() {
    List<String> types = new ArrayList<>();
    types.add(AIR);
    types.add(CAR);
    types.add(LODGING);
    types.add(STANDARD);
    types.add(TELECOM);
    return types;
  }

}
