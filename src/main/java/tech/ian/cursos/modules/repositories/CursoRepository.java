package tech.ian.cursos.modules.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ian.cursos.modules.entities.CursoEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

    Optional<CursoEntity> findByName(String name);

    Optional<CursoEntity> findByNameAndCategory(@Param("name") String name, @Param("category") String category);
}
