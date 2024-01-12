/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripes;
import common.Const;
import java.util.List;
import persistence.ObjectRelation_interface.SolutionDAO;
import persistence.oj_beans.SolutionBean;

/**
 *
 * @author Administrator
 */
public class ResetStatus {
    public static void main(String[] args){
        int count=0;
        while(true){
            List<SolutionBean> sbeans=SolutionDAO.findMore("status!=", "WAIT",10);
            if(sbeans.size()!=0){
                  SolutionBean sbean=sbeans.get(0);
                  sbean.setStatus(Const.STATUS[Const.WAIT]);
                  SolutionDAO.update(sbean);
                  System.out.println("update solution id="+sbean.getId());
                  count++;
            }else{
                break;
            }
            
        }
        System.out.println("count:"+count);
        
    }
    
}
