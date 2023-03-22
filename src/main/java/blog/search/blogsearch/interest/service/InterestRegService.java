package blog.search.blogsearch.interest.service;

import blog.search.blogsearch.interest.dto.InterestSearchDto;
import blog.search.blogsearch.interest.entity.InterestEntity;
import blog.search.blogsearch.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InterestRegService {
    private final InterestRepository interestRepository;
    private Map<String, InterestSearchDto> domainCount = new HashMap<>();
    private Map<String, Long> temp = new HashMap<>();

    PriorityQueue<InterestSearchDto> pq = new PriorityQueue<>();

    public void addAll(String domain){
        add(domain);
        addTemp(domain);
    }

    private void addCount(String domain,Long count){
        InterestSearchDto interestSearchDto = InterestSearchDto.builder()
                        .domain(domain)
                        .count(count)
                        .build();
        domainCount.put(domain,interestSearchDto);
    }
    public void add(String domain){
        if(domainCount.containsKey(domain)){
            pq.remove(domainCount.get(domain));
            domainCount.get(domain).addCount();
            pq.add(domainCount.get(domain));
        }else{
            InterestSearchDto saveValue = InterestSearchDto.builder()
                    .domain(domain)
                    .count(1L)
                    .build();
            pq.add(saveValue);
            domainCount.put(domain, saveValue);
        }
    }

    private void addTemp(String domain){
        synchronized (this.temp){
            if (temp.containsKey(domain)){
                Long cnt = temp.get(domain);
                temp.put(domain,++cnt);
            }else{
                temp.put(domain,1L);
            }
        }
    }

    public void clear(){
        temp.clear();
    }

    public List<InterestSearchDto> getInterestSearchList(){
        List<InterestSearchDto> temp = new ArrayList<>();
        for (int i =0;i<10;i++){
            temp.add(pq.poll());
        }
        for (InterestSearchDto interestSearchDto : temp){
            if (interestSearchDto != null)
                pq.add(interestSearchDto);
        }
        return temp;
    }

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void saveDomainCount(){
        Map<String,Long> t = tempDeepCopy();
        for (Map.Entry<String,Long> entry : t.entrySet()){
            Optional<InterestEntity> interest = interestRepository.findById(entry.getKey());
            if (interest.isPresent()){
                InterestEntity interestEntity = interest.get();
                interestEntity.setCount(interestEntity.getCount()+ entry.getValue());
            }else {
                InterestEntity entiry = InterestEntity.builder()
                        .domain(entry.getKey())
                        .count(entry.getValue())
                        .build();
                interestRepository.save(entiry);
            }
        }
    }

    private Map<String, Long> tempDeepCopy(){
        Map<String, Long> t = new HashMap<>();
        synchronized (this.temp) {
            for (Map.Entry<String, Long> entry : temp.entrySet()) {
                t.put(entry.getKey(), entry.getValue());
            }
            this.clear();
        }
        return t;
    }
    @PostConstruct
    public void init(){
        List<InterestEntity> interestEntities = interestRepository.findAll();
        for(InterestEntity interest : interestEntities){
            addCount(interest.getDomain(), interest.getCount());
        }
    }
}
