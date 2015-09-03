package Curso;

import Asignatura.Asignatura;
import Docente.Docente;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorCurso {
    
    @EJB
    ManejadorCurso mDoc;
    
    /**
     * Crea un Curso y lo persiste.
     * @param SemestreCurso  
     * @param AnioCurso 
     * @param DocenteCurso 
     * @param AsignaturaCurso
     * @return Devuelve un Curso si fue creado, de lo contrario devuelve null.
     */
    public Curso CrearCurso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso, String ContratoDocenteCurso){
        Curso curso = new Curso(SemestreCurso, AnioCurso, DocenteCurso, AsignaturaCurso, ContratoDocenteCurso);
        if (mDoc.CrearCurso(curso)!=-1){
            return curso;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Curso en la base de datos.
     * @param curso
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarCurso(Curso curso){
        return mDoc.ModificarCurso(curso);
    }
    
    /**
     * Borra los datos de un Curso en la base de datos.
     * @param curso
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarCurso(Curso curso){
        return mDoc.BorrarCurso(curso);
    }
    
    /**
     * Busca un Curso en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Curso BuscarCurso(int id){
        return mDoc.BuscarCurso(id);
    }
    
    /**
     * Devuelve una lista de Cursos desde la base de datos.
     * @return 
     */
    public List<Curso> ListarCursos(){
        return mDoc.ListarCursos();
    }
    
}
