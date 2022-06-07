package cloud.bluesphere.health;

import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements AsyncHealthCheck {

  @Override
  public Uni<HealthCheckResponse> call() {

    return Uni.createFrom().item(HealthCheckResponse.up("Readiness health check"));
  }

}
