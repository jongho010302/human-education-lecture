package com.myworkspace.controller;


// �ڹ� sdk �⺻ ��Ű��
import java.util.List;

// ���Ӽ����� �߰��� ��Ű��
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// ���� ���� Ŭ����
import com.myworkspace.entity.FeedEntity;
import com.myworkspace.entity.IFeed;
import com.myworkspace.entity.IFeedAttachedFile;
import com.myworkspace.repository.FeedRepository;

@RestController
public class FeedController {
	
	@Autowired
	FeedRepository feedRepo;
	
	/*
	// �ǵ� ��� ��ȸ
	@RequestMapping(value="/feeds", method=RequestMethod.GET)
	public List<FeedEntity> getFeeds() throws Exception{
					    // findAll ��ƼƼ ��ü��ȸ
		                // new Sort(Sort.Direction.DESC,"id")
		                // -> id �ʵ带 �������� ������ 
		return feedRepo.findAll(new Sort(Sort.Direction.DESC,"id"));
	}
	*/
	
	// �ǵ� ��� ��ȸ(��������)
	@RequestMapping(value="/feeds", method=RequestMethod.GET)
	//public List<IFeed> getFeeds() throws Exception{
	public List<FeedEntity> getFeeds() throws Exception{
		//return feedRepo.findAllWithoutFile(new Sort(Sort.Direction.DESC, "id"));
		return feedRepo.findAllWithoutFile();
	}	
	
	// �ǵ� ����(POST, PUT)
	// ���: /���ҽ�/���̵�
	// �޼ҵ�: GET, POST, PUT, DELETE, PATCH, OPTIONS.. ���
	@RequestMapping(value="/feeds", method= {RequestMethod.POST, RequestMethod.PUT})
	public FeedEntity saveFeed(@RequestBody FeedEntity f) throws Exception{
		// upsert - id�� ������ update, ������ insert
		return feedRepo.save(f); 
	}
	
	// �ǵ� ����
	// ex) /feeds/1
	// �޼ҵ�: DELETE
	// feeds ���ҽ��� ���̵� 1�� ���ڵ带 DELETE �ض�
	@RequestMapping(value="/feeds/{id}", method=RequestMethod.DELETE)
	public void removeFeed(@PathVariable int id) throws Exception{		
		feedRepo.deleteById(id);
	}
	
	// �ǵ� ÷������ ��ȸ
	@RequestMapping(value="/feeds/feed-file/{id}", method=RequestMethod.GET)
	public IFeedAttachedFile getFeed(@PathVariable int id) throws Exception{		
		return feedRepo.findAttachedFileById(id);
	}	
}
