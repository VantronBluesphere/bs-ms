package cloud.bluesphere.service.external;

import cloud.bluesphere.service.external.fallback.Bs1ServiceBs1Fail50PercentTimeFallback;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@RegisterRestClient(configKey = "bs-ms-bs1")
@RegisterRestClient(baseUri = "stork://bs-ms-bs1")
@Retry(maxRetries = 1, delay = 1000)
@Timeout(250)
@CircuitBreaker(requestVolumeThreshold = 4)
public interface Bs1Service {

  @Path("/api/v1/bs1/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Uni<JsonObject> bs1Test();

  @Fallback(Bs1ServiceBs1Fail50PercentTimeFallback.class)
  @Path("/api/v1/bs1/fail50PercentTime")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Uni<JsonObject> bs1Fail50PercentTime();

  @Path("/api/v1/bs1/failTimeout")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Uni<JsonObject> bs1FailTimeout();

  @Path("/api/v1/bs1/fail2suc2fail")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Uni<JsonObject> bs1Fail2suc2fail();

}
