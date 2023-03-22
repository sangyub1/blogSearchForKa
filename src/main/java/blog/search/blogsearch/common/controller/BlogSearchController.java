package blog.search.blogsearch.common.controller;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.service.SearchService;
import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.interest.service.InterestRegService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/blogs")
@RequiredArgsConstructor
public class BlogSearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    @ApiOperation(value = "블로그 검색")
    public SearchResponseDto search(RequestDto requestDto) {
        return searchService.searchBlog(requestDto);
    }

    @GetMapping("/search-list")
    @ApiOperation(value = "블로그 검색 인기 순위")
    public List<InterestSearchDto> interestSearchList(){
        return searchService.getInterestSearch();
    }

}
