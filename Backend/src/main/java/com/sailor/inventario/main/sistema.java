import java.util.Scanner;

public class sistema {
    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola.");
        }
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      SISTEMA DE GESTIÓN         ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Gestión de Empleados          ║");
        System.out.println("║ 2. Gestión de Productos          ║");
        System.out.println("║ 3. Gestión de Proveedores        ║");
        System.out.println("║ 4. Gestión de Inventario         ║");
        System.out.println("║ 5. Salir                         ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuEmpleados() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      GESTIÓN DE EMPLEADOS        ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Registrar empleado            ║");
        System.out.println("║ 2. Asignar horario               ║");
        System.out.println("║ 3. Calcular salario              ║");
        System.out.println("║ 4. Volver al menú principal      ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuProductos() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      GESTIÓN DE PRODUCTOS        ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Registrar producto            ║");
        System.out.println("║ 2. Actualizar stock             ║");
        System.out.println("║ 3. Mostrar información          ║");
        System.out.println("║ 4. Volver al menú principal      ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuProveedores() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     GESTIÓN DE PROVEEDORES       ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Registrar proveedor           ║");
        System.out.println("║ 2. Listar productos              ║");
        System.out.println("║ 3. Volver al menú principal       ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarMenuInventario() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║      GESTIÓN DE INVENTARIO       ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Agregar productos             ║");
        System.out.println("║ 2. Retirar productos             ║");
        System.out.println("║ 3. Consultar stock               ║");
        System.out.println("║ 4. Volver al menú principal      ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuario empleado = new Usuario("", 0, "", 0, 0);
        Producto producto = new Producto("", "", 0.0, 0);
        inventario inventario = new inventario("", 0);
        Proveedor proveedor = new Proveedor("", "");
        int opcionPrincipal, opcionSecundaria;

        while (true) {
            limpiarConsola();
            mostrarMenuPrincipal();
            opcionPrincipal = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcionPrincipal) {
                case 1: // Gestión de Empleados
                    do {
                        limpiarConsola();
                        mostrarMenuEmpleados();
                        opcionSecundaria = sc.nextInt();
                        sc.nextLine();

                        switch (opcionSecundaria) {
                            case 1:
                                empleado.registrar_empleaado();
                                break;
                            case 2:
                                empleado.asignar_horario();
                                break;
                            case 3:
                                empleado.calcular_salario();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        if (opcionSecundaria != 4) {
                            System.out.println("\nPresione Enter para continuar...");
                            sc.nextLine();
                        }
                    } while (opcionSecundaria != 4);
                    break;

                case 2: // Gestión de Productos
                    do {
                        limpiarConsola();
                        mostrarMenuProductos();
                        opcionSecundaria = sc.nextInt();
                        sc.nextLine();

                        switch (opcionSecundaria) {
                            case 1:
                                producto.registrar_producto();
                                break;
                            case 2:
                                producto.actualizar_stock();
                                break;
                            case 3:
                                producto.Mostrar_Informacion();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        if (opcionSecundaria != 4) {
                            System.out.println("\nPresione Enter para continuar...");
                            sc.nextLine();
                        }
                    } while (opcionSecundaria != 4);
                    break;

                case 3: // Gestión de Proveedores
                    do {
                        limpiarConsola();
                        mostrarMenuProveedores();
                        opcionSecundaria = sc.nextInt();
                        sc.nextLine();

                        switch (opcionSecundaria) {
                            case 1:
                                proveedor.registrar_proveedor();
                                break;
                            case 2:
                                proveedor.listar_productos();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        if (opcionSecundaria != 3) {
                            System.out.println("\nPresione Enter para continuar...");
                            sc.nextLine();
                        }
                    } while (opcionSecundaria != 3);
                    break;

                case 4: // Gestión de Inventario
                    do {
                        limpiarConsola();
                        mostrarMenuInventario();
                        opcionSecundaria = sc.nextInt();
                        sc.nextLine();

                        switch (opcionSecundaria) {
                            case 1:
                                System.out.println("Ingrese el nombre del producto a agregar:");
                                String nombreProducto = sc.nextLine();
                                System.out.println("Ingrese la cantidad del producto:");
                                int cantidadProducto = sc.nextInt();
                                sc.nextLine();
                                inventario.agregar_producto(nombreProducto, cantidadProducto);
                                break;
                            case 2:
                                System.out.println("Ingrese el nombre del producto a retirar:");
                                String nombreProductoRetirar = sc.nextLine();
                                System.out.println("Ingrese la cantidad a retirar:");
                                int cantidadProductoRetirar = sc.nextInt();
                                sc.nextLine();
                                inventario.retirar_producto(nombreProductoRetirar, cantidadProductoRetirar);
                                break;
                            case 3:
                                inventario.consultar_stock();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        if (opcionSecundaria != 4) {
                            System.out.println("\nPresione Enter para continuar...");
                            sc.nextLine();
                        }
                    } while (opcionSecundaria != 4);
                    break;

                case 5: // Salir
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
            }
        }
    }
}