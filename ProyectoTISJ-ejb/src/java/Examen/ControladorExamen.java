
package Examen;

import Curso.Curso;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;


public class ControladorExamen {
    @EJB
    ManejadorExamen mExamen;
    
    /**
     * Crea un Examen y lo persiste.
     * @param FechaEvaluacion 
     * @param CursoExamen
     * @return Devuelve un Examen si fue creado, de lo contrario devuelve null.
     */
    public Examen CrearExamen(Date FechaEvaluacion, Curso CursoExamen){
        Examen examen = new Examen(FechaEvaluacion, CursoExamen);
        if (mExamen.CrearExamen(examen)!=-1) {
            return examen;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Examen en la base de datos.
     * @param examen
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarExamen(Examen examen){
        return mExamen.ModificarExamen(examen);
    }
    
    /**
     * Borra un Examen de la base de datos.
     * @param examen
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarExamen(Examen examen){
        return mExamen.BorrarExamen(examen);
    }
    
    /**
     * Busca un Examen en la base de datos.
     * @param id
     * @return Devuelve null si el Examen no se pudo encontrar.
     */
    public Examen BuscarExamen(int id){
        return mExamen.BuscarExamen(id);
    }
    
    /**
     * Devuelve una lista de Examenes desde la base de datos.
     * @return 
     */
    public List<Examen> ListarExamenes(){
        return mExamen.ListarExamenes();
    }
}
