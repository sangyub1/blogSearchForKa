# 실행 파일 위치
https://github.com/sangyub1/blogSearchForKa/blob/master/build/libs/blogSearch-0.0.1-SNAPSHOT.jar

# Rest API
Swagger Url : http://localhost:8080/swagger-ui.html#/blog-search-controller
 - 위 실행 파일 실행 후 Swaager Url 입력
<img width="1467" alt="image" src="https://user-images.githubusercontent.com/128556715/226778013-52f313a6-957a-40c5-89e0-830ee49086f5.png">

1. /blogs/search
 - Request Parameter : query(String), sort(String), page(int), size(int)
 - Response parameter : item(API 별 검색 결과)
 - 기능 : query 검색 결과를 반환하고 실시간 검색 순위를 위한 keyword 등록
2. /blogs/search-list
 - Request Parameter : None
 - Response parameter : List[ domain, count ]
 - 기능 : 등록된 keyword 기준으로 실시간 검색 순위를 반환 ( domain : keyword , count : 조회 횟수 )
# 기능 정의
 Kakao blog 검색 기능 : Kakao API 사용을 위한 OpenFeign client를 사용.
 검색 Keyword 등록 : @Search 어노테이션이 붙은 메소드에 대해 Keyword를 등록해주는 AOP 구현.
 검색 순위 : Keyword가 등록될때마다 PQ(우선순위 큐)에 업데이트
 검색 Count 등록 : DB Update와 memory Update를 분리하기 위한 Scheduler 활용
                 DB update는 Memory Update와 비교하여 시간이 많이 걸림으로 DB update와 Memory Update의 Thread분리를 통해 Memory Update Delay 예방
                 
# 
