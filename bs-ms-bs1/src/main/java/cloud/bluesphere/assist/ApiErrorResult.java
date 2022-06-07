package cloud.bluesphere.assist;

public class ApiErrorResult {

  private final ApiErrorCode code;
  private String description;

  public ApiErrorResult(ApiErrorCode code) {
    this.code = code;
  }

  public ApiErrorResult(ApiErrorCode code, String description) {
    this.code = code;
    this.description = description;
  }

  public ApiErrorCode getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

}
