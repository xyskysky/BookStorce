package com.jwn.bookstore.dao;

import java.util.List;

import com.jwn.bookstore.domain.Book;
import com.jwn.bookstore.web.CriteriaBook;
import com.jwn.bookstore.web.Page;

public interface BookDAO
{
	/**
	 * ����ID��Ż�ȡ�����鱾��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	Book getBook(int id);
	/**
	 * ���ݴ����CriteriaBook ���ض��ڵ�Page����
	 * @param cb
	 * @return
	 */
	Page<Book> getPage(CriteriaBook cb,int pageSize);
	
	/**
	 * ���ݴ����CriteraBook �������Ӧ�ļ�¼����
	 * @param cb
	 * @return
	 */
	long getTotalBookNumber(CriteriaBook cb);
	
	/**
	 * ���ݴ���� CriteriaBook �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	List<Book> getPageList(CriteriaBook cb,int pageSize);
	
	/**
	 * ����ָ�� id �� book �� storeNumber �ֶε�ֵ
	 * @param id
	 * @return
	 */
	int getStoreNumber(Integer id);
}
