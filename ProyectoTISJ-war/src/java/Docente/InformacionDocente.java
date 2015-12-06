
package Docente;

import Asignatura.Curso.Curso;
import Asignatura.Curso.FacadeCurso;
import Usuario.Docente.Docente;
import Usuario.Docente.FacadeDocente;
import Usuario.FacadeUsuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class InformacionDocente implements Serializable{
    @EJB
    private FacadeDocente fDoc;
    @EJB
    private FacadeUsuario fUsr;
    @EJB
    private FacadeCurso fCurso;
    
    private Docente DocenteSeleccionado;
    private int IdDocenteSeleccionado;
    private List<Curso> CursosDocente;
    
    //  Getters
    public Docente getDocenteSeleccionado() {return DocenteSeleccionado;}
    public int getIdDocenteSeleccionado() {return IdDocenteSeleccionado;}
    public List<Curso> getCursosDocente() {return CursosDocente;}
    
    //  Setters
    public void setDocenteSeleccionado(Docente DocenteSeleccionado) {this.DocenteSeleccionado = DocenteSeleccionado;}
    public void setIdDocenteSeleccionado(int IdDocenteSeleccionado) {this.IdDocenteSeleccionado = IdDocenteSeleccionado;}
    public void setCursosDocente(List<Curso> CursosDocente) {this.CursosDocente = CursosDocente;}
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try{
            IdDocenteSeleccionado = Integer.parseInt(request.getParameter("id"));
            DocenteSeleccionado = fDoc.GetDocente(IdDocenteSeleccionado);
        }catch(NullPointerException ex){}
        
        CursosDocente = fCurso.ListarCursosDocente(IdDocenteSeleccionado);
    }    
    
}
