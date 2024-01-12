package persistence.ObjectRelation_interface;

import java.util.ArrayList;
import java.util.List;
import persistence.oj_beans.SolutionBean;

public class SolutionDAO {

    private static final Class solutionClass = SolutionBean.class;

    public static List<SolutionBean> findMore(String key, Object value, int maxNum) {
        List<Object> list = CommonDAO.findBeans(solutionClass, maxNum, key, value);
        List<SolutionBean> list1 = new ArrayList();
        for (Object o : list) {
            list1.add((SolutionBean) o);
        }
        return list1;
    }

    public static void update(SolutionBean sbean) {
        CommonDAO.update(sbean);
    }

    public static void main(String[] args) {
        List<SolutionBean> slists = SolutionDAO.findMore("id=", null, 1);
        System.out.println(slists);
    }
}
