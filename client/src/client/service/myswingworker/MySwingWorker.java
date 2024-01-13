/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service.myswingworker;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author ytxlo
 */
public class MySwingWorker extends SwingWorker<Boolean,Object>{
    private final MyService service;
    private final MyServiceDone servicedone;
    public MySwingWorker(MyService service,MyServiceDone servicedone) {
        this.service = service;
        this.servicedone = servicedone;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        return service.doMyWork();
    }

    @Override
    protected void done() {
        try {
            super.done(); //To change body of generated methods, choose Tools | Templates.
            if(get()){
                servicedone.myDone();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MySwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
