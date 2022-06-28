package cloud.bluesphere;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

@Path("/api/v1/bs2")
public class TestResource {

  @Context
  HttpServerRequest request;

  @Path("/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> test(HttpHeaders httpHeaders){
    JsonObjectBuilder httpHeadersJsonObjectBuilder = Json.createObjectBuilder();
    httpHeaders.getRequestHeaders().forEach((name, values) -> {
      JsonArrayBuilder httpHeaderValuesJsonArrayBuilder = Json.createArrayBuilder();
      values.forEach(httpHeaderValuesJsonArrayBuilder::add);
      httpHeadersJsonObjectBuilder.add(name, httpHeaderValuesJsonArrayBuilder);
    });
    JsonObject httpHeadersJsonObject = httpHeadersJsonObjectBuilder.build();
    Log.infof("Test interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString() + ", httpHeaders: " + httpHeadersJsonObject.toString());

    JsonObject jsonObject = Json.createObjectBuilder()
        .add("from", "bs2")
        .add("time", Instant.now().toString())
        .build();
    return Uni.createFrom().item(jsonObject);
  }

}
