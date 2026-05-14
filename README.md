# BST - Ejemplo 1: Numeros Enteros
**Universidad Da Vinci de Guatemala**
Facultad de Ingenieria en Sistemas | Curso: Estructuras de Datos

---

## Descripcion
Implementacion de un **Arbol Binario de Busqueda (BST)** que organiza numeros enteros. Este ejemplo demuestra la insercion, busqueda, eliminacion (los 3 casos) y los tres recorridos clasicos.

## Estructura del Arbol (ejemplo)
```
        50
       /  \
      30   70
     / \  /  \
    20  40 60  80
```

## Complejidad Big-O
| Operacion  | Caso Promedio | Peor Caso |
|------------|--------------|-----------|
| Busqueda   | O(log n)     | O(n)      |
| Insercion  | O(log n)     | O(n)      |
| Eliminacion| O(log n)     | O(n)      |

> **Peor caso O(n):** ocurre cuando el arbol degenera en una lista (insertar valores ya ordenados).

## Metodos implementados
- `insert(int valor)` — Inserta respetando la regla BST (menores a la izquierda, mayores a la derecha)
- `search(int valor)` — Retorna `true` si el valor existe, `false` si no
- `delete(int valor)` — Elimina considerando los 3 casos:
  - Nodo hoja (sin hijos)
  - Nodo con un solo hijo
  - Nodo con dos hijos (reemplazo por sucesor in-order)
- `recorridoInOrder()` — Imprime los nodos en orden ascendente
- `recorridoPreOrder()` — Raiz primero, luego subarboles
- `recorridoPostOrder()` — Subarboles primero, raiz al final

## Como ejecutar
```bash
# Compilar
javac src/BST.java -d out

# Ejecutar
java -cp out BST
```

## Salida esperada
```
===========================================
  BST - Ejemplo 1: Numeros Enteros
  Universidad Da Vinci de Guatemala
===========================================

[INSERT] Insertando valores: 50, 30, 70, 20, 40, 60, 80
  Insertado: 50
  ...

[RECORRIDOS]
In-Order:   20 30 40 50 60 70 80
Pre-Order:  50 30 20 40 70 60 80
Post-Order: 20 40 30 60 80 70 50

[SEARCH]
  Buscar 40: ENCONTRADO
  Buscar 99: NO encontrado
  ...

[DELETE]
  Eliminar 20 (sin hijos)
  In-Order:   30 40 50 60 70 80
  ...
```

## Autor
Gustavo Rafael Juarez Mendoza  
Universidad Da Vinci de Guatemala — 2025

---
> Video de defensa: [Enlace YouTube aqui]
