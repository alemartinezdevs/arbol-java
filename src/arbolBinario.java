import java.util.InputMismatchException;
import java.util.Scanner;

public class arbolBinario {

    // Clase que representa un árbol binario de búsqueda
        Nodo raiz; // La raíz del árbol


        // Constructor que inicializa el árbol vacío
        public arbolBinario() {
            raiz = null;
        }

        // Función para insertar un nuevo nodo en el árbol
        public void insertar(int valor) {
            raiz = insertarRec(raiz, valor);
        }

        // Función auxiliar para realizar la inserción de forma recursiva
        private Nodo insertarRec(Nodo raiz, int valor) {

            // Si la raíz actual es nula, creamos un nuevo nodo con el valor dado y lo devolvemos como la nueva raíz
            if (raiz == null) {
                raiz = new Nodo(valor);
                return raiz;
            }

            // Si el usuario ingresa un valor que es menor que el valor de la raíz, lo insertamos en el subárbol izquierdo
            if (valor < raiz.valor) {
                raiz.izquierdo = insertarRec(raiz.izquierdo, valor);
            }

            // Si el valor a insertar es mayor que el valor de la raíz, lo insertamos en el subárbol derecho
            else if (valor > raiz.valor) {
                raiz.derecho = insertarRec(raiz.derecho, valor);
            }
            return raiz;
        }

        // Función para realizar un recorrido en orden del árbol (inorden)
        public void inorden() {
            inordenRec(raiz);
        }

        // Función auxiliar para realizar el recorrido inorden de forma recursiva
        private void inordenRec(Nodo raiz) {

            // El recorrido inorden se realiza recursivamente en el subárbol izquierdo, luego se imprime el valor de la raíz y finalmente se recorre el subárbol derecho
            if (raiz != null) {
                inordenRec(raiz.izquierdo);
                System.out.print(raiz.valor + " ");
                inordenRec(raiz.derecho);
            }
        }
        public boolean existe(int valor){
            return existeRec(raiz, valor);
        }

        private boolean existeRec(Nodo raiz, int valor){
            if (raiz == null){
                return false;
            }if(valor == raiz.valor){
                return true;
            }
            return valor<raiz.valor
                    ?existeRec(raiz.izquierdo, valor)
                    :existeRec(raiz.derecho, valor);
        }

        // Función para realizar un recorrido preorden del árbol
        public void preorden() {
            preordenRec(raiz);
        }

        // Función auxiliar para realizar el recorrido preorden de forma recursiva
        private void preordenRec(Nodo raiz) {
            if (raiz != null) {
                System.out.print(raiz.valor + " "); // Imprimir el valor del nodo antes de recorrer los subárboles
                preordenRec(raiz.izquierdo); // Recorrer el subárbol izquierdo
                preordenRec(raiz.derecho); // Recorrer el subárbol derecho
            }
        }

        // Función para realizar un recorrido postorden del árbol
        public void postorden() {
            postordenRec(raiz);
        }

        // Función auxiliar para realizar el recorrido postorden de forma recursiva
        private void postordenRec(Nodo raiz) {
            if (raiz != null) {
                postordenRec(raiz.izquierdo); // Recorrer el subárbol izquierdo
                postordenRec(raiz.derecho); // Recorrer el subárbol derecho
                System.out.print(raiz.valor + " "); // Imprimir el valor del nodo después de recorrer los subárboles
            }
        }

        public boolean buscar(int valor){
            return buscarRec (raiz, valor);
        }

        private boolean buscarRec (Nodo raiz, int valor){
            if (raiz  == null){
                return false;
            }

            if (raiz.valor == valor){
                return true;
            }

            if (valor < raiz.valor) {
                return buscarRec(raiz.izquierdo, valor);
            }

            else{
                return buscarRec(raiz.derecho, valor);
            }
        }
        public void eliminar(int valor){
            raiz = eliminarRec(raiz, valor);
        }
        private Nodo eliminarRec (Nodo raiz, int valor){
            if(raiz == null){
                return raiz;
            }

            if(valor< raiz.valor){
                raiz.izquierdo = eliminarRec(raiz.izquierdo, valor);
            }
            else if(valor > raiz.valor){
                raiz.derecho = eliminarRec(raiz.derecho, valor);
            }
            else{
                if(raiz.izquierdo == null){
                    return raiz.derecho;
                }else if(raiz.derecho == null){
                    return raiz.izquierdo;
                }
                raiz.valor = minValor (raiz.derecho);
                raiz.derecho = eliminarRec(raiz.derecho,raiz.valor);
            }
            return raiz;
        }
        private int minValor(Nodo raiz){
            int minva = raiz.valor;
            while(raiz.izquierdo != null){
                minva = raiz.izquierdo.valor;
                raiz = raiz.izquierdo;
            }
            return minva;
        }

        // Método principal para probar el funcionamiento del árbol
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            arbolBinario arbol = new arbolBinario();
            int option;

            do{

                try{
                    System.out.print("");
                    System.out.print("------------------------------------------------------------------------------------------");
                    System.out.print("");

                    System.out.println("\n\t\t<> OPCIONES <>");
                    System.out.println("\n1. Agregar Arbol completo");
                    System.out.println("2. Mostrar Arbol completo");
                    System.out.println("3. Insertar un Nodo al arbol");
                    System.out.println("4. Eliminar un nodo en el arbol");
                    System.out.println("5. Recorrer el arbol");
                    System.out.println("6. Buscar un Nodo en Especifico");
                    System.out.println("7. Salir del Programa");
                    System.out.print("\nElija una opcion: ");
                    option = scanner.nextInt();

                } catch (Exception e) {
                    System.out.println("\nError! Ingresa un numero.");
                    scanner.nextLine();
                    option = 0;
                    continue;
                }

                switch (option) {
                    case 1:
                        System.out.println("\nCuantos datos desea agregar al arbol? ");
                        System.out.print("Respuesta:");
                        int recorrido = scanner.nextInt();
                        int []datos = new int[recorrido];

                        for (int i = 0; i< recorrido; i++){
                            System.out.print("\nIngrese el numero en la posicion " + (i+1) + ": ");
                            datos[i] = scanner.nextInt();
                        }
                        for (int dato : datos){
                            arbol.insertar(dato);
                        }
                        System.out.println("");
                        break;

                    case 2:
                        //Mostrando el arbol
                        if(arbol.raiz != null){
                        arbol.inorden();
                        System.out.println("");
                        }else{
                            System.out.println("\nError!");
                            System.out.println("El arbol esta vacio.");
                            System.out.println("");
                        }
                        break;

                    case 3:

                        int valor;
                        do{
                            System.out.print("\nDigita el valor que desea agregar al arbol: ");
                            valor = scanner.nextInt();
                            if(arbol.existe(valor)){
                                System.out.println("\nError!");
                                System.out.println("El valor ya existe en el arbol");
                            }
                        }while(arbol.existe(valor));
                        arbol.insertar(valor);
                        System.out.println("\nValor agregado exitosamente!!!");
                        break;

                    case 4:

                        if (arbol.raiz == null){
                            System.out.println("\nError!");
                            System.out.println("No puede eliminar ningun valor porque el arbol esta vacio.");
                        }else{
                        int valorelim;
                        while(true){
                            System.out.print("\nIngresa el valor que deseas eliminar: ");
                            valorelim = scanner.nextInt();
                            if(arbol.buscar(valorelim)){
                                arbol.eliminar(valorelim);
                                System.out.println("\nEl valor fue eliminado exitosamente!!!");
                                break;
                            }
                            else{
                                System.out.println("\nError!");
                                System.out.println("El valor no fue encontrado.");
                            }
                        }
                        }

                        break;

                    case 5:
                        if (arbol.raiz == null){
                            System.out.println("\nError!");
                            System.out.println("No se puede iniciar un recorrido porque el arbol esta vacio");
                        }else {
                            int optionre;
                            do {
                                try {
                                    System.out.println("\n\t<> Elija una opcion para recorrer el arbol <>");
                                    System.out.println("\n1. Inorden");
                                    System.out.println("2. Preorden");
                                    System.out.println("3. PostOrden");
                                    System.out.println("4. Volver al menu principal");
                                    System.out.print("Ingresa una opcion: ");
                                    optionre = scanner.nextInt();

                                } catch (Exception e) {
                                    System.out.println("\nIngrese una opcion Valida..");
                                    scanner.nextLine();
                                    optionre = 0;
                                    continue;
                                }

                                switch (optionre) {

                                    case 1:
                                        System.out.println("\nRecorrido InOrden");
                                        if (arbol.raiz != null) {
                                            arbol.inorden();
                                            System.out.println("");
                                        } else {
                                            System.out.println("\nError!");
                                            System.out.println("El arbol esta vacio.");
                                        }

                                        break;

                                    case 2:
                                        System.out.println("\nRecorrido Preorden");
                                        if (arbol.raiz != null) {
                                            arbol.preorden();
                                            System.out.println("");
                                        } else {
                                            System.out.println("\nError!");
                                            System.out.println("El arbol esta vacio.");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("\nRecorrido PostOrden");
                                        if (arbol.raiz != null) {
                                            arbol.postorden();
                                            System.out.println("");
                                        } else {
                                            System.out.println("\nError!");
                                            System.out.println("El arbol esta vacio.");
                                        }
                                        break;

                                    case 4:
                                        System.out.println("\nRegresando al menu!");
                                        break;

                                    default:
                                        System.out.println("\nOpcion invalida.....");
                                        break;
                                }
                            } while (optionre != 4);
                        }
                        break;

                    case 6:
                        System.out.print("\nIngresa el valor que busca: ");
                        int valorbus = scanner.nextInt();
                        if(arbol.buscar(valorbus)){
                            System.out.println("\nValor encontrado");
                        }
                        else{
                            System.out.println("\nValor no encontrado");
                        }
                        break;
                    case 7:
                        System.out.println("\nPrograma finalizado.");
                        break;

                    default:
                        System.out.println(" Intenta de nuevo...");
                        break;
                }
            }while(option != 7);
            scanner.close();

        }
}
