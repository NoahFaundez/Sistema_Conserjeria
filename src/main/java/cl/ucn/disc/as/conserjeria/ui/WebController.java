package cl.ucn.disc.as.conserjeria.ui;

import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpc;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcRequest;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcResponse;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcServiceGrpc;
import cl.ucn.disc.as.conserjeria.model.Persona;
import cl.ucn.disc.as.conserjeria.services.Sistema;
import cl.ucn.disc.as.conserjeria.services.SistemaImpl;
import io.ebean.DB;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.Optional;

public final class WebController implements RoutesConfigurator{

    /**
     * The sistema
     */
    private final Sistema sistema;

    public WebController() {
        this.sistema = new SistemaImpl(DB.getDefault());
        // FIXME: only populate in case of new database
        // this.sistema.populate();
    }

    /**
     * Configure the routes.
     *
     * @param app to use.
     */
    @Override
    public void configure(final Javalin app) {

        app.get("/", ctx -> {
            ctx.result("Welcome to conserjeria API REST");
        });

        // the personas api
        app.get("/personas", ctx -> {
            ctx.json(this.sistema.getPersonas());
        });

        app.get("/personas/rut/{rut}", ctx -> {
            String rut = ctx.pathParam("rut");
            Optional<Persona> oPersona = this.sistema.getPersona(rut);
            ctx.json(oPersona.orElseThrow(() -> new NotFoundResponse("Can´t find persona with rut: " + rut)));
        });

        app.get("/api/grpc/personas/rut/{rut}", ctx -> {

            String rut = ctx.pathParam("rut");

            // channel
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress("127.0.0.1", 50123)
                    .usePlaintext()
                    .build();

            // stub
            PersonaGrpcServiceGrpc.PersonaGrpcServiceBlockingStub stub =
                    PersonaGrpcServiceGrpc.newBlockingStub(channel);

            // call the grpc
            PersonaGrpcResponse response = stub.retrieve(PersonaGrpcRequest
                    .newBuilder()
                    .setRut("130144918")
                    .build());

            // get the response
            PersonaGrpc personaGrpc = response.getPersona();

            // return the persona
            Optional<Persona> oPersona = Optional.of(Persona.builder()
                    .rut(personaGrpc.getRut())
                    .nombre(personaGrpc.getNombre())
                    .apellidos(personaGrpc.getApellidos())
                    .email(personaGrpc.getEmail())
                    .build());

            ctx.json(oPersona.orElseThrow(() -> new NotFoundResponse("Can´t find the persona with rut: " + rut)));

        });

    }
}