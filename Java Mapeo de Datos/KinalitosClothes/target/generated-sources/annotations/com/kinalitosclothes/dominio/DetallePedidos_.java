package com.kinalitosclothes.dominio;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-26T13:17:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(DetallePedidos.class)
public class DetallePedidos_ { 

    public static volatile SingularAttribute<DetallePedidos, String> descripcion;
    public static volatile SingularAttribute<DetallePedidos, Integer> codigoDetalleP;
    public static volatile SingularAttribute<DetallePedidos, Integer> codigoPedido;
    public static volatile SingularAttribute<DetallePedidos, Double> subtotal;
    public static volatile SingularAttribute<DetallePedidos, Integer> cantidad;
    public static volatile SingularAttribute<DetallePedidos, Integer> codigoProducto;

}