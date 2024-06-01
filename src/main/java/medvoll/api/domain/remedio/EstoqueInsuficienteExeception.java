package medvoll.api.domain.remedio;

public class EstoqueInsuficienteExeception extends RuntimeException {
    public EstoqueInsuficienteExeception(String message) {
        super(message);
    }
}
