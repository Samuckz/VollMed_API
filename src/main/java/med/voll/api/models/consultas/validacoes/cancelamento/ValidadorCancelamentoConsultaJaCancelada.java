package med.voll.api.models.consultas.validacoes.cancelamento;

import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorCancelamentoConsultaJaCancelada implements ValidadorCancelamentoConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(CancelarConsultaDTO cancelarConsultaDTO) {
        var consulta = consultaRepository.getById(cancelarConsultaDTO.getId());
        if(consulta.getAtivo() == false){
            throw new ValidacaoException("Esta consulta já foi cancelada!");
        }
    }
}
