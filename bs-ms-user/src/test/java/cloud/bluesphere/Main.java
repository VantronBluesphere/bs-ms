package cloud.bluesphere;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;

public class Main {

  public static void main(String[] args) {
    System.out.println(Instant.ofEpochSecond(1481853690));
    System.out.println(Instant.parse("2022-06-13T01:00:00Z").getEpochSecond());

  }
}
