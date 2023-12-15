package cl.ucn.disc.as.conserjeria;

import cl.ucn.disc.as.conserjeria.services.PersonaGrpcServiceImpl;
import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
    public static void main(String[] args) throws InterruptedException, IOException {
        log.debug("Starting Main with library path: {}", System.getProperty("java.library.path"));

        // Start the API REST server
        log.debug("Starting ApiRest server ..");
        Javalin app = ApiRestServer.start(7070, new WebController());

        // stop the API REST server.
        // app.stop();

        // Start rge gRPC server
        log.debug("Starting the gRPC server ..");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();

        server.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

        // wait for the stop
        server.awaitTermination();

        log.debug("Done. :)");
    }
}