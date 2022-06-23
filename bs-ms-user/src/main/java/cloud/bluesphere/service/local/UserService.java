package cloud.bluesphere.service.local;

import io.opentelemetry.extension.annotations.WithSpan;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;

@ApplicationScoped
public class UserService {

  //加@WithSpan注解，追踪该方法
  @WithSpan
  public Uni<JsonObject> test() {

    return Uni.createFrom().item(JsonObject.EMPTY_JSON_OBJECT);
  }

}
