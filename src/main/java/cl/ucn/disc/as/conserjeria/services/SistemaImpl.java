package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.model.*;

import io.ebean.Database;

import java.time.Instant;
import java.util.List;

public class SistemaImpl implements Sistema{

    public SistemaImpl(Database database)
    {

    }

    /**
     * @param edificio
     * @return Edificio
     */
    @Override
    public Edificio add(Edificio edificio) {
        return null;
    }

    /**
     * @param persona
     * @return Persona
     */
    @Override
    public Persona add(Persona persona) {
        return null;
    }

    /**
     * @param departamento
     * @param edificio
     * @return Departamento
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Edificio edificio) {
        return null;
    }

    /**
     * @param departamento
     * @param idEdificio
     * @return Departamento
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Long idEdificio) {
        return null;
    }

    /**
     * @param duenio
     * @param departamento
     * @param fechaPago
     * @return Contrato
     */
    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        return null;
    }

    /**
     * @param idDuenio
     * @param idDepartamento
     * @param fechaPago
     * @return Contrato
     */
    @Override
    public Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago) {
        return null;
    }

    /**
     * @return List<Contrato>
     */
    @Override
    public List<Contrato> getContratos() {
        return null;
    }

    /**
     * @return List<Persona>
     */
    @Override
    public List<Persona> getPersonas() {
        return null;
    }

    /**
     * @param rut
     * @return List<Pago>
     */
    @Override
    public List<Pago> getPagos(String rut) {
        return null;
    }
}
