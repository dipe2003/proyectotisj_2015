package Asignatura.Curso.Encuesta.Pregunta;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorPregunta {
    
    @EJB
    private ManejadorPregunta mPreg;
    
    /**
     * Crea una Pregunta y la persiste.
     * @param TextoPregunta
     * @param TipoPregunta
     * @return el id de la pregunta si fue creada, de lo contrario devuelve -1.
     */
    public int CrearPregunta(String TextoPregunta, EnumTipoPregunta TipoPregunta){
        Pregunta preg = new Pregunta(TextoPregunta, TipoPregunta);
        return mPreg.CrearPregunta(preg);
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
