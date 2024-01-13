 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.Problemlist;
import client.model.StudentExamDetail;
import client.model.ExamProblem;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ytxlo
 */
public class Compute_xml {
    public static List<Problemlist> getProblemlist(String examId){
        List<Problemlist> pll = new ArrayList();
        try{
        List<ExamProblem> epl = new ExamProblem_io(examId).getProblemList();
        List<StudentExamDetail> sedll = new StudentExamDetail_io(examId).getstudentExamDetaillist();
        ////System.out.println(sl.size());
        for(int i=0;i<epl.size();i++){
            String status = new String();
            for(int j=0;j<sedll.size();j++){
                if(sedll.get(j).getProblemId().equals(epl.get(i).getProblemId())){
                    status=sedll.get(j).getStatus();
                }
            }

            Problemlist pl = new Problemlist(epl.get(i).getProblemId(), examId, epl.get(i).getTitle(), epl.get(i).getDifficulty(), epl.get(i).getScore(), status,epl.get(i).getUpdateTime());
            pll.add(pl);
        }
        }catch(Exception e){
            //ignore
        }
        //System.out.println("ok");
        return pll;
    }
    
}
