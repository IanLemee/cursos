package tech.ian.cursos.modules.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ian.cursos.exceptions.CourseNotFoundException;
import tech.ian.cursos.modules.entities.Active;
import tech.ian.cursos.modules.entities.CursoEntity;
import tech.ian.cursos.modules.repositories.CursoRepository;
import tech.ian.cursos.modules.useCases.CursoUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoUseCase service;

    @Autowired
    private CursoRepository repository;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity) {

        try {
            var result = this.service.execute(cursoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCourseId(@RequestParam(value = "name", required = true) String name,
                                             @RequestParam(value = "category", required = true) String category) {
        var getCorseByNameAndCategory = service.findByNameAndCategory(name, category);
        return ResponseEntity.status(HttpStatus.FOUND).body(getCorseByNameAndCategory);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CursoEntity>> getAllCourse() {
        List<CursoEntity> cursoList = service.findAllCourse();

        return ResponseEntity.status(HttpStatus.OK).body(cursoList);

    }

    @PutMapping("{id}")
    public ResponseEntity<CursoEntity> updateCourse(@PathVariable(value = "id") UUID id, @Valid @RequestBody CursoEntity cursoEntity) {
        CursoEntity cursoUpdate = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso nao achado pelo id " + id));

        cursoUpdate.setName(cursoEntity.getName());
        cursoUpdate.setCategory(cursoEntity.getCategory());

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(cursoUpdate));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "id") UUID id) {
        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado");
    }

    @PatchMapping("{id}/{active}")
    public ResponseEntity<CursoEntity> activeCurse(@PathVariable(value = "id") UUID id, @PathVariable(value = "active")Active active, CursoEntity cursoEntity) {
        CursoEntity cursoUpdate = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso nao achado pelo id " + id));

        cursoUpdate.getActive();

        repository.save(cursoUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(cursoEntity);
    }
}
