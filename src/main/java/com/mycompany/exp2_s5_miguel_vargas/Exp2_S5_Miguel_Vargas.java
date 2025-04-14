/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.exp2_s5_miguel_vargas;

 import java.util.Scanner;

 /**
 * Sistema para venta de entradas Teatro Moro V2
 * @author mvarg
 */
 
 public class Exp2_S5_Miguel_Vargas {

    //variables estáticas
    static int entradaAcumulada = 0;
    static double totalAcumulado = 0;
    static String ultimaEntradaComprada = "No hay entradas compradas aún."; //almacenara los datos de la ultima entrada que se compre
    
    //estas variables servirán para poder eliminar la ultima entrada comprada
    static int ultimaZonaSeleccionada = -1; // Zona de la última compra (1 = VIP, 2 = Normal, 3 = Palco)
    static int ultimaFila = -1;             //fila del asiento
    static int ultimaColumna = -1;          //columna del asiento
    static double precioBaseUltimaEntrada = 0;
 
    public static void main(String[] args) {

        //distribución de asientos por zona
        char[][] zonaVip = {{'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}};
        char[][] zonaNormal = {{'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}};
        char[][] zonaPalco = {{'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O', 'O'}};
        
        //precios base por zona
        double precioVip = 20000;
        double precioNormal = 7000;
        double precioPalco = 12000;

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        double precioFinal;
        
        //saludo de bienvenida
        System.out.println("---------------------");
        System.out.println("     TEATRO MORO");
        System.out.println("---------------------");
        System.out.println("Bienvenido a nuestro sistema de compra");
        System.out.println("Actualmente tenemos el siguiente show disponible:");
        System.out.println("-- De vuelta a clases con el GOTH --");

        //inicio del menu
        while (continuar) {
            System.out.println("\n--- Menu principal ---");
            System.out.println("1. Comprar entradas");
            System.out.println("2. Ver asientos disponibles");
            System.out.println("3. Promociones disponibles");
            System.out.println("4. Búsqueda de entradas");
            System.out.println("5. Eliminar ultima compra");
            System.out.println("6. Pagar");
            System.out.println("7. Salir");

            if (scanner.hasNextInt()) {         //con el if nos aseguramos que el usuario ingrese un numero valido
                int opcion = scanner.nextInt();

                switch (opcion) {

                    case 1:         //compra de entradas
                        boolean compraExitosa = false;
        
                        while (!compraExitosa) {
                            System.out.println("\nEntradas disponibles:");
                            System.out.println("   Entrada - Precio");
                            System.out.println("--------------------");
                            System.out.println("1. VIP       $20.000");
                            System.out.println("2. Normal    $ 7.000");
                            System.out.println("3. Palco     $12.000");
                            System.out.println("--------------------");
                            System.out.print("Seleccione una zona: ");
        
                            int zonaSeleccionada = scanner.nextInt();
                            char[][] zonaActual = null;
                            double precioBase = 0;
        
                            if (zonaSeleccionada == 1) {
                                zonaActual = zonaVip;
                                precioBase = precioVip;
                                System.out.println("\nEntrada VIP seleccionada.");
                            } else if (zonaSeleccionada == 2) {
                                zonaActual = zonaNormal;
                                precioBase = precioNormal;
                                System.out.println("\nEntrada normal seleccionada.");
                            } else if (zonaSeleccionada == 3) {
                                zonaActual = zonaPalco;
                                precioBase = precioPalco;
                                System.out.println("\nEntrada palco seleccionada.");
                            } else {
                                System.out.println("Selección invalida. Intente nuevamente.");
                                continue;
                            }
        
                            // Mostrar el plano de la zona seleccionada con filas y columnas etiquetadas
                            System.out.println("Asientos disponibles:");
                            System.out.println("--------------");
                            System.out.println("  ESCENARIO");
                            System.out.println("--------------");
                            
                            if (zonaSeleccionada == 2){                    //
                                System.out.println(" ZONA VIP");         // bloque de código completamente
                                System.out.println("--------------");    // innecesario, pero que hace que
                            } else if (zonaSeleccionada == 3) {            // el mapa de asientos se vea mejor
                                System.out.println(" ZONA VIP");         //
                                System.out.println("--------------");    //
                                System.out.println(" ZONA NORMAL");      //
                                System.out.println("--------------");    //
                            }
                            System.out.print("  ");     // Espacio inicial para alinear columnas
                            for (int col = 0; col < zonaActual[0].length; col++) {
                                System.out.print((col + 1) + " ");          // Etiquetas de columnas (1, 2, 3...)
                            }
                            System.out.println();
        
                            for (int fila = 0; fila < zonaActual.length; fila++) {
                                System.out.print((char) ('A' + fila) + " ");            // Etiquetas de filas (A, B, C...)
                                for (int col = 0; col < zonaActual[fila].length; col++) {
                                    System.out.print(zonaActual[fila][col] + " ");
                                }
                                System.out.println();
                            }

                            if (zonaSeleccionada == 1) {                    //
                                System.out.println("--------------");     // lo mismo que el bloque de arriba
                                System.out.println(" ZONA NORMAL");       // 
                                System.out.println("--------------");     // 
                                System.out.println(" ZONA PALCO");        //
                                System.out.println("--------------");     //

                            } else if (zonaSeleccionada == 2) {             //
                                System.out.println("--------------");     //
                                System.out.println(" ZONA PALCO");        //
                                System.out.println("--------------");     //
                            }
        
                            //solicitar la fila y columna del asiento
                            System.out.print("Seleccione fila (A, B, C...): ");
                            char filaChar = scanner.next().toUpperCase().charAt(0);
                            int fila = filaChar - 'A';          //convertir letra a índice
        
                            System.out.print("Seleccione columna (1, 2, 3...): ");
                            int columna = scanner.nextInt() - 1;            //convertir entrada a índice
        
                            if (fila >= 0 && fila < zonaActual.length && columna >= 0 && columna < zonaActual[0].length) {
                                if (zonaActual[fila][columna] == 'O') {
                                    zonaActual[fila][columna] = 'X';            //marcar asiento como ocupado
                                    System.out.println("Asiento reservado exitosamente.");
        
                                    //solicitar la edad para calcular descuento
                                    System.out.print("Ingrese su edad: ");
                                    if (scanner.hasNextInt()) {
                                        int edad = scanner.nextInt();
                                        double descuento = 0;
        
                                        if (edad >= 60) {
                                            descuento = 0.15;
                                            System.out.println("Se aplicará un descuento del 15%.");
                                        } else if (edad >= 18 && edad <= 25) {
                                            descuento = 0.10;
                                            System.out.println("Se aplicará un descuento del 10%.");
                                        } else {
                                            System.out.println("No hay descuentos disponibles actualmente.");
                                        }
        
                                        //calcular precio final
                                        double impuesto = 0.19;
                                        precioFinal = precioBase * (1 - descuento);
                                        double precioIva = precioFinal * impuesto;
                                        double precioNeto = precioFinal - precioIva;

                                        precioBaseUltimaEntrada = precioFinal;  //almacenar el precio de la última entrada
                                        totalAcumulado += precioFinal;          //actualizamos el total acumulado de entradas
                                        entradaAcumulada++;                     //actualizamos la cantidad de entradas compradas

                                        //estas variables permiten guardar y reescribir la info del ultimo asiento en cada compra
                                        ultimaZonaSeleccionada = zonaSeleccionada;      
                                        ultimaFila = fila;                              
                                        ultimaColumna = columna;                         
        
                                        //mostrar resumen de la compra
                                        System.out.println("\n--- Detalle de la Compra ---");
                                        System.out.println("Entrada: " + (zonaSeleccionada == 1 ? "VIP" : zonaSeleccionada == 2 ? "Normal" : "Palco"));
                                        System.out.println("Asiento: "+ filaChar + (columna + 1));
                                        System.out.println("Fecha  : 25/05/2025\nHora   : 18:30 hrs.");
                                        System.out.println("---- Detalle del pago ----");
                                        System.out.println("Precio base: $" + precioBase);
                                        System.out.println("Descuento  : " + (descuento * 100) + "%");
                                        System.out.println("--------------------------");
                                        System.out.println("Valor Neto : $" + precioNeto);
                                        System.out.println("IVA        : $" + precioIva);
                                        System.out.println("Valor total: $" + precioFinal);
                                        System.out.println("--------------------------\n");

                                        ultimaEntradaComprada = "Zona: " + (zonaSeleccionada == 1 ? "VIP" : zonaSeleccionada == 2 ? "Normal" : "Palco") +
                                                                ", Asiento: " + filaChar + (columna + 1) +
                                                                ", Precio: $" + precioFinal;
        
                                        compraExitosa = true;
                                    } else {
                                        System.out.println("Edad inválida. Intente nuevamente.");
                                        scanner.next();         //limpiar entrada no válida
                                    }
                                } else {
                                    System.out.println("El asiento ya está ocupado. Intente nuevamente.");
                                }
                            } else {
                                System.out.println("Selección inválida. Intente nuevamente.");
                            }
                        }
                        break;

                    case 2:         //mostrar plano de asientos disponibles
                        System.out.println("\n--- Plano de asientos disponibles ---");
                        System.out.println("--------------");
                        System.out.println("  ESCENARIO");
                        System.out.println("--------------");
                    
                        System.out.println("\nZona VIP:");
                        mostrarPlano(zonaVip);
                    
                        System.out.println("\nZona Normal:");
                        mostrarPlano(zonaNormal);
                    
                        System.out.println("\nZona Palco:");
                        mostrarPlano(zonaPalco);
                        break;
                    
                    case 3:         //promociones disponibles
                        promocionesDisponibles();
                        break;

                    case 4:         //búsqueda de entradas
                        System.out.println("\n--- Última entrada comprada ---");
                        System.out.println(ultimaEntradaComprada);
                        break;

                    case 5:         //eliminar última entrada
                        if (ultimaZonaSeleccionada != -1 && ultimaFila != -1 && ultimaColumna != -1) {
                            char[][] zonaActual = null;
                            
                            //identificar la zona de la última compra
                            if (ultimaZonaSeleccionada == 1) {
                                zonaActual = zonaVip;
                            } else if (ultimaZonaSeleccionada == 2) {
                                zonaActual = zonaNormal;
                            } else if (ultimaZonaSeleccionada == 3) {
                                zonaActual = zonaPalco;
                            }
                    
                            //restablecer el asiento ocupado
                            if (zonaActual != null) {
                                zonaActual[ultimaFila][ultimaColumna] = 'O';        //desocupar el asiento
                                System.out.println("El asiento " + (char) ('A' + ultimaFila) + (ultimaColumna + 1) + " ha sido desocupado.");
                    
                                //ajustar acumulados
                                if (precioBaseUltimaEntrada > 0) { 
                                    totalAcumulado -= precioBaseUltimaEntrada;      //resta el valor correcto
                                    entradaAcumulada--;                             //reducir cantidad de entradas compradas
                                } else {
                                    System.out.println("Error: No se encontró el precio de la última entrada.");
                                }
                    
                                //restablecer variables
                                ultimaZonaSeleccionada = -1;
                                ultimaFila = -1;
                                ultimaColumna = -1;
                                precioBaseUltimaEntrada = 0;
                                ultimaEntradaComprada = "No hay entradas compradas aún.";
                            }
                        } else {
                            System.out.println("No hay entradas compradas para eliminar.");
                        }
                        break;

                    case 6:             //pago
                        if (entradaAcumulada > 0) {         //solo permitir pagos si hay entradas acumuladas
                            procesarPago(scanner);
                            continuar = false;
                        } else {
                            System.out.println("No hay compras realizadas. Por favor, compre sus entradas antes de proceder al pago.");
                        }
                        break;

                        case 7:         //salir del programa
                        if (entradaAcumulada == 0) {
                            System.out.println("\nGracias por usar nuestro sistema. ¡Hasta luego!");
                            continuar = false;          //finaliza el programa saliendo del bucle principal
                        } else {
                            System.out.println("\nTiene compras pendientes de pago.");
                            System.out.println("Se le redirigirá automáticamente al menú de pago.\n");
                            
                            //invoca directamente el método de pago
                            procesarPago(scanner);          
                            continuar = false;              //finaliza el programa saliendo del bucle principal
                        }
                        break;
                
                    default:            //mensaje para opciones no validas 
                        System.out.println("Opcion invalida, por favor intente nuevamente");
                        break;
                }
            } else {
                System.out.println("Opcion invalida, por favor intente nuevamente");
                scanner.next();
            }

        }

        scanner.close();

    }

    //método para la opción 3 - promociones
    static void promocionesDisponibles() {
        System.out.println("\n--- Promociones Disponibles ---");
        System.out.println("- 10% de descuento para estudiantes.");
        System.out.println("- 15% de descuento para personas de la tercera edad.");
    }

    //método para finalizar el proceso de pago
    public static void procesarPago(Scanner scanner) {
        System.out.println("\nSu compra es de " + entradaAcumulada + " entradas, por un total de $" + totalAcumulado);
        System.out.println("Seleccione el medio de pago:");
        System.out.println("1. Débito\n2. Crédito\n3. Transferencia\n4. Cancelar compra");
        
        int confirmaCompra;
        do {
            confirmaCompra = scanner.nextInt();
        } while (confirmaCompra < 1 || confirmaCompra > 4);
    
        switch (confirmaCompra) {
            case 1:
                System.out.println("Pago con tarjeta de débito. Procesando...");
                confirmarCompra(scanner, "Débito");
                break;

            case 2:
                System.out.println("Pago con tarjeta de crédito.");
                System.out.println("Indique la cantidad de cuotas (1 a 12 cuotas): ");
                int cuotas = scanner.nextInt();

                if (cuotas < 1 || cuotas > 12) {
                    System.out.println("Numero de cuotas seleccionado invalido");
                } else {
                    System.out.println("Tu compra sera cargada en tu tarjeta en " + cuotas + " cuotas");
                }
                confirmarCompra(scanner, "Crédito");
                break;

            case 3:
                System.out.println("Pago mediante transferencia.");
                System.out.println("Recuerda que recibirás las instrucciones para la transferencia en tu correo");
                confirmarCompra(scanner, "Transferencia");
                break;
            
            case 4:
                System.out.println("Compra cancelada. Vuelve pronto.");
                System.exit(0); //finaliza el programa de forma automática 
                break;
        }
    }

    public static double obtenerUltimoPrecio() {
        
        return precioBaseUltimaEntrada; 
    }

    //bloque de código opción 6 - pago
    public static void confirmarCompra(Scanner scanner, String metodoPago) {
        System.out.println("\nHas elegido el método de pago: " + metodoPago);
        System.out.println("Estamos procesando la compra...");
        
        try {
            Thread.sleep(3000);         //simulación de espera durante el procesamiento (3 segundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("¡Compra confirmada!");
        System.out.print("Para finalizar, ingrese su correo: ");
        scanner.nextLine();         //limpiar entrada
        String correo = scanner.nextLine();
        
        System.out.println("\nSu boleta y entradas serán enviadas al correo " + correo);
        System.out.println("Gracias por usar nuestro sistema. ¡Hasta luego!");

    }
    
    //método para la opción 2 - ver asientos disponibles
    public static void mostrarPlano(char[][] zona) {
        System.out.print("  ");                       //espacio inicial para alinear columnas
        for (int col = 0; col < zona[0].length; col++) {
            System.out.print((col + 1) + " ");          //etiquetas de columnas (1, 2, 3...)
        }
        System.out.println();
    
        for (int fila = 0; fila < zona.length; fila++) {
            System.out.print((char) ('A' + fila) + " ");    //etiquetas de filas (A, B, C...)
            for (int col = 0; col < zona[fila].length; col++) {
                System.out.print(zona[fila][col] + " ");
            }
            System.out.println();
        }
    }
    
}    