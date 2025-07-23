package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.Facturas.EstadoFactura;
import com.kinalitosclothes.dominio.Facturas.FormaEntrega;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-23T11:16:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Facturas.class)
public class Facturas_ { 

    public static volatile SingularAttribute<Facturas, Integer> codigoPedido;
    public static volatile SingularAttribute<Facturas, Integer> codigoEmpleado;
    public static volatile SingularAttribute<Facturas, Double> totalFactura;
    public static volatile SingularAttribute<Facturas, EstadoFactura> estadoFactura;
    public static volatile SingularAttribute<Facturas, Double> descuentoAplicado;
    public static volatile SingularAttribute<Facturas, Integer> codigoFactura;
    public static volatile SingularAttribute<Facturas, Date> fechaEmision;
    public static volatile SingularAttribute<Facturas, FormaEntrega> formaEntrega;

}