package medvoll.api.domain.remedio;


import medvoll.api.domain.medico.Dosagem;

public record DadosListagemRemedios(Long id, String descricao, String resumoMedico, String numeroRegistro, Long estoque, Dosagem dosagem, TipoRemedio tipoRemedio) {

    public DadosListagemRemedios(RemedioEntity remedio) {
        this(remedio.getId(), remedio.getDescricao(), remedio.getResumoMedicamento(), remedio.getNumeroRegistro(), remedio.getEstoque(), remedio.getDosagem(), remedio.getTipoRemedio());
    }
}
