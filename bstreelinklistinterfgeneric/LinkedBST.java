package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // Definición de un nodo
    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    
    public Node getRoot() {
        return root;
    }

    public LinkedBST() {
        this.root = null;
    }
    
 // Método público para iniciar el recorrido Pre-Orden
    public void preOrderTraversal() {
        preOrder(root);
    }

    // Método privado para realizar el recorrido Pre-Orden
    private void preOrder(Node node) {
        if (node == null) {
            return; // Si el nodo es null, no hacemos nada
        }
        
        // Procesamos el nodo actual (por ejemplo, imprimiéndolo)
        System.out.print(node.data + " ");
        
        // Recorrido del subárbol izquierdo
        preOrder(node.left);
        
        // Recorrido del subárbol derecho
        preOrder(node.right);
    }
    
 // Método público para iniciar el recorrido In-Orden
    public void inOrderTraversal() {
        inOrder(root);
    }

    // Método privado para realizar el recorrido In-Orden
    private void inOrder(Node node) {
        if (node == null) {
            return; // Si el nodo es null, no hacemos nada
        } 
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
    
    public void postOrderTraversal() {
    	postOrder(root);
    }
    
    private void postOrder(Node node) {
        if (node == null) {
            return; // Si el nodo es null, no hacemos nada
        } 
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }
    
    // Método para insertar un elemento
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, E data) throws ItemDuplicated {
        // Si el árbol está vacío, colocar el nodo como raíz
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Si el dato ya existe, lanzar la excepción ItemDuplicated
        if (data.compareTo(root.data) == 0) {
            throw new ItemDuplicated("El elemento " + data + " ya existe en el árbol.");
        }

        // Si el dato es menor que el nodo actual, ir al subárbol izquierdo
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } 
        // Si el dato es mayor, ir al subárbol derecho
        else {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Método para eliminar un elemento
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node root, E data) {
        // Si el árbol está vacío
        if (root == null) {
            return root;
        }

        // Si el dato es menor que la raíz, buscar en el subárbol izquierdo
        if (data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } 
        // Si el dato es mayor que la raíz, buscar en el subárbol derecho
        else if (data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } 
        // Si el dato es igual a la raíz, eliminar este nodo
        else {
            // Nodo con solo un hijo o sin hijo
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Nodo con dos hijos, obtener el sucesor más pequeño (el más a la izquierda del subárbol derecho)
            root.data = minValue(root.right);

            // Eliminar el sucesor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private E minValue(Node root) {
        E minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Método para buscar un elemento
    @Override
    public E search(E data) throws ItemNotFound {
        Node result = searchRec(root, data);
        if (result == null) {
            throw new ItemNotFound("El elemento " + data + " no se encuentra en el árbol.");
        }
        return result.data;
    }

    private Node searchRec(Node root, E data) {
        // Si el árbol está vacío o hemos encontrado el dato
        if (root == null || data.compareTo(root.data) == 0) {
            return root;
        }

        // Si el dato es menor que la raíz, buscar en el subárbol izquierdo
        if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        }

        // Si el dato es mayor que la raíz, buscar en el subárbol derecho
        return searchRec(root.right, data);
    }
    
    public E findMin() throws ItemNotFound {
        return findMinNode(root);
    }

    public E findMax() throws ItemNotFound {
        return findMaxNode(root);
    }
    
    private E findMinNode(Node node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("El subárbol está vacío, no se puede encontrar el mínimo.");
        }

        Node current = node;
        while (current.left != null) {
            current = current.left;
        }

        // Validamos usando search()
        return search(current.data); // Puede lanzar ItemNotFound si no lo encuentra
    }
    
    private E findMaxNode(Node node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("El subárbol está vacío, no se puede encontrar el máximo.");
        }

        Node current = node;
        while (current.right != null) {
            current = current.right;
        }

        // Validamos usando search()
        return search(current.data); // Puede lanzar ItemNotFound si no lo encuentra
    }


    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
    // Método para representar el árbol como una cadena
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb, "", true);
        return sb.toString();
    }

    private void toStringRec(Node node, StringBuilder sb, String indent, boolean isLast) {
        if (node != null) {
            sb.append(indent);
            if (isLast) {
                sb.append("└── ");
                indent += "    ";
            } else {
                sb.append("├── ");
                indent += "|   ";
            }
            sb.append(node.data).append("\n");
            toStringRec(node.left, sb, indent, false);
            toStringRec(node.right, sb, indent, true);
        }
    }
}

