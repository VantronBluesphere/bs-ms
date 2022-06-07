package cloud.bluesphere;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

  public static void main(String[] args) {
    JsonObject jsonObject = Json.createObjectBuilder().add("x", 1).add("y", false).add("z", 2.5).build();
    System.out.println(jsonObject.toString());


    JsonReader jsonReader = Json.createReader(new StringReader(jsonObject.toString()));
    JsonObject data = jsonReader.readObject();
    System.out.println(data.toString());

  }
}
