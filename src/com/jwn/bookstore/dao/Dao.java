package com.jwn.bookstore.dao;

import java.util.List;

/**
 * Dao 接口, 定义 Dao 的基本操作, 由 BaseDao 提供实现.
 * 
 * @param <T>:
 *            Dao 实际操作的泛型类型
 */
public interface Dao<T>
{
	/**
	 * 执行 INSERT 操作, 返回插入后的记录的 ID
	 * 
	 * @param sql
	 * @param params
	 * @return 返回插入后的记录的ID
	 */
	long insert(String sql, Object... params);

	/**
	 * 执行 UPDATE 操作, 包括 INSERT(但没有返回值), UPDATE, DELETE
	 * 
	 * @param sql
	 * @param params
	 */
	void update(String sql, Object... params);

	/**
	 * 执行单条记录的查询操作, 返回与记录对应的类的一个对象
	 * 
	 * @param sql
	 * @param params
	 * @return 记录对应的类的对象
	 */
	T query(String sql, Object... params);

	/**
	 * 执行多条记录的查询操作, 返回与记录对应的类的一个 List
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	List<T> queryForList(String sql, Object... params);
	/**
	 * 执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	 * @param <V>
	 * @param sql
	 * @param params
	 * @return 执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	 */
	<V> V getSingleVal(String sql, Object... params);
	
	/**
	 * 执行批量更新操作
	 * @param sql
	 * @param params
	 */
	void batch(String sql,Object[]...params);
}
