package medvoll.api.domain.remedio;

public record DadosRemedio (Long id, String descricao, String resumoMedicamento, String numeroRegistro) {
    public DadosRemedio(RemedioEntity remedio) {
        this(remedio.getId(), remedio.getDescricao(), remedio.getResumoMedicamento(), remedio.getNumeroRegistro());
    }
}
