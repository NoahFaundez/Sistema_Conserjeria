import cl.ucn.disc.as.conserjeria.model.Departamento;
import cl.ucn.disc.as.conserjeria.model.Edificio;
import cl.ucn.disc.as.conserjeria.model.Contrato;
import cl.ucn.disc.as.conserjeria.model.Persona;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main.
 * @author Noah Faundez
 */
@Slf4j
public final class Main {
    /**
     * The Main constr.
     * @param args to use.
     */
    public static void main(String[] args) {
//        OwnerFinder pf = new OwnerFinder();
//        Optional<Persona> oPersona = pf.byRut(20416699);
//        oPersona.ifPresent(p -> log.debug("Persona from db: {}", p));

        Persona persona = Persona.builder()
                .rut("19952130-6")
                .nombre("Noah")
                .apellidos("Faundez Velasco")
                .email("noah.faundez@alumnos.ucn.cl")
                .telefono("+56950954592")
                .build();

        Contrato contrato = Contrato.builder()
                .fechaPago(Instant.now())
                .persona(persona)
                .build();

        List<Departamento> departamentos = new ArrayList<>();
        Departamento departamento = Departamento.builder()
                .numero(15)
                .piso(15)
                .build();
        departamentos.add(departamento);
        departamento = Departamento.builder()
                .numero(7)
                .piso(7)
                .build();
        departamentos.add(departamento);
        departamento = Departamento.builder()
                .numero(4)
                .piso(4)
                .build();
        departamentos.add(departamento);

        Edificio edificio = Edificio.builder()
                .nombre("Hotel Antofagasta")
                .direccion("Avd Edmundo Perez Zujovic")
                .departamentos(departamentos)
                .build();

        Database db = DB.getDefault();
        db.save(persona);
        db.save(contrato);
        db.save(edificio);
    }
}