
package Utilidades;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.*;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

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
     * Devuelve el valor del caracter separador ("/"-"\")
     * @return caracter separador
     */
    public String getSeparator(){return separator;}
    /**
     * Crea el directorio y el archivo de propiedades si estos no estan creados.
     * @param Archivo 
     * @param DirectorioArchivo 
     */
    public void guardarArchivo(String DirectorioArchivo, Part Archivo){
        try{
            Path path = Paths.get(homeDir+separator+DirectorioArchivo+separator);
            try{
                Files.createDirectory(path);
            }catch(Exception e){
                System.out.println("Error al subir el archivo: " + e.getMessage());
            }
            InputStream input = Archivo.getInputStream();
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception err){
            System.out.println("Error: "+err.getMessage());
        }
    }
    
}
