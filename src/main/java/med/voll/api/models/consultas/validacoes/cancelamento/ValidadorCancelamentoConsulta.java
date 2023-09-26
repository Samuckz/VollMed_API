package med.voll.api.models.consultas.validacoes.cancelamento;

import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;

public interface ValidadorCancelamentoConsulta {
    void validar(CancelarConsultaDTO cancelarConsultaDTO);
}
