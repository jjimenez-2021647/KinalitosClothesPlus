package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.Usuarios.TipoUsuarios;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-23T11:44:58", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, Integer> codigoUsuario;
    public static volatile SingularAttribute<Usuarios, Date> fechaRegistro;
    public static volatile SingularAttribute<Usuarios, String> contraseñaUsuario;
    public static volatile SingularAttribute<Usuarios, TipoUsuarios> tipoUsuario;
    public static volatile SingularAttribute<Usuarios, String> nombreUsuario;

}