package cloud.bluesphere.assist;

import java.util.HashMap;
import java.util.Map;

public class Args {

  public static final String ARG_PREFIX = "--";
  public static final String ARG_EQ_SYMBOL = "=";
  public static final String MODE_KEY = "mode";

  private Mode mode;

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public static Args parse(String... args) {
    Map<String, String> map = new HashMap<>();
    for (String arg : args) {
      if (isValidArg(arg)) {
        break;
      }
      String[] argArrays = arg.split(ARG_EQ_SYMBOL);
      map.put(argArrays[0], argArrays[1]);
    }
    Args argsObject = new Args();
    argsObject.mode = Mode.valueOf(map.get(MODE_KEY));

    return argsObject;
  }

  private static boolean isValidArg(String arg) {

    return arg.startsWith(ARG_PREFIX) && arg.contains(ARG_EQ_SYMBOL);
  }

}
