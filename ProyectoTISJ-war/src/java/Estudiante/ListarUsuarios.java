
package Estudiante;

import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import Usuario.Docente.Docente;
import Usuario.Docente.FacadeDocente;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarUsuarios implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    @EJB
    private FacadeDocente fDoc;
    
    private List<Usuario> Usuarios;
    
    private String Tipo;

    public String getTipo() {return Tipo;}
    public List<Usuario> getUsuarios() {return Usuarios;}

    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setTipo(String Tipo) {this.Tipo = Tipo;}
    
    //  Constructores
    public ListarUsuarios(){}
    
    
    @PostConstruct
    public void Init(){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Tipo = request.getParameter("tipo");
            Usuario usr = (Usuario) request.getSession().getAttribute("Usuario");
            if (Tipo.equalsIgnoreCase("Estudiante")){
                Usuarios = (List<Usuario>) (ArrayList<?>) fEst.ListarCompanierosDeClases(usr.getIdUsuario());
            }else if (Tipo.equalsIgnoreCase("Docente")){
              Usuarios = (List<Usuario>) (ArrayList<?>) fDoc.ListarProfesoresDeEstudiante(usr.getIdUsuario());
            }
            
    }
    
    
}
