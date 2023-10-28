package cl.ucn.disc.as.conserjeria.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

/**
 * The Server class.
 *
 * @author Noah Faúndez
 */
@Slf4j
public final class ApiRestServer {

    /**
     * Nothing here
     */
    private ApiRestServer() {
        // nothing
    }

    private static Gson createAndConfigureGson() {
        // Instant Serializer
        TypeAdapter<Instant> instantTypeAdapter = new TypeAdapter<>() {

            /**
             * Writes one JSON value (an array, object, string, number, boolean or null)
             * for {@code value}
             *
             * @param out
             * @param instant the Java object to write. May be null.
             */
            public void write(JsonWriter out, Instant instant) throws IOException {
                if (instant == null) {
                    out.nullValue();
                } else {
                    out.value(instant.toEpochMilli());
                }
            }

            /**
             * Reads one JSON value(an array, object, string, number, boolean or null)
             * and converts it to a Java object. Returns the converted object
             * @param in
             * @return the converted Java object. May be null.
             * @throws IOException
             */
            @Override
            public Instant read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Instant.ofEpochMilli(in.nextLong());
            }
        };

        // the gson serializer
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, instantTypeAdapter)
                .create();

    }


    private static Javalin createAndConfigureJavalin() {
        // gson config
        JsonMapper jsonMapper = new JsonMapper() {

            // the gson serializer
            private static final Gson GSON = createAndConfigureGson();

            /**
             * json to object
             */
            @NotNull
            @Override
            public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
                return GSON.fromJson(json, targetType);
            }

            /**
             * object to json
             * @param obj
             * @param type
             * @return
             */
            @NotNull
            @Override
            public String toJsonString(@NotNull Object obj, @NotNull Type type) {
                return GSON.toJson(obj, type);
            }
        };

        // configure the server
        return Javalin.create(config -> {
            // json mapper configuration
            config.jsonMapper(jsonMapper);

            // gzip compression
            config.compression.gzipOnly(9);
            // config.compression.brotliAndGzip(9, 9);

            // enable logger
            config.requestLogger.http((ctx, ms) -> {
                log.debug("served: {} in {} ms.", ctx.fullUrl(), ms);
            });

            // enable debug logger
            config.plugins.enableDevLogging();

        });

    }

    /**
     * Starting the server.
     * @param port to use.
     * @param routesConfigurator
     */
    public static Javalin start(final Integer port, final RoutesConfigurator routesConfigurator) {
        if (port < 1024 || port > 65535) {
            log.error("Bad port {}.", port);
            throw new IllegalArgumentException("Bad port: " + port);
        }
        log.debug("Starting api rest server in port {} ..", port);

        // the server
        Javalin app = createAndConfigureJavalin();

        // configure the paths
        routesConfigurator.configure(app);

        // the hookup thread
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

        // hooks to detect the shutdown
        app.events(event -> {
            event.serverStarting(() -> {
                log.debug("Starting the Javalin server ..");
            });

            event.serverStarted(() -> {
                log.debug("Server started!");
            });

            event.serverStopping(() -> {
                log.debug("Stopping the server ..");
            });

            event.serverStopped(() -> {
                log.debug("Server stopped!");
            });

        });


        // start!
        return app.start(port);

    }

}
