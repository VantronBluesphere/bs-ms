package cloud.bluesphere;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.time.Instant;

@Path("/api/v1")
public class TestResource {

  @Context
  HttpServerRequest request;

  @Path("/bs2/test")
  @GET
  public Uni<JsonObject> test(){
    Log.infof("Test interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString());
    JsonObject jsonObject = Json.createObjectBuilder()
        .add("from", "bs2")
        .add("time", Instant.now().toString())
        .build();
    return Uni.createFrom().item(jsonObject);
  }

}
