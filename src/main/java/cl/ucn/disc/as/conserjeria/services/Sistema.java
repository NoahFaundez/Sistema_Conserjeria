package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.model.*;

import java.time.Instant;
import java.util.List;

public interface Sistema {

    public Edificio add(Edificio edificio);
    public Persona add(Persona persona);
    public Departamento addDepartamento(Departamento departamento, Edificio edificio);
    public Departamento addDepartamento(Departamento departamento, Long idEdificio);
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);
    public Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago);
    public List<Contrato> getContratos();
    public List<Persona> getPersonas();
    public void populate();
    public List<Pago> getPagos(String rut);
}
