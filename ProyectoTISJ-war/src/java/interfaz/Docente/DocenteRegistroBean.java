
package interfaz.Docente;

import Usuario.FacadeUsuario;
import Utilidades.Cedula;
import Utilidades.FileUpload;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class DocenteRegistroBean implements Serializable{

    private String NombreDocente; 
    private Part ImagenDocente;
    private String CorreoDocente;
    private String CedulaDocente;
    private String PasswordDocente;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FileUpload fUp;
    
    @EJB
    private Cedula verifCedula;
    
    public DocenteRegistroBean() {}

    public String getNombreDocente() {return NombreDocente;}

    public void setNombreDocente(String NombreDocente) {this.NombreDocente = NombreDocente;}

    public Part getImagenDocente() {return ImagenDocente;}

    public void setImagenDocente(Part ImagenDocente) {this.ImagenDocente = ImagenDocente;}

    public String getCorreoDocente() {return CorreoDocente;}

    public void setCorreoDocente(String CorreoDocente) {this.CorreoDocente = CorreoDocente;}

    public String getCedulaDocente() {return CedulaDocente;}

    public void setCedulaDocente(String CedulaDocente) {this.CedulaDocente = CedulaDocente;}

    public String getPasswordDocente() {return PasswordDocente;}

    public void setPasswordDocente(String PasswordDocente) {this.PasswordDocente = PasswordDocente;}
               
    public String registrarDocente(){
        if (verifCedula.EsCedulaValida(CedulaDocente)) {
            String ubicacion = fUp.guardarArchivo("ImagenesPerfil", ImagenDocente, CedulaDocente);
            if (ubicacion!=null) {
                if(fUsr.RegistrarUsuario(NombreDocente, CorreoDocente, PasswordDocente, Integer.valueOf(CedulaDocente), "Docente", "" ,ubicacion )!=-1){
                    return "registrado";
                }
            }else{
                if(fUsr.RegistrarUsuario(NombreDocente, CorreoDocente, PasswordDocente, Integer.valueOf(CedulaDocente), "Docente", "" ,"" )!=-1){
                    return "registrado";
                }                
            }
        }
        return "";
    }
}
