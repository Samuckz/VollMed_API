package med.voll.api.services;

import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface PoliticaConsultas {
    @Transactional(readOnly = true)
    Page<ConsultaResponseDTO> listarConsultas(
            Pageable paginacao
    );

    @Transactional
    void excluir(Long id);

    @Transactional
    void cancelar(CancelarConsultaDTO consultaDTO);
}
