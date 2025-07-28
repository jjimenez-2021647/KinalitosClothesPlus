package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.Pedidos.EstadoPedido;
import java.sql.Time;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-28T01:29:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, Integer> codigoPedido;
    public static volatile SingularAttribute<Pedidos, Double> total;
    public static volatile SingularAttribute<Pedidos, Time> hora;
    public static volatile SingularAttribute<Pedidos, Integer> codigoCliente;
    public static volatile SingularAttribute<Pedidos, EstadoPedido> estadoPedido;
    public static volatile SingularAttribute<Pedidos, Integer> codigoMetodoPago;
    public static volatile SingularAttribute<Pedidos, Date> fechaPedido;

}