package com.kinalitosclothes.view;

import com.kinalitosclothes.controller.*;
import java.util.Scanner;

public class MenuPrincipal {

    public void mostrarMenu() {
        Scanner leer = new Scanner(System.in);
        int op = -1;

        do {
            System.out.println("-------------------------------------------------");
            System.out.println("Bienvenido al Menu Principal de KinalitosClothes");
            System.out.println("Elije una de las siguientes opciones para acceder \na sus menus");
            System.out.println("1. Proveedores");
            System.out.println("2. Categorias");
            System.out.println("3. Metodo Pagos");
            System.out.println("4. Usuarios");
            System.out.println("5. Clientes");
            System.out.println("6. Empleados");
            System.out.println("7. Productos");
            System.out.println("8. Pedidos");
            System.out.println("9. Detalle Pedidos");
            System.out.println("10. Facturas");
            System.out.println("0. Salir");
            System.out.println("-------------------------------------------------");

            try {
                op = leer.nextInt();
                leer.nextLine();

                switch (op) {
                    case 1:
                        ProveedoresController p = new ProveedoresController();
                        p.menuProveedores();
                        break;
                    case 2:
                        CategoriasController cc = new CategoriasController();
                        cc.CategoriasController();
                        break;
                    case 3:
                        MetodoPagosController mpc = new MetodoPagosController();
                        mpc.MetodoPagosController();
                        break;
                    case 4:
                        UsuariosController u = new UsuariosController();
                        u.UsuariosController();
                        break;
                    case 5:
                        ClientesController C = new ClientesController();
                        C.ClientesController();
                        break;
                    case 6:
                        EmpleadosController ec = new EmpleadosController();
                        ec.menuEmpleados();
                        break;
                    case 7:
                    ProductosController pc = new ProductosController();
                    pc.ProductosController();
                        break;
                    case 8:
                        PedidosController pe = new PedidosController();
                        pe.PedidosController();
                        break;
                    case 9:
                        DetallePedidosController dpc = new DetallePedidosController();
                        dpc.DetallePedidosController();
                        break;
                    case 10:
                        FacturasController f = new FacturasController();
                        f.FacturasController();
                        break;
                    case 0:
                        System.out.println("---------------------");
                        System.out.println("Saliendo...");
                        System.out.println("---------------------");
                        break;
                    default:
                        System.out.println("Por favor, selecciona una opción válida.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                leer.nextLine();
            }
        } while (op != 0);
    }
}
