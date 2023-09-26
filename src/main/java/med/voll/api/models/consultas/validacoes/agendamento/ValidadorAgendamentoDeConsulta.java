package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.DTOS.ConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
    void validar(ConsultaDTO consultaDTO);
}
