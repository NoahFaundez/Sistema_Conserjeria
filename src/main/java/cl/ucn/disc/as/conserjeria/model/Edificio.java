package cl.ucn.disc.as.conserjeria.model;

import lombok.Builder;

import javax.persistence.Entity;
import java.util.List;


@Builder
@Entity
public class Edificio extends BaseModel{

    private String nombre;
    private String direccion;
    private List<Departamento> departamentos;

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void add(Departamento nuevoDepartamento) {
        departamentos.add(nuevoDepartamento);
    }

    public List<Departamento> getDepartamento() {
        return departamentos;
    }
}
