package cloud.bluesphere;

import cloud.bluesphere.service.external.Bs2Service;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/v1/bs1")
public class TestResource {

  private final AtomicLong counter = new AtomicLong(0);

  @RestClient
  Bs2Service bs2Service;

  @Context
  HttpServerRequest request;

  @Path("/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> test(HttpHeaders httpHeaders) {
    JsonObjectBuilder httpHeadersJsonObjectBuilder = Json.createObjectBuilder();
    httpHeaders.getRequestHeaders().forEach((name, values) -> {
      JsonArrayBuilder httpHeaderValuesJsonArrayBuilder = Json.createArrayBuilder();
      values.forEach(httpHeaderValuesJsonArrayBuilder::add);
      httpHeadersJsonObjectBuilder.add(name, httpHeaderValuesJsonArrayBuilder);
    });
    JsonObject httpHeadersJsonObject = httpHeadersJsonObjectBuilder.build();
    Log.infof("Test interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString() + ", httpHeaders: " + httpHeadersJsonObject.toString());

    return bs2Service.bs2Test();
  }

  @Path("/fail50PercentTime")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> fail50PercentTime() {
    maybeFail50PercentTime();
    return bs2Service.bs2Test();
  }

  @Path("/bs1/failTimeout")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> failTimeout() {
    randomDelay();
    return bs2Service.bs2Test();
  }

  @Path("/bs1/fail2suc2fail")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<JsonObject> fail2suc2fail() {
    maybeFail2suc2fail();
    return bs2Service.bs2Test();
  }

  private void maybeFail50PercentTime() {
    if (new Random().nextFloat() < 0.5f) {
      throw new RuntimeException("Simulation fail 50 percent time");
    }
  }

  private void randomDelay() {
    try {
      Thread.sleep(new Random().nextInt(500));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void maybeFail2suc2fail() {
    // introduce some artificial failures
    final long invocationNumber = counter.getAndIncrement();
    if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
      throw new RuntimeException("Simulation fail 2 suc 2 fail");
    }
  }

}
