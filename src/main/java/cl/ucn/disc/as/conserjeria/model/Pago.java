package cl.ucn.disc.as.conserjeria.model;

import java.time.Instant;
import java.util.List;

public class Pago {

    private Instant fechaPago;
    private Integer monto;

    public Pago(Instant fechaPago, Integer monto)
    {
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Instant getFechaPago() {
        return fechaPago;
    }

    public Integer getMonto() {
        return monto;
    }
}
