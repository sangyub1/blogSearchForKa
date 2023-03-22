package blog.search.blogsearch.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of="domain")
public class InterestSearchDto implements Comparable<InterestSearchDto> {
    private String domain;
    private Long count;

    public void addCount(){
        count++;
    }
    @Override
    public int compareTo(InterestSearchDto comp) {

        if (this.count < comp.getCount())
            return 1;
        else if (this.count > comp.getCount())
            return -1;
        return 0;
    }
}
