package cloud.bluesphere;

import cloud.bluesphere.service.external.Bs1Service;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

@Path("/api/v1/user")
public class TestResource {

  @RestClient
  Bs1Service bs1Service;

  @Context
  HttpServerRequest request;

  @Path("/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> test() {
    Log.infof("Test interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString());
    return bs1Service.bs1Test();
  }

  @Path("/fail50PercentTime")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> fail50PercentTime() {

    return bs1Service.bs1Fail50PercentTime();
  }

  @Path("/failTimeout")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> failTimeout() {

    return bs1Service.bs1FailTimeout();
  }

  @Path("/fail2suc2fail")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> fail2suc2fail() {

    return bs1Service.bs1Fail2suc2fail();
  }

}
