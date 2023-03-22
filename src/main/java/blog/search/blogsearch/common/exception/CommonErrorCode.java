package blog.search.blogsearch.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    KAKAO_CLIENT_TIMEOUT(HttpStatus.INTERNAL_SERVER_ERROR,"ERR-001","Kakao Client 접속에 실패 했습니다."),
    NECESSARY_VALUE_ERROR(HttpStatus.BAD_REQUEST,"ERR-002","필수값이 누락되었습니다."),
    ADMIN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"ERR-003","구분되지 않은 Error로 관리자에게 문의 바랍니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String debug;

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String debug() {
        return this.debug;
    }
}
