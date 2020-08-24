package Model;

public class Mail {

    

    private String toMail;
   
    private String message;
        
    private String codigo;
    
    private String cuerpo;
   
    public Mail() {
    }

    public Mail(String toMail, String message, String codigo, String cuerpo) {
        this.toMail = toMail;
        this.message = message;
        this.codigo = codigo;
        this.cuerpo = cuerpo;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    
    
    
    
    
}
