
package interfaz.curso;


import Curso.Clase.Clase;
import Curso.Clase.FacadeClase;
import Curso.Curso;
import Curso.FacadeCurso;
import Estudiante.FacadeEstudiante;
import Usuario.Usuario;
import interfaz.Login;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarClasesBean implements Serializable{
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeEstudiante fEst;
    @EJB
    private FacadeClase fClase;
    @Inject
    private Login login;
    
    private List<Clase> Clases;
    
    private String opt;
    private String NombreCurso;
    
    //  Constructores
    public ListarClasesBean(){}
    
    //  Getters
    public List<Clase> getClases(){return Clases;}
    public String getOpt() {return this.opt;} 
    public String getNombreCurso(){return this.NombreCurso;}
    public int getTotalClases(){return this.Clases.size();}
    
    //  Setters
    public void setClases(List<Clase> Clases){this.Clases = Clases;}
    public void setOpt(String opt) {this.opt = opt;}
    
    @PostConstruct
    public void Init(){
        this.Clases = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try{
            this.opt = request.getParameter("opt");
        }catch(NullPointerException ex){
            this.opt = "no";
        }finally{
            if (this.opt == null) this.opt = "no";
        }
        if(!opt.equals("no")){
          Curso curso = fCurso.BuscarCurso(Integer.parseInt(opt));
          this.NombreCurso = curso.getAsignaturaCurso().getNombreAsignatura() + " " + curso.getAnioCurso();          
          this.Clases = fClase.ListarClases(Integer.parseInt(opt));
        }
        
    }
    
    
}
