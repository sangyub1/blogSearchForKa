package blog.search.blogsearch.kakao.dto;

import blog.search.blogsearch.common.dto.SearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class KakaoResponseDto {
    private KakaoMeta meta;
    private List<KakaoDoc> documents;

    public SearchResponseDto<KakaoResponseDto> toCommon(){
        return SearchResponseDto.<KakaoResponseDto>builder()
                .item(this)
                .build();
    }
}
