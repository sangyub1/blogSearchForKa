package blog.search.blogsearch.common.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class RequestDto {
    @NotNull
    private String query;
    private String sort;
    private int page;
    private int size;
}
