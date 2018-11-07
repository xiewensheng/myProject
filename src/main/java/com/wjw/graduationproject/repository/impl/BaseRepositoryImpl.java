package com.wjw.graduationproject.repository.impl;

import com.wjw.graduationproject.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author XWS
 * @ClassName BaseRepositoryImpl
 * @Description 自定义Repository实现
 * @create 2018-11-07 14:45
 * @Version 1.0
 */

@Repository
public class BaseRepositoryImpl<T> implements BaseRepository<T> {


  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<T> findByHql(String hql) {
    Query query = entityManager.createQuery(hql);
    return query.getResultList();
  }

  @Override
  public List<T> findByHql(String hql, Map<String, Object> params) {
    Query query = entityManager.createQuery(hql);
    Set<String> keys = params.keySet();
    for (String keyItem : keys) {
      query.setParameter(keyItem, params.get(keyItem));
    }

    return query.getResultList();
  }

  @Override
  public List<T> findByHql(String hql, int pageNo, int pageSize) {
    Query query = entityManager.createQuery(hql);
    return query.setFirstResult(pageNo * pageSize).setMaxResults(pageSize).getResultList();
  }

  @Override
  public List<T> findByHql(String hql, int pageNo, int pageSize, Map<String, Object> params) {
    Query query = entityManager.createQuery(hql);
    Set<String> keys = params.keySet();
    for (String keyItem : keys) {
      query.setParameter(keyItem, params.get(keyItem));
    }
    return query.setFirstResult(pageNo * pageSize).setMaxResults(pageSize).getResultList();
  }

  @Override
  public List<T> findBySql(String sql, Class class1, Object[] oParams) {
    Query query = entityManager.createNativeQuery(sql, class1);
    List<T> resultList;
    if (oParams != null){
      for(int i = 0; i < oParams.length; i ++) {
        query.setParameter(i+1, oParams[i]);
      }
    }
    try {
      resultList = query.getResultList();
    }catch (Exception e) {
      e.printStackTrace();
      resultList = null;
    }
    return resultList;
  }

  @Override
  public T findFirstBySql(String sql,Class class1, Object[] oParams) {
    Query query = entityManager.createNativeQuery(sql,class1);
    Object object;
    if (oParams != null){
      for(int i = 0; i < oParams.length; i ++) {
        query.setParameter(i+1, oParams[i]);
      }
    }
    try {
      object = query.getSingleResult();
    }catch (Exception e) {
      e.printStackTrace();
      object = null;
    }
    return (T)object;
  }

  @Override
  public List<T> findBySql(String sql, Object[] oParams) {
    Query query = entityManager.createNativeQuery(sql);
    List<T> resultList;
    if (oParams != null){
      for(int i = 0; i < oParams.length; i ++) {
        query.setParameter(i+1, oParams[i]);
      }
    }
    try {
      resultList = query.getResultList();
    }catch (Exception e) {
      e.printStackTrace();
      resultList = null;
    }
    return resultList;
  }

  @Override
  public int updateBySql(String sql) {
    Query query = entityManager.createNativeQuery(sql);
    int result;
    try {
      result = query.executeUpdate();
    }catch (Exception e) {
      e.printStackTrace();
      result = 0;
    }
    return result;
  }

  @Override
  public int updateBySql(String sql, Object[] oParams) {
    Query query = entityManager.createNativeQuery(sql);
    int result;
    if (oParams != null){
      for(int i = 0; i < oParams.length; i ++) {
        query.setParameter(i+1, oParams[i]);
      }
    }
    try {
      result = query.executeUpdate();
    }catch (Exception e) {
      e.printStackTrace();
      result = 0;
    }
    return result;
  }


}
