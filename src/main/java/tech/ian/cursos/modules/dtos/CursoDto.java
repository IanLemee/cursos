package tech.ian.cursos.modules.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CursoDto(@NotBlank String name, @NotBlank String category) {
}
