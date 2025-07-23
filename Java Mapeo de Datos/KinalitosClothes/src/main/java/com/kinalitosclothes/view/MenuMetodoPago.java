package com.kinalitosclothes.view;

import java.util.List;
import java.util.Scanner;
import com.kinalitosclothes.dominio.MetodoPagos;
import com.kinalitosclothes.dominio.MetodoPagos.TipoMetodoPago;

public class MenuMetodoPago {
    private Scanner scanner;

    public MenuMetodoPago() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenuYObtenerOpcion() {
        System.out.println("-----------------------------------");
        System.out.println("Menú de Métodos de Pago");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Agregar");
        System.out.println("2. Listar");
        System.out.println("3. Eliminar");
        System.out.println("4. Buscar");
        System.out.println("5. Editar");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------");
        return scanner.nextInt();
    }

    public MetodoPagos obtenerDatosMetodoPago() {
        scanner.nextLine(); // Consumir salto de línea
        System.out.println("Ingrese el tipo de método de pago (Tarjeta/Efectivo):");
        TipoMetodoPago tipo = TipoMetodoPago.valueOf(scanner.nextLine());
        System.out.println("Ingrese la entidad financiera:");
        String entidad = scanner.nextLine();
        System.out.println("Ingrese la moneda:");
        String moneda = scanner.nextLine();

        return new MetodoPagos(tipo, entidad, moneda);
    }

    public void mostrarMetodoPagos(List<MetodoPagos> lista) {
        System.out.println("Lista de Métodos de Pago:");
        for (MetodoPagos mp : lista) {
            System.out.println("-----------------------------------");
            System.out.println(mp);
        }
    }

    public int obtenerIdMetodoPago() {
        System.out.println("Ingrese el código del método de pago:");
        return scanner.nextInt();
    }

    public String confirmarEliminacion() {
        System.out.println("¿Está seguro de eliminar? (Escriba 'SI' para confirmar):");
        return scanner.next();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public MetodoPagos obtenerDatosActualizados(MetodoPagos metodoActual) {
        scanner.nextLine();
        System.out.println("Ingrese el nuevo tipo de método de pago (Tarjeta/Efectivo):");
        TipoMetodoPago nuevoTipo = TipoMetodoPago.valueOf(scanner.nextLine());
        System.out.println("Ingrese la nueva entidad financiera:");
        String nuevaEntidad = scanner.nextLine();
        System.out.println("Ingrese la nueva moneda:");
        String nuevaMoneda = scanner.nextLine();

        metodoActual.setTipoMetodoPago(nuevoTipo);
        metodoActual.setEntidadFinanciaera(nuevaEntidad);
        metodoActual.setMoneda(nuevaMoneda);

        return metodoActual;
    }
}
