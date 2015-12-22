
package interfaz.curso;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ManagedBean
@ConversationScoped
public class RegCursoWizard implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idAsignatura;
    private int idDocente;
    
    @Inject
    private Conversation conversation;
    
    public Conversation getConversation() {
        return conversation;
    }
    
    public void initConversation(){
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public void endConversation() throws IOException{
        if(!conversation.isTransient()){
            conversation.end();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("../Curso/ListarCursos.xhtml");
    }
    
    // Navigation
    public void seleccionarAsignatura(String idAsig) throws IOException {
        this.idAsignatura = Integer.parseInt(idAsig);
        if(this.idAsignatura==0 || conversation == null) {
            FacesMessage msj = new FacesMessage("No se selecciono Asignatura", "No se selecciono Asignatura");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:btnRegCurso", msj);
            FacesContext.getCurrentInstance().renderResponse();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("../Usuario/ListarUsuarios.xhtml?faces-redirect=true&rol=Docente&opt=regcurso&cid="+this.conversation.getId());
    }
    
    public void seleccionarDocente(String idDoc) throws IOException {
        this.idDocente = Integer.parseInt(idDoc);
        if(this.idDocente==0 || conversation == null) {
           FacesMessage msj = new FacesMessage("No se selecciono Docente", "No se selecciono Docente");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:btnRegCurso", msj);
            FacesContext.getCurrentInstance().renderResponse();
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("../Curso/RegistrarCurso.xhtml?cid="+this.conversation.getId());
    }
    
    //  Getters    
    public int getIdAsignatura() {return idAsignatura;}
    public int getIdDocente() {return idDocente;}
    
    //  Setters    
    public void setIdAsignatura(int IdAsignatura) {this.idAsignatura = IdAsignatura;}
    public void setIdDocente(int IdDocente) {this.idDocente = IdDocente;}

    @PostConstruct
    public void init(){
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            conversation.begin();
        }
    }
}
