package blog.search.blogsearch.common.controller;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.service.SearchService;
import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.interest.service.InterestRegService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/blogs")
@RequiredArgsConstructor
public class BlogSearchController {
    private final SearchService searchService;

    private final InterestRegService interestRegService;

    @GetMapping("/search")
    public SearchResponseDto search(@RequestBody @Valid RequestDto requestDto){
        return searchService.searchBlog(requestDto);
    }
    @GetMapping("/search-list")
    public List<InterestSearchDto> interestSearchList(){
        return searchService.getInterestSearch();
    }

}
