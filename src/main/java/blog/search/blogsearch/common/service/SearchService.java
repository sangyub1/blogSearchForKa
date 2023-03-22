package blog.search.blogsearch.common.service;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.dto.SortEnum;
import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.interest.service.InterestRegService;
import blog.search.blogsearch.kakao.service.KakaoSearchService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final KakaoSearchService kakaoSearchService;
    private final InterestRegService interestRegService;

    public SearchResponseDto searchBlog(String query,
                                        SortEnum sort,
                                        int page,
                                        int size) {
        RequestDto requestDto = RequestDto.builder()
                .query(query)
                .sort(sort)
                .page(page)
                .size(size)
                .build();
        return kakaoSearchService.search(requestDto);
    }

    public List<InterestSearchDto> getInterestSearch() {
        return interestRegService.getInterestSearchList();
    }
}
