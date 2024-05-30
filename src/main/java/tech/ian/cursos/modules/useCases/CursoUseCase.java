package tech.ian.cursos.modules.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.cursos.exceptions.CourseFoundExeception;
import tech.ian.cursos.modules.entities.CursoEntity;
import tech.ian.cursos.modules.repositories.CursoRepository;

import java.util.List;

@Service
public class CursoUseCase {

    @Autowired
    private CursoRepository repository;

        public CursoEntity execute(CursoEntity cursoEntity) {
            repository.findByName(cursoEntity.getName())
                    .ifPresent((curso) -> {
                        throw new CourseFoundExeception();
                    });
            return repository.save(cursoEntity);
        }

    public CursoEntity findByNameAndCategory(String name, String category) {
        return repository.findByNameAndCategory(name, category)
                .orElseThrow(CourseFoundExeception::new);
    }

    public List<CursoEntity> findAllCourse() {
            return repository.findAll();
    }

    public CursoEntity updateById(CursoEntity cursoEntity) {
            return repository.save(cursoEntity);
    }

}
