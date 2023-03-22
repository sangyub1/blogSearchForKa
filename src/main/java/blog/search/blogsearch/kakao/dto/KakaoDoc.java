package blog.search.blogsearch.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoDoc {
    private String title;
    private String contents;
    private String url;
    private String datetime;
}
