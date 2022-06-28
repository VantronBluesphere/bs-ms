package cloud.bluesphere.assist;

public class ApiErrorResult {

  public static final String ERROR_CODE = "errorCode";
  public static final String ERROR_MSG = "errorMsg";

  private final ApiErrorCode errorCode;
  private String errorMsg;

  public ApiErrorResult(ApiErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public ApiErrorResult(ApiErrorCode errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public ApiErrorCode getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

}
