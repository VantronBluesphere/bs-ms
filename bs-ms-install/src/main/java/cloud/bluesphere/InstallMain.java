package cloud.bluesphere;

import cloud.bluesphere.assist.Args;
import cloud.bluesphere.assist.Mode;
import cloud.bluesphere.install.InstallService;
import cloud.bluesphere.upgrade.UpgradeService;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain
public class InstallMain {

  /**
   * 参数格式：--mode=install --arg1=value1 --arg2=value2
   * @param args
   */
  public static void main(String[] args) {
    Quarkus.run(InstallApplication.class, args);
  }

  public static class InstallApplication implements QuarkusApplication {

    @Inject
    InstallService installService;
    @Inject
    UpgradeService upgradeService;

    @Override
    public int run(String... args) throws Exception {
      Args argsObject = Args.parse(args);
      Mode mode = argsObject.getMode();
      switch (mode) {
        case INSTALL:
          Log.infof("Start install...");
          installService.install(argsObject).await().indefinitely();
          break;
        case UPGRADE:
          Log.infof("Start upgrade...");
          upgradeService.upgrade(argsObject).await().indefinitely();
          break;
      }
      return 0;
    }
  }

}
