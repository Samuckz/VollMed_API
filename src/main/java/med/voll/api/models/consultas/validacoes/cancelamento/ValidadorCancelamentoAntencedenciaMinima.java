package med.voll.api.models.consultas.validacoes.cancelamento;

import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoAntencedenciaMinima implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(CancelarConsultaDTO cancelarConsultaDTO) {
        var dataConsulta = consultaRepository.getById(cancelarConsultaDTO.getId()).getData();
        var horarioAtual = LocalDateTime.now();
        var diferencaTempo = Duration.between(horarioAtual, dataConsulta).toHours();

        if (diferencaTempo < 24){
            throw new ValidacaoException("Só é permitido cancelar consultas com um prazo mínimo de 24 horas");
        }

    }
}
