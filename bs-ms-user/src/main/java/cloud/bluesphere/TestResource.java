package cloud.bluesphere;

import cloud.bluesphere.service.external.Bs1Service;
import cloud.bluesphere.service.local.UserService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
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

  @Inject
  UserService userService;

  @Context
  HttpServerRequest request;

  @Path("/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> test() {
    Log.infof("Test log message 1, log param 1: %s, log param 2: %b, log param 3: %d", "param1", true, 3);
    Log.debugf("Test log message 2, log param 1: %f, log param 2: %s, log param 3: %b", 2.5d, "param2", false);
    Log.infof("Test interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString());

    return userService.test().chain(jsonObject -> bs1Service.bs1Test());
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
