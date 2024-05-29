package tech.ian.cursos.modules.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ian.cursos.modules.entities.CursoEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

    Optional<CursoEntity> findByNameOrCategory(String name, String category);
}
