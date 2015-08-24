package Pregunta;

import java.util.List;
import javax.ejb.EJB;

public class ControladorPregunta {
    
    @EJB
    ManejadorPregunta mPreg;
    
    /**
     * Crea una Pregunta y la persiste.
     * @param ResultadoPregunta 
     * @param TextoPregunta 
     * @param TipoPregunta 
     * @return Devuelve una Pregunta si fue creada, de lo contrario devuelve null.
     */
    public Pregunta CrearPregunta(String TextoPregunta, int ResultadoPregunta, EnumTipoPregunta TipoPregunta){
        Pregunta preg = new Pregunta(TextoPregunta, ResultadoPregunta, TipoPregunta);
        if (mPreg.CrearPregunta(preg)!=-1){
            return preg;
        }
        return null;
    }
    
    /**
     * Modifica los datos de una Pregunta en la base de datos.
     * @param pregunta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarPregunta(Pregunta pregunta){
        return mPreg.ModificarPregunta(pregunta);
    }
    
    /**
     * Borra los datos de una Pregunta en la base de datos.
     * @param pregunta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarPregunta(Pregunta pregunta){
        return mPreg.BorrarPregunta(pregunta);
    }
    
    /**
     * Busca una Pregunta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Pregunta BuscarPregunta(int id){
        return mPreg.BuscarPregunta(id);
    }
    
    /**
     * Devuelve una lista de Preguntas desde la base de datos.
     * @return 
     */
    public List<Pregunta> ListarPreguntas(){
        return mPreg.ListarPreguntas();
    }
    
}
