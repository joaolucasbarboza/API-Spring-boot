package medvoll.api.domain.remedio;

public record DadosAtualizarRemedios(
        String descricao,
        Long estoque,
        String resumoMedicamento,
        Dosagem dosagem,
        TipoRemedio tipoRemedio) {

}
