package blog.search.blogsearch.common.service;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.dto.SortEnum;
import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.interest.service.InterestRegService;
import blog.search.blogsearch.kakao.client.KakaoClient;
import blog.search.blogsearch.kakao.dto.KakaoDoc;
import blog.search.blogsearch.kakao.dto.KakaoMeta;
import blog.search.blogsearch.kakao.dto.KakaoResponseDto;
import blog.search.blogsearch.kakao.service.KakaoSearchService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    @DisplayName("pq 등록 확인")
    public void testPqReg(){
        String domain = "Test";
        SearchResponseDto test = searchService.searchBlog(makeRequestByQuery(domain));
        List<InterestSearchDto> pqList = searchService.getInterestSearch();
        assertEquals(10,pqList.size());
        assertEquals(domain,pqList.get(0).getDomain());
    }

    @Test
    @DisplayName("pq 정렬 확인")
    public void testPqSort(){
        String domain1 = "Test_1";
        String domain2 = "Test_2";
        for (int i =0;i<3;i++){
            searchService.searchBlog(makeRequestByQuery(domain1));
        }
        for (int i =0;i<2;i++){
            searchService.searchBlog(makeRequestByQuery(domain2));
        }
        List<InterestSearchDto> pqList = searchService.getInterestSearch();
        assertEquals(domain1,pqList.get(0).getDomain());
        assertEquals(domain2,pqList.get(1).getDomain());

        // domain2 추가 호출
        for (int i =0;i<2;i++){
            searchService.searchBlog(makeRequestByQuery(domain2));
        }
        pqList = searchService.getInterestSearch();
        assertEquals(domain2,pqList.get(0).getDomain());
        assertEquals(domain1,pqList.get(1).getDomain());
    }

    private RequestDto makeRequestByQuery(String query){
        return RequestDto.builder()
                .query(query)
                .page(1)
                .size(1)
                .sort(SortEnum.RECENCY)
                .build();
    }
}