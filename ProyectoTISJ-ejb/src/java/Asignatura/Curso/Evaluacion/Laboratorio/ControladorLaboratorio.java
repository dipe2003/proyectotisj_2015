package Asignatura.Curso.Evaluacion.Laboratorio;

import Asignatura.Curso.Curso;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorLaboratorio {
    
    @EJB
    ManejadorLaboratorio mLab;
    
    /**
     * Crea un Laboratorio y lo persiste.
     * @param FechaEvaluacion 
     * @param CursoLaboratorio 
     * @return Devuelve un Laboratorio si fue creado, de lo contrario devuelve null.
     */
    public Laboratorio CrearLaboratorio(Date FechaEvaluacion, Curso CursoLaboratorio){
        Laboratorio lab = new Laboratorio(FechaEvaluacion, CursoLaboratorio);
        if (mLab.CrearLaboratorio(lab)!=-1){
            return lab;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Laboratorio en la base de datos.
     * @param laboratorio
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarLaboratorio(Laboratorio laboratorio){
        return mLab.ModificarLaboratorio(laboratorio);
    }
    
    /**
     * Borra los datos de un Laboratorio en la base de datos.
     * @param laboratorio
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarLaboratorio(Laboratorio laboratorio){
        return mLab.BorrarLaboratorio(laboratorio);
    }
    
    /**
     * Busca un Laboratorio en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Laboratorio BuscarLaboratorio(int id){
        return mLab.BuscarLaboratorio(id);
    }
    
    /**
     * Devuelve una lista de Laboratorios desde la base de datos.
     * @return 
     */
    public List<Laboratorio> ListarLaboratorios(){
        return mLab.ListarLaboratorios();
    }
    
}
