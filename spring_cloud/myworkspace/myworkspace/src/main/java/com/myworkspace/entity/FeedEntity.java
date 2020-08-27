package com.myworkspace.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity // 데이터베이스 연결되는 객체다
@Table(name="feeds") // 연결되는 테이블 이름 정의
@NoArgsConstructor // 생성자의 매개변수가 없다
@Data // 롬복으로 getter, setter 자동 생성
public class FeedEntity {
	
	@Id  // 테이블의 pk임을 지정
	@GeneratedValue(strategy=GenerationType.AUTO) // 자동증가 컬럼
	private int id;
	
	@Column(nullable=false) // 테이블에 컬럼으로 필드를 지정
	private String content;
	
	@Column(nullable=false, updatable = false)
	private String profileImg;
	
	@Column(nullable=false, updatable = false)
	private String profileShort;
	
	@Column(nullable=false, updatable = false)
	private String profileName;
	
	@Column(nullable=false, updatable = false)
	private long timestamp;
	
	@Column(columnDefinition = "TEXT", updatable = false)
	// mysql의 LONGTEXT 처리를 위해서 타입을 지정해줌
	private String attachedImg;
	
	@Column(columnDefinition = "TEXT", updatable = false)
	private String attachedVid;
}
