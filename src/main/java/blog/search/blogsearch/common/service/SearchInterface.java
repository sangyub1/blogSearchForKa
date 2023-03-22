package blog.search.blogsearch.common.service;

import blog.search.blogsearch.common.dto.SearchResponseDto;
import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.interest.anno.Search;

public interface SearchInterface {
    SearchResponseDto search(RequestDto requestDto);
}
