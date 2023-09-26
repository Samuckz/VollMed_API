package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.models.medicos.MedicoRepository;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoDeConsultaMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(ConsultaDTO consultaDTO){

        if(consultaDTO.getMedico() == null){
            return;
        }


        var medicoId = consultaDTO.getMedico();
        var medico = medicoRepository.getById(medicoId);

        if(medico.getAtivo() == false){
            throw new ValidacaoException("Médico não ativo no sistema");
        }


    }
}
