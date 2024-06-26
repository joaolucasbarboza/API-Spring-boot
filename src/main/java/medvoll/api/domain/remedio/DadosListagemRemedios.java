package medvoll.api.domain.remedio;


public record DadosListagemRemedios(Long id, String descricao, String resumoMedicamento, String numeroRegistro, Long estoque, Dosagem dosagem, TipoRemedio tipoRemedio) {

    public DadosListagemRemedios(RemedioEntity remedio) {
        this(remedio.getId(), remedio.getDescricao(), remedio.getResumoMedicamento(), remedio.getNumeroRegistro(), remedio.getEstoque(), remedio.getDosagem(), remedio.getTipoRemedio());
    }
}
