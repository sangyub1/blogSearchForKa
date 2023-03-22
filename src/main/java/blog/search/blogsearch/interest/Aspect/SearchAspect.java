package blog.search.blogsearch.interest.Aspect;

import blog.search.blogsearch.common.dto.RequestDto;
import blog.search.blogsearch.interest.service.InterestRegService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class SearchAspect {
    private final InterestRegService interestRegService;
    @AfterReturning("@annotation(blog.search.blogsearch.interest.anno.Search)")
    public void searchCount(JoinPoint joinPoint){
        RequestDto requestDto = (RequestDto)joinPoint.getArgs()[0];
        /*
         query 내 키워드란 무엇인가??
         키워드의 의미에 따라 Logic 수정 필요.
         ex) query = "화장실 샤워기"
           1. keyword : "화장실 샤워기"
           2. keyword : "화장실,"샤워기"
         */
        interestRegService.addAll(requestDto.getQuery());
    }
}
