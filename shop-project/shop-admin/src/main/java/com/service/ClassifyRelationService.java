package com.service;

import java.util.List;

import com.model.ClassifyRelation;

public interface ClassifyRelationService {

	//��ѯ����
	public List<ClassifyRelation> selectAll(ClassifyRelation classifyRelation);
     //����
	public boolean add(ClassifyRelation classifyRelation);
	//�޸�
	public boolean update(ClassifyRelation classifyRelation);
	//ɾ��
	public boolean del(Integer id);
}
