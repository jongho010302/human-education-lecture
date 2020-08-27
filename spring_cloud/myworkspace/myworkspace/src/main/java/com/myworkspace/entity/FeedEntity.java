package com.myworkspace.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity // �����ͺ��̽� ����Ǵ� ��ü��
@Table(name="feeds") // ����Ǵ� ���̺� �̸� ����
@NoArgsConstructor // �������� �Ű������� ����
@Data // �Һ����� getter, setter �ڵ� ����
public class FeedEntity {
	
	@Id  // ���̺��� pk���� ����
	@GeneratedValue(strategy=GenerationType.AUTO) // �ڵ����� �÷�
	private int id;
	
	@Column(nullable=false) // ���̺� �÷����� �ʵ带 ����
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
	// mysql�� LONGTEXT ó���� ���ؼ� Ÿ���� ��������
	private String attachedImg;
	
	@Column(columnDefinition = "TEXT", updatable = false)
	private String attachedVid;
}
