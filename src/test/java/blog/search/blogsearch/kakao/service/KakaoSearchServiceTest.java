package blog.search.blogsearch.kakao.service;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.service.SearchService;
import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.kakao.client.KakaoClient;
import blog.search.blogsearch.kakao.dto.KakaoDoc;
import blog.search.blogsearch.kakao.dto.KakaoMeta;
import blog.search.blogsearch.kakao.dto.KakaoResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KakaoSearchServiceTest {
    @InjectMocks
    private KakaoSearchService kakaoSearchService;
    @Mock
    private KakaoClient kakaoClient;


    @Test
    @DisplayName("Kakao responce Search Response 변환")
    public void testPqPriority(){
        KakaoDoc kakaoDoc = KakaoDoc.builder()
                .title("test Title")
                .contents("test contents")
                .datetime("test date")
                .build();
        List<KakaoDoc> docs = new ArrayList<>();
        docs.add(kakaoDoc);
        KakaoMeta kakaoMeta = KakaoMeta.builder()
                .total_count(100)
                .pageable_count(10)
                .is_end(false)
                .build();
        KakaoResponseDto kakaoResponseDto = KakaoResponseDto.builder()
                .meta(kakaoMeta)
                .documents(docs)
                .build();
        SearchResponseDto responseItem = SearchResponseDto.builder()
                .item(kakaoResponseDto)
                .build();
        when(kakaoClient.getBlogs(any(),any(),anyInt(),anyInt())).thenReturn(kakaoResponseDto);
        RequestDto requestDto = RequestDto.builder()
                .query("TEST")
                .page(1)
                .size(10)
                .sort("RECENTLY")
                .build();
        SearchResponseDto res = kakaoSearchService.search(requestDto);
        KakaoResponseDto result = (KakaoResponseDto) res.getItem();
        KakaoResponseDto acc = (KakaoResponseDto) responseItem.getItem();
        assertEquals(acc.getDocuments().get(0).getTitle(),result.getDocuments().get(0).getTitle());

    }

}