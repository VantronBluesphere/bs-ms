package cloud.bluesphere;

import io.quarkus.logging.Log;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;

public class Main {

  public static void main(String[] args) {
    JsonObject jsonObject = Json.createObjectBuilder().add("x", 123).add("y", true).build();
    System.out.println(jsonObject.toString());

  }
}
