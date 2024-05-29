package tech.ian.cursos.modules.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ian.cursos.modules.dtos.CursoDto;
import tech.ian.cursos.modules.entities.CursoEntity;
import tech.ian.cursos.modules.useCases.CursoUseCase;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoUseCase service;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity) {

        try {
            var result = this.service.execute(cursoEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
