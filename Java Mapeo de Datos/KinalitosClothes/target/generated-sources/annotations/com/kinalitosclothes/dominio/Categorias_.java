package com.kinalitosclothes.dominio;

import com.kinalitosclothes.dominio.Categorias.Genero;
import com.kinalitosclothes.dominio.Categorias.RangoEdad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-07-23T13:12:00", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, RangoEdad> rangoEdad;
    public static volatile SingularAttribute<Categorias, Genero> genero;
    public static volatile SingularAttribute<Categorias, Integer> codigoCategoria;
    public static volatile SingularAttribute<Categorias, String> descripcionCategoria;
    public static volatile SingularAttribute<Categorias, String> nombreCategoria;

}