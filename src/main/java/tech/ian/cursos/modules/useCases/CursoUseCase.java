package tech.ian.cursos.modules.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ian.cursos.exceptions.CurseFoundExeception;
import tech.ian.cursos.modules.dtos.CursoDto;
import tech.ian.cursos.modules.entities.CursoEntity;
import tech.ian.cursos.modules.repositories.CursoRepository;

@Service
public class CursoUseCase {

    @Autowired
    private CursoRepository repository;

        public CursoEntity execute(CursoEntity cursoEntity) {
            repository.findByNameOrCategory(cursoEntity.getName(), cursoEntity.getCategory())
                    .ifPresent((curso) -> {
                        throw new CurseFoundExeception();
                    });

            return repository.save(cursoEntity);
        }
}
