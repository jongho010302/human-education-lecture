package com.myworkspace.entity;

// projection을 이용하여 특정컬럼만 조회하겠다.
// 인터페이스 생성
public interface IFeed {
	
	// 필드의 getter와 정확히 맞추어야함
	int getId();
	String getContent();
	String getProfileImg();
	String getProfileShort();
	String getProfileName();
	long getTimestamp();
}
