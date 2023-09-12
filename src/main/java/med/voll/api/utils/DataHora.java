package med.voll.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DataHora {
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minutos;

    public DataHora(DataHoraDTO dataHoraDTO) {
        this.dia = dataHoraDTO.getDia();
        this.mes = dataHoraDTO.getMes();
        this.ano = dataHoraDTO.getAno();
        this.hora = dataHoraDTO.getHora();
        this.minutos = dataHoraDTO.getMinutos();
    }

    public String toString(DataHora dataHora){
        return dataHora.getDia() + "/" + dataHora.getMes() + "/" + dataHora.getAno() + " - " + dataHora.getHora() + ":" + dataHora.getMinutos();
    }

    public String dataToString(DataHora dataHora){
        return dataHora.getDia() + "/" + dataHora.getMes() + "/" + dataHora.getAno();
    }

    public String timeToString(DataHora dataHora){
        return dataHora.getHora() + ":" + dataHora.getMinutos();
    }
}
