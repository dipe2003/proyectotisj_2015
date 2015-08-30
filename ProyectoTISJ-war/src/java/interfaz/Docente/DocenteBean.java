
package interfaz.Docente;

import Docente.Docente;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class DocenteBean implements Serializable{
    
    private String NickDocente;
    private String NombreDocente; 
    private String ImagenDocente;
    private String CorreoDocente;
    private int CedulaDocente;
    private String ContratoDocente;
    
    public DocenteBean() {}

    public String getNickDocente() {return NickDocente;}

    public void setNickDocente(String NickDocente) {this.NickDocente = NickDocente;}

    public String getNombreDocente() {return NombreDocente;}

    public void setNombreDocente(String NombreDocente) {this.NombreDocente = NombreDocente;}

    public String getImagenDocente() {return ImagenDocente;}

    public void setImagenDocente(String ImagenDocente) {this.ImagenDocente = ImagenDocente;}

    public String getCorreoDocente() {return CorreoDocente;}

    public void setCorreoDocente(String CorreoDocente) {this.CorreoDocente = CorreoDocente;}

    public int getCedulaDocente() {return CedulaDocente;}

    public void setCedulaDocente(int CedulaDocente) {this.CedulaDocente = CedulaDocente;}

    public String getContratoDocente() {return ContratoDocente;}

    public void setContratoDocente(String ContratoDocente) {this.ContratoDocente = ContratoDocente;}
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Docente  Doc = (Docente)request.getSession().getAttribute("Docente");
        this.NickDocente = Doc.getNickUsuario();
        this.NombreDocente = Doc.getNombreUsuario();
        this.ImagenDocente = Doc.getImagenURL();
        this.CorreoDocente = Doc.getCorreoUsuario();
        this.CedulaDocente = Doc.getCedulaUsuario();
        this.ContratoDocente = Doc.getContratoDocente();
    }
    
    
}
