package blog.search.blogsearch.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> handleCommonException(CommonException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return ResponseEntity.badRequest().body(makeValidResponse(ex.getBindingResult()));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.code())
                .message(errorCode.debug())
                .build();
    }


    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.status())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeValidResponse(BindingResult bindingResult) {
        String detail = bindingResult.getFieldError().getDefaultMessage();

        //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
        String bindResultCode = bindingResult.getFieldError().getCode();
        switch (bindResultCode) {
            case "NotNull":
                return ErrorResponse.builder()
                        .message(CommonErrorCode.NECESSARY_VALUE_ERROR.getDebug())
                        .code(CommonErrorCode.NECESSARY_VALUE_ERROR.getCode())
                        .build();
        }
        return ErrorResponse.builder()
                .message(CommonErrorCode.ADMIN_ERROR.getDebug())
                .code(CommonErrorCode.ADMIN_ERROR.getCode())
                .build();
    }

}
