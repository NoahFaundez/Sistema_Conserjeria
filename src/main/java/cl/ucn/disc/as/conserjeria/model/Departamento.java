package cl.ucn.disc.as.conserjeria.model;

import io.ebean.annotation.NotNull;
import lombok.Builder;

import javax.persistence.Entity;

@Builder
@Entity
public class Departamento extends BaseModel{
    @NotNull
    public Integer numero;
    @NotNull
    public Integer piso;

    public Departamento(Integer numero, Integer piso) {
        this.numero = numero;
        this.piso = piso;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getPiso() {
        return piso;
    }
}
