
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProgressBarView implements Serializable {
     
    private Integer progress1;
    private Integer progress2;
 
    public Integer getProgress1() {
        progress1 = updateProgress(progress1);
        return progress1;
    }
 
    public Integer getProgress2() {
        progress2 = updateProgress(progress2);
        return progress2;
    }
 
    public void longRunning() throws InterruptedException {
        progress2 = 0;
        while (progress2 == null || progress2 < 100) {
            progress2 = updateProgress(progress2);
            Thread.sleep(500);
        }
    }
 
    private Integer updateProgress(Integer progress) {
        if(progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int)(Math.random() * 35);
             
            if(progress > 100)
                progress = 100;
        }
         
        return progress;
    }
 
    public void setProgress1(Integer progress1) {
        this.progress1 = progress1;
    }
 
    public void setProgress2(Integer progress2) {
        this.progress2 = progress2;
    }
 
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }
 
    public void cancel() {
        progress1 = null;
        progress2 = null;
    }
}