package blog.search.blogsearch.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoMeta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
