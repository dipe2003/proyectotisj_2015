package Encuesta;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

public class ControladorEncuesta {
    
    @EJB
    ManejadorEncuesta mEnc;
    
    /**
     * Crea una Encuesta y la persiste.
     * @param FechaEncuesta 
     * @param IdAsignatura 
     * @return Devuelve una Encuesta si fue creada, de lo contrario devuelve null.
     */
    public Encuesta CrearEncuesta(Date FechaEncuesta, int IdAsignatura){
        Encuesta enc = new Encuesta(FechaEncuesta, IdAsignatura);
        if (mEnc.CrearEncuesta(enc)!=-1){
            return enc;
        }
        return null;
    }
    
    /**
     * Modifica los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEncuesta(Encuesta encuesta){
        return mEnc.ModificarEncuesta(encuesta);
    }
    
    /**
     * Borra los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEncuesta(Encuesta encuesta){
        return mEnc.BorrarEncuesta(encuesta);
    }
    
    /**
     * Busca una Encuesta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Encuesta BuscarEncuesta(int id){
        return mEnc.BuscarEncuesta(id);
    }
    
    /**
     * Devuelve una lista de Encuestas desde la base de datos.
     * @return 
     */
    public List<Encuesta> ListarEncuestas(){
        return mEnc.ListarEncuestas();
    }
    
}