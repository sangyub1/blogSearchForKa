package blog.search.blogsearch.interest.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INTEREST_SEARCh_TABLE")
@Getter
public class InterestEntity {
    @Id
    private String domain;
    @Setter
    private Long count;

}
