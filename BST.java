/**
 * Arbol Binario de Busqueda - Ejemplo 1: Numeros Enteros
 * Universidad Da Vinci de Guatemala
 * Facultad: Ingenieria en Sistemas y Ciencias de la Computacion
 * Curso: Estructuras de Datos
 *
 * Descripcion: BST que organiza numeros enteros positivos.
 * Demuestra insert, search, delete y los tres recorridos.
 */
public class BST {

    static class Nodo {
        int valor;
        Nodo izq, der;
        Nodo(int v) { valor = v; izq = null; der = null; }
    }

    private Nodo raiz;

    public BST() { raiz = null; }

    // INSERT - O(log n) promedio, O(n) peor caso
    public void insert(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);
        if (valor < nodo.valor)
            nodo.izq = insertarRec(nodo.izq, valor);
        else if (valor > nodo.valor)
            nodo.der = insertarRec(nodo.der, valor);
        return nodo;
    }

    // SEARCH - O(log n) promedio, O(n) peor caso
    public boolean search(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Nodo nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.valor) return true;
        if (valor < nodo.valor) return buscarRec(nodo.izq, valor);
        return buscarRec(nodo.der, valor);
    }

    // DELETE - O(log n) promedio, O(n) peor caso
    // Caso 1: nodo sin hijos -> eliminacion directa
    // Caso 2: nodo con un hijo -> reemplazar por hijo
    // Caso 3: nodo con dos hijos -> reemplazar por sucesor in-order
    public void delete(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.valor) {
            nodo.izq = eliminarRec(nodo.izq, valor);
        } else if (valor > nodo.valor) {
            nodo.der = eliminarRec(nodo.der, valor);
        } else {
            // Caso 1: sin hijos
            if (nodo.izq == null && nodo.der == null) return null;
            // Caso 2: un solo hijo
            if (nodo.izq == null) return nodo.der;
            if (nodo.der == null) return nodo.izq;
            // Caso 3: dos hijos - buscar minimo del subarbol derecho
            Nodo sucesor = minimoNodo(nodo.der);
            nodo.valor = sucesor.valor;
            nodo.der = eliminarRec(nodo.der, sucesor.valor);
        }
        return nodo;
    }

    private Nodo minimoNodo(Nodo nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
        return nodo;
    }

    // RECORRIDO IN-ORDER: izquierda -> raiz -> derecha (resultado ordenado)
    public void recorridoInOrder() {
        System.out.print("In-Order:   ");
        inOrder(raiz);
        System.out.println();
    }

    private void inOrder(Nodo nodo) {
        if (nodo == null) return;
        inOrder(nodo.izq);
        System.out.print(nodo.valor + " ");
        inOrder(nodo.der);
    }

    // RECORRIDO PRE-ORDER: raiz -> izquierda -> derecha
    public void recorridoPreOrder() {
        System.out.print("Pre-Order:  ");
        preOrder(raiz);
        System.out.println();
    }

    private void preOrder(Nodo nodo) {
        if (nodo == null) return;
        System.out.print(nodo.valor + " ");
        preOrder(nodo.izq);
        preOrder(nodo.der);
    }

    // RECORRIDO POST-ORDER: izquierda -> derecha -> raiz
    public void recorridoPostOrder() {
        System.out.print("Post-Order: ");
        postOrder(raiz);
        System.out.println();
    }

    private void postOrder(Nodo nodo) {
        if (nodo == null) return;
        postOrder(nodo.izq);
        postOrder(nodo.der);
        System.out.print(nodo.valor + " ");
    }

    // -------------------------------------------------------
    // MAIN - Demostracion con numeros enteros
    // -------------------------------------------------------
    public static void main(String[] args) {
        BST arbol = new BST();

        System.out.println("===========================================");
        System.out.println("  BST - Ejemplo 1: Numeros Enteros");
        System.out.println("  Universidad Da Vinci de Guatemala");
        System.out.println("===========================================");

        // Insertar 7 valores
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("\n[INSERT] Insertando valores: 50, 30, 70, 20, 40, 60, 80");
        for (int v : valores) {
            arbol.insert(v);
            System.out.println("  Insertado: " + v);
        }

        // Recorridos
        System.out.println("\n[RECORRIDOS]");
        arbol.recorridoInOrder();
        arbol.recorridoPreOrder();
        arbol.recorridoPostOrder();

        // Busquedas
        System.out.println("\n[SEARCH]");
        int[] buscar = {40, 99, 20, 55};
        for (int b : buscar) {
            System.out.println("  Buscar " + b + ": " + (arbol.search(b) ? "ENCONTRADO" : "NO encontrado"));
        }

        // Eliminaciones (los 3 casos)
        System.out.println("\n[DELETE]");
        System.out.println("  Eliminar 20 (sin hijos / hoja)");
        arbol.delete(20);
        arbol.recorridoInOrder();

        System.out.println("  Eliminar 30 (un hijo)");
        arbol.delete(30);
        arbol.recorridoInOrder();

        System.out.println("  Eliminar 70 (dos hijos)");
        arbol.delete(70);
        arbol.recorridoInOrder();

        System.out.println("\n===========================================");
        System.out.println("  Complejidad Big-O:");
        System.out.println("  Busqueda:  O(log n) prom / O(n) peor");
        System.out.println("  Insercion: O(log n) prom / O(n) peor");
        System.out.println("  Eliminac.: O(log n) prom / O(n) peor");
        System.out.println("===========================================");
    }
}
