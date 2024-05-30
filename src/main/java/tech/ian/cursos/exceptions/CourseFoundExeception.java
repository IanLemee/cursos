package tech.ian.cursos.exceptions;

public class CourseFoundExeception extends RuntimeException{
    public CourseFoundExeception() {
        super("Curso ja existe");
    }
}
