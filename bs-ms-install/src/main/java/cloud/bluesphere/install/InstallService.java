package cloud.bluesphere.install;

import cloud.bluesphere.assist.Args;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstallService {

  public Uni<Void> install(Args args) {
    // TODO 在这里编写安装代码

    return Uni.createFrom().voidItem();
  }

}
