package blog.search.blogsearch.kakao.config;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Authorization", "KakaoAK 920bbb6e1b818ec56d31c1d8dc8dafe1");
    }
}
