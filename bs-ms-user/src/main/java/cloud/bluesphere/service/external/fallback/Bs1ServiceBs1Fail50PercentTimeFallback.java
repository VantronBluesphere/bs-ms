package cloud.bluesphere.service.external.fallback;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Instant;

public class Bs1ServiceBs1Fail50PercentTimeFallback implements FallbackHandler<Uni<JsonObject>> {

  @Override
  public Uni<JsonObject> handle(ExecutionContext context) {

    return Uni.createFrom().item(Json.createObjectBuilder().add("from", "fallback for bs1Fail50PercentTime").add("time", Instant.now().toString()).build());
  }

}
