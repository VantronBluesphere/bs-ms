package cloud.bluesphere.service.local;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;

@ApplicationScoped
public class UserService {

  public Uni<JsonObject> test() {

    return Uni.createFrom().item(JsonObject.EMPTY_JSON_OBJECT);
  }

}
