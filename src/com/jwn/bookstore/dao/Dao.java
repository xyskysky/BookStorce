package com.jwn.bookstore.dao;

import java.util.List;

/**
 * Dao �ӿ�, ���� Dao �Ļ�������, �� BaseDao �ṩʵ��.
 * 
 * @param <T>:
 *            Dao ʵ�ʲ����ķ�������
 */
public interface Dao<T>
{
	/**
	 * ִ�� INSERT ����, ���ز����ļ�¼�� ID
	 * 
	 * @param sql
	 * @param params
	 * @return ���ز����ļ�¼��ID
	 */
	long insert(String sql, Object... params);

	/**
	 * ִ�� UPDATE ����, ���� INSERT(��û�з���ֵ), UPDATE, DELETE
	 * 
	 * @param sql
	 * @param params
	 */
	void update(String sql, Object... params);

	/**
	 * ִ�е�����¼�Ĳ�ѯ����, �������¼��Ӧ�����һ������
	 * 
	 * @param sql
	 * @param params
	 * @return ��¼��Ӧ����Ķ���
	 */
	T query(String sql, Object... params);

	/**
	 * ִ�ж�����¼�Ĳ�ѯ����, �������¼��Ӧ�����һ�� List
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	List<T> queryForList(String sql, Object... params);
	/**
	 * ִ��һ�����Ի�ֵ�Ĳ�ѯ����, �����ѯĳһ����¼��һ���ֶ�, ���ѯĳ��ͳ����Ϣ, ����Ҫ��ѯ��ֵ
	 * @param <V>
	 * @param sql
	 * @param params
	 * @return ִ��һ�����Ի�ֵ�Ĳ�ѯ����, �����ѯĳһ����¼��һ���ֶ�, ���ѯĳ��ͳ����Ϣ, ����Ҫ��ѯ��ֵ
	 */
	<V> V getSingleVal(String sql, Object... params);
	
	/**
	 * ִ���������²���
	 * @param sql
	 * @param params
	 */
	void batch(String sql,Object[]...params);
}
