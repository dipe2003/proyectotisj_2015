
package interfaz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

@Named
@Stateless
@SessionScoped
public class FileUpload implements Serializable{
    
    private String homeDir = System.getProperty("user.home");
    private String separator = System.getProperty("file.separator");
    
    public FileUpload() {}
    
    /**
     * Devuelve el valor correspondiente al directorio del usuario
     * @return directorio de usuario (home)
     */
    public String getHome(){return homeDir;}
    
    /**
     * Devuelve el valor del caracter separador ("/"-"\") del sistema operativo del host
     * @return caracter separador
     */
    public String getSeparator(){return separator;}
    
    /**
     * Crea el directorio (si no existe) y guarda el archivo. <b>Si existe el archivo se sobreescribe</b>
     * @param Archivo
     * @param DirectorioArchivo
     * @param NombreArchivo Nombre delk archivo extension
     * @return Devuelve la ubicacion del archivo guardado. Null si no se pudo guardar.
     */
    public String guardarArchivo(String DirectorioArchivo, Part Archivo, String NombreArchivo){
        try{
            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String resPath = "Tecnoinf"+separator+"Resources"+separator+"Images"+separator+DirectorioArchivo+separator;
            String extensionArchivo = FilenameUtils.getExtension(Archivo.getSubmittedFileName());
            NombreArchivo += "."+extensionArchivo;
            String realPath = homeDir+separator+resPath+NombreArchivo;
            Archivo.write(realPath);
            return realPath;
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no econtrado: " + ex.getMessage());
        }catch(IOException ex){
            System.out.println("Error IO: " + ex.getMessage());
        }
        return null;
    }
    
}
