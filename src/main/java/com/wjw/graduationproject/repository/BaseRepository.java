package com.wjw.graduationproject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 自定义
 *
 * @author XWS
 * @create 2018-11-07 14:42
 */

public interface BaseRepository<T> {

  /**
   *
   * @param hql
   * @return
   */
  public List<T> findByHql(String hql);

  /**
   *
   * @param hql
   * @param params
   * @return
   */
  public List<T> findByHql(String hql,Map<String, Object> params);

  /**
   *
   * @param hql
   * @param pageNo
   * @param pageSize
   * @return
   */
  public List<T> findByHql(String hql,int pageNo, int pageSize);

  /**
   *
   * @param hql
   * @param pageNo
   * @param pageSize
   * @param params
   * @return
   */
  public List<T> findByHql(String hql,int pageNo, int pageSize,Map<String, Object> params);


  /**
   * 自定义sql查询
   * @param sql
   * @param class1
   * @param oParams
   * @return
   */
  public List<T> findBySql(String sql,Class class1,Object[] oParams);

  /**
   * 自定义sql查询
   * @param sql
   * @param oParams
   * @return
   */
  public T findFirstBySql(String sql,Class class1,Object[] oParams);

  /**
   * 自定义sql查询
   * @param sql
   * @param oParams
   * @return
   */
  public List<T> findBySql(String sql,Object[] oParams);

  /**
   * 自定义sql更新
   * @param sql
   * @return
   */
  @Modifying
  @Transactional
  public int updateBySql(String sql);

  /**
   * 自定义sql更新
   * @param sql
   * @param oParams
   * @return
   */
  @Modifying
  @Transactional
  public int updateBySql(String sql,Object[] oParams);
}
