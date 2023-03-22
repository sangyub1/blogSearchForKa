package blog.search.blogsearch.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SearchResponseDto<T> {
    private T item;
}
