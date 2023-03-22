package blog.search.blogsearch.kakao.service;

import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.exception.CommonErrorCode;
import blog.search.blogsearch.common.exception.CommonException;
import blog.search.blogsearch.common.service.SearchInterface;
import blog.search.blogsearch.interest.anno.Search;
import blog.search.blogsearch.kakao.client.KakaoClient;
import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.kakao.dto.KakaoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;


@Service
@RequiredArgsConstructor
public class KakaoSearchService implements SearchInterface {
    private final KakaoClient kakaoClient;

    @Override
    @Search
    public SearchResponseDto search(RequestDto requestDto){
        try {
            SearchResponseDto<KakaoResponseDto> ret = kakaoClient.getBlogs(requestDto.getQuery(),
                    requestDto.getSort().getValue(),
                    requestDto.getPage(),
                    requestDto.getSize()).toCommon();
            return ret;
        }catch (Exception ex){
            throw new CommonException(CommonErrorCode.KAKAO_CLIENT_TIMEOUT);
        }

    }
}
