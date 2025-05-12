package bstreelinklistinterfgeneric;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        
        // Inserción
        try {
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);
        } catch (ItemDuplicated e) {
            e.printStackTrace();
        }
        
        System.out.println("Recorrido In-Orden del árbol: ");
        bst.inOrderTraversal();
        System.out.println("\nRecorrido Pre-Orden del árbol: ");
        bst.preOrderTraversal();
        System.out.println("\nRecorrido Post-Orden del árbol: ");
        bst.postOrderTraversal();
        
        try {
            System.out.println("\nMínimo: " + bst.findMin());
            System.out.println("Máximo: " + bst.findMax());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

