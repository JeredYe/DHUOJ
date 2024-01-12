/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.ObjectRelation_interface;

import java.util.List;
import java.util.Map;
import persistence.oj_beans.WrongCaseBean;


/**
 *
 * @author Administrator
 */
public class WrongCaseDAO {

    private static final Class wrongcaseClass = WrongCaseBean.class;

    public static void update(WrongCaseBean wbean) {
        CommonDAO.update(wbean);
    }

    public static void add(WrongCaseBean wbean) {
        CommonDAO.add(wbean);
    }

    public static WrongCaseBean findOne(Map map) {
        List wrongcases = CommonDAO.findBeans(wrongcaseClass, 1, map);
        if (wrongcases.size() != 0) {
            return (WrongCaseBean) (wrongcases.get(0));
        } else {
            return null;
        }

    }
}
