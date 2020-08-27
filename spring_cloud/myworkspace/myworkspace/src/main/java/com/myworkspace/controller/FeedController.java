package com.myworkspace.controller;


// 자바 sdk 기본 패키지
import java.util.List;

// 종속성으로 추가된 패키지
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 내가 만든 클래스
import com.myworkspace.entity.FeedEntity;
import com.myworkspace.entity.IFeed;
import com.myworkspace.entity.IFeedAttachedFile;
import com.myworkspace.repository.FeedRepository;

@RestController
public class FeedController {
	
	@Autowired
	FeedRepository feedRepo;
	
	/*
	// 피드 목록 조회
	@RequestMapping(value="/feeds", method=RequestMethod.GET)
	public List<FeedEntity> getFeeds() throws Exception{
					    // findAll 엔티티 전체조회
		                // new Sort(Sort.Direction.DESC,"id")
		                // -> id 필드를 기준으로 역정렬 
		return feedRepo.findAll(new Sort(Sort.Direction.DESC,"id"));
	}
	*/
	
	// 피드 목록 조회(파일제외)
	@RequestMapping(value="/feeds", method=RequestMethod.GET)
	//public List<IFeed> getFeeds() throws Exception{
	public List<FeedEntity> getFeeds() throws Exception{
		//return feedRepo.findAllWithoutFile(new Sort(Sort.Direction.DESC, "id"));
		return feedRepo.findAllWithoutFile();
	}	
	
	// 피드 저장(POST, PUT)
	// 경로: /리소스/아이디
	// 메소드: GET, POST, PUT, DELETE, PATCH, OPTIONS.. 등등
	@RequestMapping(value="/feeds", method= {RequestMethod.POST, RequestMethod.PUT})
	public FeedEntity saveFeed(@RequestBody FeedEntity f) throws Exception{
		// upsert - id가 있으면 update, 없으면 insert
		return feedRepo.save(f); 
	}
	
	// 피드 삭제
	// ex) /feeds/1
	// 메소드: DELETE
	// feeds 리소스에 아이디 1인 레코드를 DELETE 해라
	@RequestMapping(value="/feeds/{id}", method=RequestMethod.DELETE)
	public void removeFeed(@PathVariable int id) throws Exception{		
		feedRepo.deleteById(id);
	}
	
	// 피드 첨부파일 조회
	@RequestMapping(value="/feeds/feed-file/{id}", method=RequestMethod.GET)
	public IFeedAttachedFile getFeed(@PathVariable int id) throws Exception{		
		return feedRepo.findAttachedFileById(id);
	}	
}
