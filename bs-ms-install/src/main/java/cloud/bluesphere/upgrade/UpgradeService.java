package cloud.bluesphere.upgrade;

import cloud.bluesphere.assist.Args;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpgradeService {

  public Uni<Void> upgrade(Args args) {
    // TODO 在这里编写升级代码

    return Uni.createFrom().voidItem();
  }

}
