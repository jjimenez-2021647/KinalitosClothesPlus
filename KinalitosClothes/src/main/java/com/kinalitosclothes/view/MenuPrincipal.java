package com.josuejimenez.view;

import com.josuejimenez.controller.UsuariosController;
import com.josuejimenez.controller.FacturasController;
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

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        UsuariosController u = new UsuariosController();
                        u.UsuariosController();
                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

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
