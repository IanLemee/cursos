package tech.ian.cursos.exceptions;

public class CurseFoundExeception extends RuntimeException{
    public CurseFoundExeception() {
        super("Curso ja existe");
    }
}
