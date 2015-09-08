
package interfaz.Estudiante;

import Estudiante.Estudiante;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class EstudianteBean implements Serializable{

    private String NombreEstudiante; 
    private String ImagenEstudiante;
    private String CorreoEstudiante;
    private String CedulaEstudiante;
    private String FormInscripcion;
    
    public EstudianteBean() {}

    public String getNombreEstudiante() {return NombreEstudiante;}

    public void setNombreEstudiante(String NombreEstudiante) {this.NombreEstudiante = NombreEstudiante;}

    public String getImagenEstudiante() {return ImagenEstudiante;}

    public void setImagenEstudiante(String ImagenEstudiante) {this.ImagenEstudiante = ImagenEstudiante;}

    public String getCorreoEstudiante() {return CorreoEstudiante;}

    public void setCorreoEstudiante(String CorreoEstudiante) {this.CorreoEstudiante = CorreoEstudiante;}

    public String getCedulaEstudiante() {return CedulaEstudiante;}

    public void setCedulaEstudiante(String CedulaEstudiante) {this.CedulaEstudiante = CedulaEstudiante;}

    public String getFormInscripcion() {return FormInscripcion;}

    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Estudiante  Est = (Estudiante)request.getSession().getAttribute("Estudiante");
        this.NombreEstudiante = Est.getNombreUsuario();
        this.ImagenEstudiante = Est.getImagenURL();
        this.CorreoEstudiante = Est.getCorreoUsuario();
        this.CedulaEstudiante = String.valueOf(Est.getCedulaUsuario());
        this.FormInscripcion = Est.getFormInscripcion();
    }
    
    
}
