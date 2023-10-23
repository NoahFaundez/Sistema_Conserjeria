package cl.ucn.disc.as.conserjeria.ui;

import io.javalin.Javalin;

/**
 * The Routes config.
 *
 * @author Noah Faúndez
 */
public interface RoutesConfigurator {

    /**
     * Configure the routes
     *
     * @param javalin to use
     */
    void configure(Javalin javalin);

}
