package cl.ucn.disc.as.conserjeria.model;

import lombok.Builder;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;
@Builder
@Entity
public class Contrato extends BaseModel{

    private Instant fechaPago;
    private Departamento departamento;
    private Persona persona;

    /**
     *
     * @param fechaPago
     * @param departamento
     * @param persona
     */
    public Contrato(Instant fechaPago, Departamento departamento, Persona persona) {
        this.fechaPago = fechaPago;
        this.departamento = departamento;
        this.persona = persona;
    }
}
