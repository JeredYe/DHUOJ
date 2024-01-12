/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.ObjectRelation_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class CommonDAO {

    //增
    public static void add(Object bean) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(bean);
        tx.commit();
    }

    //删
    public static void delete(Class c, int id) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.flush();
        Query query = session.createQuery("delete " + c.getName() + " as bean where bean.id = ?");
        query.setInteger(0, id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    //改，update
    public static int updateDetailByHql(String status,int userId,int problemId,int examId,int solutionId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.flush();
        Query query = session.createQuery("update ExamDetailBean as d set d.status=? where d.userId=? and d.problemId=? and d.examId=? and d.solutionId=?");
        query.setString(0, status);
        query.setInteger(1, userId);
        query.setInteger(2, problemId);
        query.setInteger(3, examId);
        query.setInteger(4, solutionId);
        int count=query.executeUpdate();
        tx.commit();
        session.close();
        return count;
    }

    public static void update(Object bean) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(bean);
        tx.commit();
    }

    public static void updateMore(Object[] beans) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < beans.length; i++) {
            session.update(beans[i]);
        }
        tx.commit();
    }

    //查，取
    public static List<Object> findBeans(Class c, int maxNum, String key1, Object value1) {
        try {
            String hql = "from " + c.getName() + " as bean where bean." + key1 + "? " + "order by id asc";
            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query = session.createQuery(hql);

            query.setParameter(0, value1);

            query.setFirstResult(0);
            query.setMaxResults(maxNum);
            List<Object> rs = query.list();
            tx.commit();
            session.close();
            return rs;
        } catch (Exception ex) {
            return new ArrayList<Object>();
        }
    }

    public static List<Object> findBeans(Class c, int maxNum, String key1, Object value1, String key2, Object value2) {
        try {
            String hql = "from " + c.getName() + " as bean where bean." + key1 + "? bean." + key2 + "s?" + " order by id asc";
            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query = session.createQuery(hql);

            query.setParameter(0, value1);

            query.setFirstResult(0);
            query.setMaxResults(maxNum);
            List<Object> rs = query.list();
            tx.commit();
            session.close();
            return rs;
        } catch (Exception e) {
            return new ArrayList<Object>();
        }
    }

    public static List<Object> findBeans(Class c, int maxNum, Map<String, Object> map) {
        try {
            int len = map.size();
            String[] keys = new String[len];

            Set<String> keyss = map.keySet();
            int i = 0;
            for (String key : keyss) {
                keys[i] = key;
                i++;
            }

            String hql = "from " + c.getName() + " as bean where ";
            for (i = 0; i < len; i++) {
                hql += "bean." + keys[i] + "? ";
                if (i != len - 1) {
                    hql += " and ";
                }
            }
            hql += " order by id asc";

            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query = session.createQuery(hql);

            for (i = 0; i < len; i++) {
                Object o = map.get(keys[i]);
                query.setParameter(i, o);
            }

            query.setFirstResult(0);
            query.setMaxResults(maxNum);
            List<Object> rs = query.list();
            tx.commit();
            session.close();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }

    //for test
//    public static void main(String[] args) {
////        //增
////        ProblemBean pb=new ProblemBean();
////        pb.setTime_limit((float)7758521.0);
////        CommonDAO.add(pb);
//
////        //删
////        CommonDAO.delete(ProblemBean.class, 292);
//        //查询
////       List<Object> beans= CommonDAO.findBeans(ProblemBean.class, 5, "author", "卢婷");
////       System.out.println(beans.size());
////       List<Object> beans2= CommonDAO.findBeans(ProblemBean.class, 5, "id", 293);
////       System.out.println(beans2.size());
////       ProblemBean pbean=(ProblemBean)beans2.get(0);
////       if(pbean.getSubmit()==null){
////           System.out.println("submit:null");
////       }else{
////           System.out.println("submit:"+pbean.getSubmit());
////       }
////       HashMap<String,Object> map=new HashMap();
////       map.put("author", "卢婷");
////       List<Object> beans3=CommonDAO.findBeans(ProblemBean.class, 5, map);
////       System.out.println(beans3.size());
//        HashMap<String, Object> map = new HashMap();
//        map.put(new String("userId"), 1);
//        map.put(new String("examId"), 10);
//        map.put(new String("problemId"), 37);
//        ExamDetailBean examDetailBean = (ExamDetailBean) CommonDAO.findBeans(ExamDetailBean.class, 1, map).get(0);
//
//        //改，update
////       pbean.setSubmit(13);
////       CommonDAO.update(pbean);
//    }
}
