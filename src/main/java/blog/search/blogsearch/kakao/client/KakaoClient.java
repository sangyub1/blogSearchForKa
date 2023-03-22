package blog.search.blogsearch.kakao.client;

import blog.search.blogsearch.kakao.config.KakaoClientConfig;
import blog.search.blogsearch.kakao.dto.KakaoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kako",url ="https://dapi.kakao.com/",configuration = KakaoClientConfig.class)
public interface KakaoClient {
    @GetMapping(value = "/v2/search/blog")
    KakaoResponseDto getBlogs(@RequestParam String query,
                              @RequestParam String sort,
                              @RequestParam int page,
                              @RequestParam int size);
}
