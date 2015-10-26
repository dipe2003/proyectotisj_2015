
package Evaluacion;

import Curso.Curso;
import Examen.Examen;
import Laboratorio.Laboratorio;
import Parcial.Parcial;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEvaluacion {
    @EJB
    ManejadorEvaluacion mEva;  
    
    /**
     * Modifica los datos de un Evaluacion en la base de datos.
     * @param evaluacion
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEvaluacion(Evaluacion evaluacion){
        return mEva.ModificarEvaluacion(evaluacion);
    }
    
    /**
     * Borra un Evaluacion de la base de datos.
     * @param evaluacion
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEvaluacion(Evaluacion evaluacion){
        return mEva.BorrarEvaluacion(evaluacion);
    }
    
    /**
     * Busca una Evaluacion en la base de datos.
     * @param id
     * @return Devuelve null si la Evaluacion no se pudo encontrar.
     */
    public Evaluacion BuscarEvaluacion(int id){
        return mEva.BuscarEvaluacion(id);
    }
    
    /**
     * Devuelve una lista de Evaluacions desde la base de datos.
     * @return 
     */
    public List<Evaluacion> ListarEvaluaciones(){
        return mEva.ListarEvaluaciones();
    }
}
