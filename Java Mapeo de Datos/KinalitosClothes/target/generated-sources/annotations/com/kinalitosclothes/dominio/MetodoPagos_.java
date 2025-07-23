package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.MetodoPagos.TipoMetodoPago;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-23T11:44:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(MetodoPagos.class)
public class MetodoPagos_ { 

    public static volatile SingularAttribute<MetodoPagos, TipoMetodoPago> tipoMetodoPago;
    public static volatile SingularAttribute<MetodoPagos, Integer> codigoMetodoPago;
    public static volatile SingularAttribute<MetodoPagos, String> moneda;
    public static volatile SingularAttribute<MetodoPagos, String> entidadFinanciaera;

}