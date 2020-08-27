package com.myworkspace.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myworkspace.entity.FeedEntity;
import com.myworkspace.entity.IFeed;
import com.myworkspace.entity.IFeedAttachedFile;

// FeedEntity�� CRUD�� �����ϴ� ����� �������̽�
// ������ ó���ϱ� ���ؼ� JpaRepository�� ���
													// ��ƼƼŸ��, Id Ÿ��
public interface FeedRepository extends JpaRepository<FeedEntity, Integer> {

	// JPQL(Java Persitent Query Lanaguage)
	// Object Query ����
	// SELECT f <- ��ü�� ����
	// SELECT f.id, f.content <- ��ü 2���� ����
	@Query("SELECT f FROM FeedEntity f") 
	List<IFeed> findAllWithoutFile(Sort s);
	
	// nativeQuery=true
	// value=�� ������ ���� �����ͺ��̽� ������ �۵���
	@Query(value = "SELECT id, content, profile_img, profile_short, profile_name, timestamp, '' AS attached_img, '' AS attached_vid  FROM feeds ORDER BY id DESC", nativeQuery=true)
	List<FeedEntity> findAllWithoutFile();
	
	@Query("SELECT f FROM FeedEntity f WHERE id = :id")
	IFeedAttachedFile findAttachedFileById(@Param("id") int id);		
}
