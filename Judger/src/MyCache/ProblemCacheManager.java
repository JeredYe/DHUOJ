/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyCache;

import persistence.ObjectRelation_interface.ProblemDAO;
import persistence.ObjectRelation_interface.TestcaseDAO;


/**
 *
 * @author Administrator
 */
public class ProblemCacheManager {
    public static int count=0;
    public static int hitCount=0;
    public static int nohitCount=0;
    public static int hitButOutdateCount=0;
    

    private static Problem generateProblemFromDB(Integer id) {
        Problem p = new Problem();
        p.setEntryTime(System.currentTimeMillis());
        p.setProblemId(id);
        p.setPbean(ProblemDAO.findOne("id=", id));
        p.setTestcases(TestcaseDAO.findMore("problemId=", id,100));
        return p;
    }

    public static Problem getOneProblem(Integer id) {
        count++;

//        if (Cache.contains(id)) {//�����а���
//            Problem p = Cache.getOneProblem(id);
//            //�ж��Ƿ����
//            if ((System.currentTimeMillis() - p.getEntryTime()) > 600000) {//����
//                hitButOutdateCount++;
//                //1.���»���
//                Problem new_p = generateProblemFromDB(id);
//                Cache.putOneProblem(new_p);
//                //2.����
//                return new_p;
//            } else {//û�й���
//                //ֱ�ӷ��ػ����е�Problem
//                hitCount++;
//                return Cache.getOneProblem(id);
//            }
//
//        } else {//������û��
            //1.���»���
            nohitCount++;
            Problem new_p = generateProblemFromDB(id);
            Cache.putOneProblem(new_p);
            //2.����
            return new_p;
       // }
    }
}
