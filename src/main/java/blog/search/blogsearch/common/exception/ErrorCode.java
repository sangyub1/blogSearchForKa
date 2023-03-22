package blog.search.blogsearch.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus status();
    String code();
    String debug();
}
