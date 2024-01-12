/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.ObjectRelation_interface;

import java.util.Map;
import persistence.oj_beans.ExamDetailBean;

/**
 *
 * @author Administrator
 */
public class ExamDetailDAO {
    private static final Class detailClass=ExamDetailBean.class;

    public static ExamDetailBean findOne(Map map){
        return (ExamDetailBean)(CommonDAO.findBeans(detailClass, 1, map).get(0));
    }
    
    public static void update(ExamDetailBean bean){
        CommonDAO.update(bean);
    }
    
    public static void add(ExamDetailBean bean){
        CommonDAO.add(bean);
    }
}
