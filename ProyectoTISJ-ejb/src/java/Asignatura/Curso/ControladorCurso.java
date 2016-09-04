package Asignatura.Curso;

import Asignatura.Asignatura;
import Usuario.Docente.Docente;
import Usuario.Estudiante.Estudiante;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorCurso {
    
    @EJB
    private ManejadorCurso mCurso;

    /**
     * Crea un Curso y lo persiste.
     * @param SemestreCurso
     * @param AnioCurso
     * @param DocenteCurso
     * @param AsignaturaCurso
     * @param ContratoDocenteCurso
     * @return Devuelve un Curso si fue creado, de lo contrario devuelve null.
     */
    public Curso CrearCurso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso, String ContratoDocenteCurso){
        Curso curso = new Curso(SemestreCurso, AnioCurso, DocenteCurso, AsignaturaCurso, ContratoDocenteCurso);
        if (mCurso.CrearCurso(curso)!=-1){
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
        return mCurso.ModificarCurso(curso);
    }
    
    /**
     * Borra los datos de un Curso en la base de datos.
     * @param curso
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarCurso(Curso curso){
        return mCurso.BorrarCurso(curso);
    }
    
    /**
     * Busca un Curso en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Curso BuscarCurso(int id){
        return mCurso.BuscarCurso(id);
    }
    
    /**
     * Devuelve una lista de Cursos desde la base de datos.
     * @return
     */
    public List<Curso> ListarCursos(){
        return mCurso.ListarCursos();
    }
    /**
     * Devuelve una lista de Cursos del usuario (docente o estudiante) especificado por su id desde la base de datos.
     * @param IdUsuario
     * @param docente true si es docente
     * @return
     */
    public List<Curso> ListarCursos(int IdUsuario, boolean docente){
        return mCurso.ListarCursos(IdUsuario, docente);
    }
    /**
     * Agrega el estudiante al curso especificado.
     * @param estudiante
     * @param IdCurso
     * @return
     */
    public Curso AgregarEstudianteACurso(Estudiante estudiante, int IdCurso){
        Curso curso = mCurso.BuscarCurso(IdCurso);
        curso.addEstudianteCurso(estudiante);
        mCurso.ModificarCurso(curso);
        return curso;
    }
    
    public List<String> getAniosCursos(){
        List<Integer> anios = mCurso.getAniosCursos();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < anios.size(); i++) {
            result.add(String.valueOf(anios.get(i)));
        }
        return result;
    }
    
    /**
     * Calcula la fecha actual y devuelve los cursos que se estan dictando.
     * @return
     */
    public List<Curso> GetCursosActuales(){
        int Anio = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH)+1;
        List<Curso> cursos = mCurso.FiltrarCursos(Anio);
        List<Curso> lista = new ArrayList<>();
        
        for(Curso curso: cursos){
            int semestre = curso.getSemestreCurso();
            if(mes > 7){
                if(semestre%2==0){
                    if(curso.getEncuestaCurso()==null)lista.add(curso);
                }
            }else{
                if(semestre%2!=0){
                    if(curso.getEncuestaCurso()==null)lista.add(curso);
                }
            }
        }
        return lista;
    }
    
    public List<Curso> filtrarCursos(int anioFilter, int semestreFilter, int idAsignatura){
        return mCurso.filtrarCursos(anioFilter, semestreFilter, idAsignatura);
    }
       
}
