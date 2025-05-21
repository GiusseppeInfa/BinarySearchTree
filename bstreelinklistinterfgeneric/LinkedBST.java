package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
	
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
    
    public void preOrderTraversal() {
        preOrder(root);
    }
    
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    
    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
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
    
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, E data) throws ItemDuplicated {
    	
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data.compareTo(root.data) == 0) {
            throw new ItemDuplicated("El elemento " + data + " ya existe en el árbol.");
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } 

        else {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty{  //, ItemNotFound
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        
        /**
        if (searchRec(root, data) == null) {
        	throw new ItemNotFound("El elemento "+data+" no se encuentra en el árbol.");
        }
        **/
        root = deleteRec(root, data);

    }

    private Node deleteRec(Node root, E data) {

        if (root == null) {
            return root;
        }
        
        if (data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } 
        
        else if (data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } 

        else {

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
    
    @Override
    public E search(E data) throws ItemNotFound {
        Node result = searchRec(root, data);
        if (result == null) {
            throw new ItemNotFound("El elemento " + data + " no se encuentra en el árbol.");
        }
        return result.data;
    }

    private Node searchRec(Node root, E data) {
    	
        if (root == null || data.compareTo(root.data) == 0) {
            return root;
        }
        
        if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        }else{
        	return searchRec(root.right, data);
        }
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
        
        return search(current.data); // Puede lanzar ItemNotFound si no lo encuentra
    }


    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
    @Override
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol ya está vacío.");
        }
        root = null;
    }
    
    @Override
    public int countAllNodes() {
        return countAll(root);
    }
    
    public int countAll(Node node) {
    	if(node == null)
    		return 0;
    	return 1 + countAll(node.left) + countAll(node.right);
    }
    
    @Override
    public int countNodes() {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node node) {
        if (node == null) 
        	return 0;
        if (node.left == null && node.right == null) 
        	return 0;
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }

    @Override
    public int height(E x) {
        Node node = searchRec(root, x);
        if (node == null) 
        	return -1;

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(node);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Número de nodos en este nivel
            height++; // Subimos un nivel

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return height;
    }
    
    @Override
    public int amplitude(int targetLevel) {
        if (root == null || targetLevel < 0) 
        	return 0;

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Nodos en este nivel

            if (currentLevel == targetLevel) {
                return levelSize; // La amplitud
            }

            // Avanzamos al siguiente nivel
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            currentLevel++;
        }

        return 0; // Nivel no existe
    }
    
    public int areaBST() {
        if (root == null) return 0;

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        int height = -1;
        int leafCount = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                
                if (current.left == null && current.right == null) {
                    leafCount++;
                }
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return height * leafCount;
    }

    
    public void parenthesize() {
        System.out.println(parenthesize(root));
    }

    private String parenthesize(Node node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.data);
        if (node.left != null || node.right != null) {
            sb.append("(");
            sb.append(parenthesize(node.left));
            sb.append(",");
            sb.append(parenthesize(node.right));
            sb.append(")");
        }
        return sb.toString();
    }

    public void drawBST() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb, "", true);
        return sb.toString();
    }

    private void toStringRec(Node node, StringBuilder sb, String indent, boolean isLast) {
        if (node != null) {
            sb.append(indent);
            sb.append(isLast ? "└── " : "├── ");
            sb.append(node.data).append("\n");
            toStringRec(node.left, sb, indent + (isLast ? "    " : "│   "), false);
            toStringRec(node.right, sb, indent + (isLast ? "    " : "│   "), true);
        }
    }
}

