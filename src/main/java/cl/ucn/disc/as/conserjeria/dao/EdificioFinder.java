package cl.ucn.disc.as.conserjeria.dao;

import cl.ucn.disc.as.conserjeria.model.Edificio;
import cl.ucn.disc.as.conserjeria.model.query.QEdificio;
import io.ebean.Finder;

import java.util.Optional;

public class EdificioFinder extends Finder<Long, Edificio> {
    /**
     * The constructor.
     */
    public EdificioFinder() {
        super(Edificio.class);
    }

    /**
     * Find a building by address.
     * @param direccion to use.
     * @return the building.
     */
    public Optional<Edificio> byAddress(String direccion) {
        return new QEdificio().direccion.eq(direccion).findOneOrEmpty();
    }
}