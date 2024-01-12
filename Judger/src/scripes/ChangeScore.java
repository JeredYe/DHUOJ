/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import persistence.ObjectRelation_interface.CommonDAO;
import persistence.oj_beans.ExamDetailBean;
import persistence.oj_beans.ExamInfoBean;
import persistence.oj_beans.ExamProblemsBean;
import persistence.oj_beans.UserBean;

/**
 *
 * @author Administrator
 */
public class ChangeScore {

    static HashMap<String, Set<Integer>> students = new HashMap();

    static {
//        students.put("151320206", 36);
//        students.put("151320117", 15);
//        students.put("151320225", 31);
//        students.put("151320225", 35);
//
//        students.put("151320122", 10);
//        students.put("151320122", 12);
//
//        students.put("151320219", 35);
//
//        students.put("151320209", 5);
//        students.put("151320209", 7);
//        students.put("151320209", 10);
//
//        students.put("141210119", 28);
//        students.put("141210119", 36);
//
//        students.put("151320223", 34);
//
//        students.put("151320216", 14);
//
//        students.put("151320222", 1);
        Set problemSet = new HashSet();
        problemSet.add(28);
        problemSet.add(36);
        problemSet.add(42);
        problemSet.add(43);
        problemSet.add(44);
        problemSet.add(46);
        problemSet.add(48);
        problemSet.add(49);
        problemSet.add(50);
        problemSet.add(51);
        problemSet.add(54);
        students.put("141210119", problemSet);

    }

    public static void main(String[] args) {

        int count = 0;
        Set<String> keys = students.keySet();
        for (String key : keys) {
            String studentNo = key;
            Set<Integer> problemIds = students.get(key);
            //System.out.println();
            for(Integer playSequence:problemIds){
                updateOneStudent(studentNo, playSequence);
                count++;
            }
        }
        //System.out.println("count:" + count);
    }

    private static void updateOneStudent(String studentNo, int playSequence) {

        final int examId = 1;

        int userId = 0;
        int problemId = 0;
        String name = "";

        //get basic info
        HashMap<String, Object> inExamProblems = new HashMap();
        inExamProblems.put("examId=", 1);
        inExamProblems.put("displaySequence=", playSequence);
        List examProblemsList = CommonDAO.findBeans(ExamProblemsBean.class, 1, inExamProblems);
        problemId = ((ExamProblemsBean) (CommonDAO.findBeans(ExamProblemsBean.class, 1, inExamProblems).get(0))).getProblemId();
        HashMap inUser = new HashMap();
        inUser.put("studentNo=", studentNo);
        userId = ((UserBean) (CommonDAO.findBeans(UserBean.class, 1, inUser)).get(0)).getId();
        name = ((UserBean) (CommonDAO.findBeans(UserBean.class, 1, inUser)).get(0)).getChineseName();

//        System.out.println("update student name:" + name + "'s score");
//        System.out.println("userId=" + userId);
//        System.out.println("problemId=" + problemId);
        //update examDetail
        HashMap<String, Object> inExamDetail = new HashMap();
        inExamDetail.put("userId=", userId);
        inExamDetail.put("examId=", examId);
        inExamDetail.put("problemId=", problemId);
        ExamDetailBean examDetailBean = ((ExamDetailBean) (CommonDAO.findBeans(ExamDetailBean.class, 1, inExamDetail)).get(0));
        System.out.println("wrong score:" + examDetailBean.getScore());
        float preScore = examDetailBean.getScore();
        float delta = 1.0f - preScore;
        examDetailBean.setScore(1.0f);
        CommonDAO.update(examDetailBean);
        System.out.println("update " + name + "'s " + "examdetail table success ");

        //update examInfo
        HashMap<String, Object> inExamInfo = new HashMap();
        inExamInfo.put("userId=", userId);
        inExamInfo.put("examId=", examId);
        ExamInfoBean examInfoBean = ((ExamInfoBean) (CommonDAO.findBeans(ExamInfoBean.class, 1, inExamInfo)).get(0));
        System.out.println("before sumScore:" + examInfoBean.getScore());
        examInfoBean.setScore(examInfoBean.getScore() + delta);
        CommonDAO.update(examInfoBean);
        System.out.println("after update sumScore:" + examInfoBean.getScore());

    }
}
