
package Asignatura.Curso.Evaluacion;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Examen.Examen;
import Asignatura.Curso.Evaluacion.Laboratorio.Laboratorio;
import Asignatura.Curso.Evaluacion.Parcial.Parcial;
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
    
    public List<Evaluacion> getEvaluacionesPorCurso(int idCurso){
        return mEva.getEvaluacionesPorCurso(idCurso);
    }
    
    public List<Evaluacion> getEvaluacionPorEstudiante(int idEstudiante){
        return mEva.getEvaluacionPorEstudiante(idEstudiante);
    }
    
}
