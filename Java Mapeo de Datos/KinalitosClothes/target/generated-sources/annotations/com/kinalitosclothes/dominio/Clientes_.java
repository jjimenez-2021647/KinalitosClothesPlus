package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.Usuarios;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-23T11:16:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> correoCliente;
    public static volatile SingularAttribute<Clientes, String> nombreCliente;
    public static volatile SingularAttribute<Clientes, Integer> codigoCliente;
    public static volatile SingularAttribute<Clientes, String> apellidoCliente;
    public static volatile SingularAttribute<Clientes, String> telefonoCliente;
    public static volatile SingularAttribute<Clientes, String> direccionCliente;
    public static volatile SingularAttribute<Clientes, Usuarios> usuario;

}