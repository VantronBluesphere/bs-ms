package cloud.bluesphere.assist;

import org.jboss.resteasy.reactive.RestResponse;

public enum ApiErrorCode {

  UNKNOWN_EXCEPTION(RestResponse.Status.INTERNAL_SERVER_ERROR),
  EXTERNAL_SERVICE_EXCEPTION(RestResponse.Status.INTERNAL_SERVER_ERROR),
  CONSTRAINT_VIOLATION_EXCEPTION(RestResponse.Status.BAD_REQUEST),
  ;

  private final RestResponse.Status status;

  ApiErrorCode(RestResponse.Status status) {
    this.status = status;
  }

  public RestResponse.Status getStatus() {
    return status;
  }
}
