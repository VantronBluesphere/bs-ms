package cloud.bluesphere.assist;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.validation.ConstraintViolationException;
import java.io.StringReader;

public class ExceptionMapper {

  @ServerExceptionMapper
  public Uni<RestResponse<ApiErrorResult>> mapException(ConstraintViolationException t) {
    Log.warnf(t, "ConstraintViolationException occurred: ");
    RestResponse<ApiErrorResult> restResponse = RestResponse.ResponseBuilder.<ApiErrorResult>create(RestResponse.Status.BAD_REQUEST).entity(new ApiErrorResult(ApiErrorCode.CONSTRAINT_VIOLATION_EXCEPTION, t.getMessage())).build();
    return Uni.createFrom().item(restResponse);
  }

  @ServerExceptionMapper
  public Uni<RestResponse<ApiErrorResult>> mapException(ClientWebApplicationException t) {
    Log.errorf(t, "ClientWebApplicationException occurred: ");
    try (JsonReader jsonReader = Json.createReader(new StringReader(t.getResponse().readEntity(String.class)))) {
      JsonObject data;
      try {
        data = jsonReader.readObject();
      }catch (Exception e){
        ApiErrorResult apiErrorResult = new ApiErrorResult(ApiErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        data = Json.createObjectBuilder()
            .add(ApiErrorResult.ERROR_CODE, ApiErrorCode.UNKNOWN_EXCEPTION.toString())
            .add(ApiErrorResult.ERROR_MSG, e.getMessage())
            .build();
      }
      RestResponse<ApiErrorResult> restResponse = RestResponse.ResponseBuilder.<ApiErrorResult>serverError().entity(new ApiErrorResult(ApiErrorCode.EXTERNAL_SERVICE_EXCEPTION, t.getMessage() + ", external service response code: " + t.getResponse().getStatus() + " and body: " + data.toString())).build();
      return Uni.createFrom().item(restResponse);
    }
  }

  @ServerExceptionMapper
  public Uni<RestResponse<ApiErrorResult>> mapException(Exception t) {
    Log.errorf(t, "Unexpected error occurred: ");
    RestResponse<ApiErrorResult> restResponse = RestResponse.ResponseBuilder.<ApiErrorResult>serverError().entity(new ApiErrorResult(ApiErrorCode.UNKNOWN_EXCEPTION, t.getMessage())).build();
    return Uni.createFrom().item(restResponse);
  }

}
