
package Utilidades;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.*;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

@Named
@Stateless
@SessionScoped
public class FileUpload implements Serializable{

    private String homeDir = System.getProperty("user.dir");
    private String separator = System.getProperty("file.separator");

    public FileUpload() {}    

    /**
     * Devuelve el valor correspondiente al directorio del usuario
     * @return directorio de usuario (home)
     */
    public String getHome(){return homeDir;}
    /**
     * Devuelve el valor del caracter separador ("/"-"\")
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
            Path path = Paths.get(homeDir+separator+DirectorioArchivo+separator);
            try{
                Files.createDirectory(path);
            }catch(Exception e){
                System.out.println("Error al subir el archivo: " + e.getMessage());
            }
            InputStream input = Archivo.getInputStream();
            String extensionArchivo = FilenameUtils.getExtension(Archivo.getSubmittedFileName());
            NombreArchivo += "."+extensionArchivo;
            path = Paths.get(path.toString()+separator+NombreArchivo);
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        }catch(Exception err){
            System.out.println("Error: "+err.getMessage());
        }
        return null;
    }
    
}
