package com.myworkspace.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myworkspace.entity.FeedEntity;
import com.myworkspace.entity.IFeed;
import com.myworkspace.entity.IFeedAttachedFile;

// FeedEntity의 CRUD를 제어하는 저장소 인터페이스
// 정렬을 처리하기 위해서 JpaRepository를 사용
													// 엔티티타입, Id 타입
public interface FeedRepository extends JpaRepository<FeedEntity, Integer> {

	// JPQL(Java Persitent Query Lanaguage)
	// Object Query 일종
	// SELECT f <- 객체를 선택
	// SELECT f.id, f.content <- 객체 2개가 선택
	@Query("SELECT f FROM FeedEntity f") 
	List<IFeed> findAllWithoutFile(Sort s);
	
	// nativeQuery=true
	// value=의 쿼리는 실제 데이터베이스 쿼리로 작동됨
	@Query(value = "SELECT id, content, profile_img, profile_short, profile_name, timestamp, '' AS attached_img, '' AS attached_vid  FROM feeds ORDER BY id DESC", nativeQuery=true)
	List<FeedEntity> findAllWithoutFile();
	
	@Query("SELECT f FROM FeedEntity f WHERE id = :id")
	IFeedAttachedFile findAttachedFileById(@Param("id") int id);		
}
