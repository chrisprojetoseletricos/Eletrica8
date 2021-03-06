package chc.eletrica8.entidades;

public interface Entidade<T extends Entidade<T>> {

    T clonarSemID();

    T copiar();

    Integer getId();
}
