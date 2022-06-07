package cloud.bluesphere.service.external;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@RegisterRestClient(configKey = "bs-ms-bs2")
@RegisterRestClient(baseUri = "stork://bs-ms-bs2")
@Retry
@Timeout
@CircuitBreaker
public interface Bs2Service {

  @Path("/api/v1/bs2/test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Uni<JsonObject> bs2Test();

}
