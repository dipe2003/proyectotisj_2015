
package interfaz.Docente;

import Usuario.FacadeUsuario;
import Utilidades.FileUpload;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class DocenteRegistroBean implements Serializable{

    private String NickDocente;
    private String NombreDocente; 
    private Part ImagenDocente;
    private String CorreoDocente;
    private int CedulaDocente;
    private String PasswordDocente;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FileUpload fUp;
    
    public DocenteRegistroBean() {}

    public String getNickDocente() {return NickDocente;}

    public void setNickDocente(String NickDocente) {this.NickDocente = NickDocente;}

    public String getNombreDocente() {return NombreDocente;}

    public void setNombreDocente(String NombreDocente) {this.NombreDocente = NombreDocente;}

    public Part getImagenDocente() {return ImagenDocente;}

    public void setImagenDocente(Part ImagenDocente) {this.ImagenDocente = ImagenDocente;}

    public String getCorreoDocente() {return CorreoDocente;}

    public void setCorreoDocente(String CorreoDocente) {this.CorreoDocente = CorreoDocente;}

    public int getCedulaDocente() {return CedulaDocente;}

    public void setCedulaDocente(int CedulaDocente) {this.CedulaDocente = CedulaDocente;}

    public String getPasswordDocente() {return PasswordDocente;}

    public void setPasswordDocente(String PasswordDocente) {this.PasswordDocente = PasswordDocente;}
               
    public String registrarDocente(){
        String ubicacion = fUp.guardarArchivo("ImagenesPerfil", ImagenDocente, Integer.toString(CedulaDocente));
        if (ubicacion!=null) {
            if(fUsr.RegistrarUsuario(NickDocente, NombreDocente, CorreoDocente, PasswordDocente, CedulaDocente, "Docente", "" ,ubicacion )!=-1){
                return "registrado";
            }
        }else{
            if(fUsr.RegistrarUsuario(NickDocente, NombreDocente, CorreoDocente, PasswordDocente, CedulaDocente, "Docente", "" ,"ubicacion_imagen" )!=-1){
                return "registrado";
            }                
        }
        return "";
    }
}
