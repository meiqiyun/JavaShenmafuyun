package com.smfy.dao;

import java.util.List;

import com.smfy.entity.Cunkuan;

public interface CunkuanDao {
	/**
	 * ��ѯ���д��
	 * @param tj
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Cunkuan>findAllCunkuan(String tj,int pageIndex,int pageSize);	
	/**
	 * ��ѯ��������
	 * @param tj ����
	 * @return
	 */
	int findAllCount(String tj);
	/**
	 * ɾ������
	 * @param cunkuan
	 * @return
	 */
	int delCunkuan(Cunkuan cunkuan);
}