package com.myworkspace.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity // �����ͺ��̽� ����Ǵ� ��ü��
@Table(name="todo") // ����Ǵ� ���̺� �̸� ����
@NoArgsConstructor // �������� �Ű������� ����
@Data // �Һ����� getter, setter �ڵ� ����
public class TodoEntity {
	
	@Id  // ���̺��� pk���� ����
	@GeneratedValue(strategy=GenerationType.AUTO) // �ڵ����� �÷�
	//@GeneratedValue(generator = "generator")
    //@GenericGenerator(name = "generator", strategy = "increment")	
	private int id;
	
	@Column(nullable=false, updatable=false) // ���̺� �÷����� �ʵ带 ����
	private String memo;
	
	@Column // ���̺� �÷����� �ʵ带 ����
	private boolean isDone;
}
