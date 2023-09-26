package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAgendamentoDeConsultaAntecedenciaMinima implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(ConsultaDTO consultaDTO){
        var data = consultaDTO.getData();
        var agora = LocalDateTime.now();
        var antecedenciaMinima = Duration.between(agora,data).toMinutes();

        if (antecedenciaMinima < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }

}
