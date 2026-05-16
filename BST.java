import java.util.Scanner;

/**
 * Arbol Binario de Busqueda (BST)
 * Universidad Da Vinci de Guatemala
 * Facultad: Ingenieria en Sistemas y Ciencias de la Computacion
 * Curso: Estructuras de Datos
 *
 * Descripcion:
 * BST que organiza numeros enteros positivos.
 * Permite:
 * - Insertar
 * - Buscar
 * - Eliminar
 * - Mostrar recorridos
 * - Ingreso manual de datos
 */

public class BST {

    // =========================================
    // CLASE NODO
    // =========================================
    static class Nodo {
        int valor;
        Nodo izq, der;

        Nodo(int v) {
            valor = v;
            izq = null;
            der = null;
        }
    }

    // Raiz del arbol
    private Nodo raiz;

    // Constructor
    public BST() {
        raiz = null;
    }

    // =========================================
    // INSERT
    // =========================================
    public void insert(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {

        // Si el nodo esta vacio
        if (nodo == null) {
            return new Nodo(valor);
        }

        // Insertar izquierda
        if (valor < nodo.valor) {
            nodo.izq = insertarRec(nodo.izq, valor);
        }

        // Insertar derecha
        else if (valor > nodo.valor) {
            nodo.der = insertarRec(nodo.der, valor);
        }

        return nodo;
    }

    // =========================================
    // SEARCH
    // =========================================
    public boolean search(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Nodo nodo, int valor) {

        if (nodo == null) {
            return false;
        }

        if (valor == nodo.valor) {
            return true;
        }

        if (valor < nodo.valor) {
            return buscarRec(nodo.izq, valor);
        }

        return buscarRec(nodo.der, valor);
    }

    // =========================================
    // DELETE
    // =========================================
    public void delete(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {

        if (nodo == null) {
            return null;
        }

        // Buscar izquierda
        if (valor < nodo.valor) {
            nodo.izq = eliminarRec(nodo.izq, valor);
        }

        // Buscar derecha
        else if (valor > nodo.valor) {
            nodo.der = eliminarRec(nodo.der, valor);
        }

        // Nodo encontrado
        else {

            // Caso 1: sin hijos
            if (nodo.izq == null && nodo.der == null) {
                return null;
            }

            // Caso 2: un hijo derecho
            if (nodo.izq == null) {
                return nodo.der;
            }

            // Caso 2: un hijo izquierdo
            if (nodo.der == null) {
                return nodo.izq;
            }

            // Caso 3: dos hijos
            Nodo sucesor = minimoNodo(nodo.der);

            nodo.valor = sucesor.valor;

            nodo.der = eliminarRec(nodo.der, sucesor.valor);
        }

        return nodo;
    }

    // Buscar minimo del subarbol
    private Nodo minimoNodo(Nodo nodo) {

        while (nodo.izq != null) {
            nodo = nodo.izq;
        }

        return nodo;
    }

    // =========================================
    // RECORRIDO IN-ORDER
    // =========================================
    public void recorridoInOrder() {
        System.out.print("In-Order:   ");
        inOrder(raiz);
        System.out.println();
    }

    private void inOrder(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        inOrder(nodo.izq);

        System.out.print(nodo.valor + " ");

        inOrder(nodo.der);
    }

    // =========================================
    // RECORRIDO PRE-ORDER
    // =========================================
    public void recorridoPreOrder() {
        System.out.print("Pre-Order:  ");
        preOrder(raiz);
        System.out.println();
    }

    private void preOrder(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        System.out.print(nodo.valor + " ");

        preOrder(nodo.izq);

        preOrder(nodo.der);
    }

    // =========================================
    // RECORRIDO POST-ORDER
    // =========================================
    public void recorridoPostOrder() {
        System.out.print("Post-Order: ");
        postOrder(raiz);
        System.out.println();
    }

    private void postOrder(Nodo nodo) {

        if (nodo == null) {
            return;
        }

        postOrder(nodo.izq);

        postOrder(nodo.der);

        System.out.print(nodo.valor + " ");
    }

    // =========================================
    // MAIN
    // =========================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BST arbol = new BST();

        System.out.println("===========================================");
        System.out.println("  BST - Arbol Binario de Busqueda");
        System.out.println("  Universidad Da Vinci de Guatemala");
        System.out.println("===========================================");

        // -------------------------------------
        // INSERTAR DATOS
        // -------------------------------------
        System.out.print("\n¿Cuantos numeros desea insertar?: ");
        int cantidad = sc.nextInt();

        for (int i = 0; i < cantidad; i++) {

            System.out.print("Ingrese numero " + (i + 1) + ": ");

            int valor = sc.nextInt();

            arbol.insert(valor);

            System.out.println("  Insertado correctamente");
        }

        // -------------------------------------
        // RECORRIDOS
        // -------------------------------------
        System.out.println("\n[RECORRIDOS DEL ARBOL]");

        arbol.recorridoInOrder();

        arbol.recorridoPreOrder();

        arbol.recorridoPostOrder();

        // -------------------------------------
        // BUSQUEDA
        // -------------------------------------
        System.out.print("\nIngrese numero a buscar: ");

        int buscar = sc.nextInt();

        if (arbol.search(buscar)) {
            System.out.println("Numero ENCONTRADO");
        } else {
            System.out.println("Numero NO encontrado");
        }

        // -------------------------------------
        // ELIMINACION
        // -------------------------------------
        System.out.print("\nIngrese numero a eliminar: ");

        int eliminar = sc.nextInt();

        arbol.delete(eliminar);

        System.out.println("\nArbol despues de eliminar:");

        arbol.recorridoInOrder();

        // -------------------------------------
        // COMPLEJIDAD
        // -------------------------------------
        System.out.println("\n===========================================");
        System.out.println("Complejidad Big-O");
        System.out.println("Busqueda:   O(log n) promedio");
        System.out.println("Insercion:  O(log n) promedio");
        System.out.println("Eliminacion: O(log n) promedio");
        System.out.println("===========================================");

        sc.close();
    }
}
