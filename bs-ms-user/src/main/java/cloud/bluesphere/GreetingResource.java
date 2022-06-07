package cloud.bluesphere;

import io.quarkus.logging.Log;
import io.vertx.core.http.HttpServerRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.server.spi.ServerRequestContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.Optional;

@Path("/hello")
public class GreetingResource {

  @ConfigProperty(name = "greeting.message")
  String message;

  @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
  String suffix;

  @ConfigProperty(name = "greeting.name")
  Optional<String> name;

  @Context
  HttpServerRequest request;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    String returnMsg = message + " " + name.orElse("world") + suffix;
    Log.infof("Hello interface requested at " + Instant.now().toString() + ", from " + request.remoteAddress().toString());
    return returnMsg;
  }

}