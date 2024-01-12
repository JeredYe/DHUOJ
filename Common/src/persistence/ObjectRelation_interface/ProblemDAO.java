package persistence.ObjectRelation_interface;

import persistence.oj_beans.ProblemBean;


public class ProblemDAO {

    private static final Class problemClass = ProblemBean.class;

    public static ProblemBean findOne(String key, Object problemId) {
        return (ProblemBean) (CommonDAO.findBeans(problemClass, 1, key, problemId).get(0));
    }

    public static void update(ProblemBean pbean) {
        CommonDAO.update(pbean);
    }
}
