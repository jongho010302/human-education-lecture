package com.myworkspace.entity;

// projection�� �̿��Ͽ� Ư���÷��� ��ȸ�ϰڴ�.
// �������̽� ����
public interface IFeed {
	
	// �ʵ��� getter�� ��Ȯ�� ���߾����
	int getId();
	String getContent();
	String getProfileImg();
	String getProfileShort();
	String getProfileName();
	long getTimestamp();
}
