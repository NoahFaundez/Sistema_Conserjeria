package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.model.*;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.ebean.Database;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class SistemaImpl implements Sistema{

    private Database database;

    public SistemaImpl(Database database)
    {
        this.database = database;
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
        List<Persona> personas =
                database.find(Persona.class)
                        .select("nombre")
                        .findList();

        return personas;
    }

    @Override
    public Optional getPersona(String rut) {
        Persona persona = database.find(Persona.class)
                .where().eq("rut", rut)
                .findOne();

        return Optional.ofNullable(persona);
    }

    /**
     * Initialize
     */
    @Override
    public void populate() {

        // build the persona
        {
            Persona persona = Persona.builder()
                    .rut("22012334-7")
                    .nombre("Homero")
                    .apellidos("Simpson")
                    .email("hsimpson@ucn.cl")
                    .telefono("+5622874512")
                    .build();
            this.database.save(persona);
        }

        // the faker
        Locale locale = new Locale("es-CL");
        FakeValuesService fvs = new FakeValuesService(locale, new RandomService());
        Faker faker = new Faker(locale);

        // faker
        for (int i = 0; i < 1000; i++) {
            Persona persona = Persona.builder()
                    .rut(fvs.bothify("#######-#"))
                    .nombre(faker.name().firstName())
                    .apellidos(faker.name().lastName())
                    .email(fvs.bothify("????##@gmail.com"))
                    .telefono(fvs.bothify("+569########"))
                    .build();
            this.database.save(persona);
        }

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
