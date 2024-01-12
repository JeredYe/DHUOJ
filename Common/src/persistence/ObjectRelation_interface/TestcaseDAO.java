/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.ObjectRelation_interface;

import java.util.ArrayList;
import java.util.List;
import persistence.oj_beans.ProblemTestCaseBean;

/**
 *
 * @author Administrator
 */
public class TestcaseDAO {

    private static final Class testcaseClass = ProblemTestCaseBean.class;

    public static List<ProblemTestCaseBean> findMore(String key, Object value,int maxNum) {
        List<Object> list = CommonDAO.findBeans(testcaseClass, maxNum, key, value);
        List<ProblemTestCaseBean> list1 = new ArrayList();
        for (Object o : list) {
            list1.add((ProblemTestCaseBean) o);
        }
        return list1;
    }
}
