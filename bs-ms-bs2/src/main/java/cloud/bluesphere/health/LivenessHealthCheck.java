package cloud.bluesphere.health;

import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements AsyncHealthCheck {

  @Override
  public Uni<HealthCheckResponse> call() {

    return Uni.createFrom().item(HealthCheckResponse.up("Liveness health check"));
  }

}
