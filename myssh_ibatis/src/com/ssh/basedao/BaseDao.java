package com.ssh.basedao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
/**
 * @ClassName: BaseDao
 * @Description: baseDaoʵ��
 * @author yz
 * @date 2014��6��16��17:09:52
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDao<T, ID extends Serializable> implements IBaseDao<T, ID> {
     
    @Autowired
    private SessionFactory sessionFactory;
    protected Class<T> entityClass;
 
    public BaseDao() {
 
    }
 
    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }
 
    /**
     * <����ʵ��>
     * <��������ʵ��>
     * @param t ʵ�����
     * @see com.itv.launcher.util.IBaseDao#save(java.lang.Object)
     */
    @Override
    public void save(T t) {
        this.getSession().save(t);
    }
     
    /**
     * <������߸���ʵ��>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#saveOrUpdate(java.lang.Object)
     */
    @Override
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
    }
     
    /**
     * <load>
     * <����ʵ���load����>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     * @see com.itv.launcher.util.IBaseDao#load(java.io.Serializable)
     */
    @Override
    public T load(ID id) {
        T load = (T) this.getSession().load(getEntityClass(), id);
        return load;
    }
     
    /**
     * <get>
     * <���ҵ�get����>
     * @param id ʵ���id
     * @return ��ѯ������ʵ��
     * @see com.itv.launcher.util.IBaseDao#get(java.io.Serializable)
     */
    @Override
    public T get(ID id) {
        T load = (T) this.getSession().get(getEntityClass(), id);
        return load;
    }
     
    /**
     * <contains>
     * @param t ʵ��
     * @return �Ƿ����
     * @see com.itv.launcher.util.IBaseDao#contains(java.lang.Object)
     */
    @Override
    public boolean contains(T t) {
        return this.getSession().contains(t);
    }
 
    /**
     * <delete>
     * <ɾ�����е�t����>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#delete(java.lang.Object)
     */
    @Override
    public void delete(T t) {
        this.getSession().delete(t);
    }
     
    /**
     * <����IDɾ������>
     * @param Id ʵ��id
     * @return �Ƿ�ɾ���ɹ�
     * @see com.itv.launcher.util.IBaseDao#deleteById(java.io.Serializable)
     */
    @Override
    public boolean deleteById(ID Id) {
         T t = get(Id);
         if(t == null){
             return false;
         }
         delete(t);
        return true;
    }
 
    /**
     * <ɾ������>
     * @param entities ʵ���Collection����
     * @see com.itv.launcher.util.IBaseDao#deleteAll(java.util.Collection)
     */
    @Override
    public void deleteAll(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
    }
 
    /**
     * <ִ��Hql���>
     * @param hqlString hql
     * @param values ������������
     * @see com.itv.launcher.util.IBaseDao#queryHql(java.lang.String, java.lang.Object[])
     */
    @Override
    public void queryHql(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
 
    /**
     * <ִ��Sql���>
     * @param sqlString sql
     * @param values ������������
     * @see com.itv.launcher.util.IBaseDao#querySql(java.lang.String, java.lang.Object[])
     */
    @Override
    public void querySql(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
 
    /**
     * <����HQL������Ψһʵ��>
     * @param hqlString HQL���
     * @param values ����������Object����
     * @return ��ѯʵ��
     * @see com.itv.launcher.util.IBaseDao#getByHQL(java.lang.String, java.lang.Object[])
     */
    @Override
    public T getByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }
 
    /**
     * <����SQL������Ψһʵ��>
     * @param sqlString SQL���
     * @param values ����������Object����
     * @return ��ѯʵ��
     * @see com.itv.launcher.util.IBaseDao#getBySQL(java.lang.String, java.lang.Object[])
     */
    @Override
    public T getBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }
 
    /**
     * <����HQL��䣬�õ���Ӧ��list>
     * @param hqlString HQL���
     * @param values ����������Object����
     * @return ��ѯ���ʵ���List����
     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
     */
    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
 
    /**
     * <����SQL��䣬�õ���Ӧ��list>
     * @param sqlString HQL���
     * @param values ����������Object����
     * @return ��ѯ���ʵ���List����
     * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String, java.lang.Object[])
     */
    @Override
    public List<T> getListBySQL(String sqlString, Object... values ) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
     
    /**
     * <refresh>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#refresh(java.lang.Object)
     */
    @Override
    public void refresh(T t) {
        this.getSession().refresh(t);
    }
 
    /**
     * <update>
     * @param t ʵ��
     * @see com.itv.launcher.util.IBaseDao#update(java.lang.Object)
     */
    @Override
    public void update(T t) {
        this.getSession().update(t);
    }
     
    /**
     * <����HQL�õ���¼��>
     * @param hql HQL���
     * @param values ����������Object����
     * @return ��¼����
     * @see com.itv.launcher.util.IBaseDao#countByHql(java.lang.String, java.lang.Object[])
     */
    @Override
    public Long countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
    }
 
 
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    /**
     * 
     * @return session
     */
    public Session getSession() {
        //��Ҫ����������ܵõ�CurrentSession
        return sessionFactory.getCurrentSession();
    }
     
    /**
     * 
     * ����ÿ�����������
     * 
     * @param ps
     * @param pos ?ռλ����������0��ʼ
     * @param data
     * @throws SQLException
     * @see [�ࡢ��#��������#��Ա]
     */
    private void setParameter(PreparedStatement ps, int pos, Object data)
        throws SQLException
    {
        if (data == null)
        {
            ps.setNull(pos + 1, Types.VARCHAR);
            return;
        }
        Class dataCls = data.getClass();
        if (String.class.equals(dataCls))
        {
            ps.setString(pos + 1, (String)data);
        }
        else if (boolean.class.equals(dataCls))
        {
            ps.setBoolean(pos + 1, ((Boolean)data));
        }
        else if (int.class.equals(dataCls))
        {
            ps.setInt(pos + 1, (Integer)data);
        }
        else if (double.class.equals(dataCls))
        {
            ps.setDouble(pos + 1, (Double)data);
        }
        else if (Date.class.equals(dataCls))
        {
            Date val = (Date)data;
            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        }
        else if (BigDecimal.class.equals(dataCls))
        {
            ps.setBigDecimal(pos + 1, (BigDecimal)data);
        }
        else
        {
            // δ֪����
            ps.setObject(pos + 1, data);
        }
         
    }
     
}