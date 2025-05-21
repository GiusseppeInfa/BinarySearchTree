package bstreelinklistinterfgeneric;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class Prueba {
	
	public static boolean sameArea(LinkedBST<?> tree1, LinkedBST<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
	
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        
        System.out.println("¿Árbol vacío?: " + bst.isEmpty());
        
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
        
        System.out.println("¿Árbol vacío?: " + bst.isEmpty());
        
        System.out.println("Recorrido In-Orden del árbol: ");
        bst.inOrderTraversal();
        System.out.println("\nRecorrido Pre-Orden del árbol: ");
        bst.preOrderTraversal();
        System.out.println("\nRecorrido Post-Orden del árbol: ");
        bst.postOrderTraversal();
        
        bst.countNodes();
        
        try {
            System.out.println("\nMínimo: " + bst.findMin());
            System.out.println("Máximo: " + bst.findMax());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            System.out.println("\nBuscando 60: " + bst.search(60)); // Debe mostrar 60
            System.out.println("Buscando 100: " + bst.search(100)); // Lanza excepción
        } catch (ItemNotFound e) {
            System.out.println("No encontrado: " + e.getMessage());
        }
        
        try {
            bst.delete(70);
            System.out.println("\nÁrbol después de eliminar 70:");
            System.out.println(bst);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        // Área del árbol
        int area = bst.areaBST();
        System.out.println("\nÁrea del árbol (hojas × altura): " + area);
        
        // Amplitud
        int nivel = 2;
        int amplitud = bst.amplitude(nivel);
        System.out.println("Amplitud del árbol en el nivel " + nivel + ": " + amplitud);

        // Representación con sangría y paréntesis
        System.out.println("\nVisualización con sangría y paréntesis:");
        bst.parenthesize();

        // Comparación con otro árbol (sameArea)
        LinkedBST<Integer> otroBST = new LinkedBST<>();
        try {
            otroBST.insert(100);
            otroBST.insert(90);
            otroBST.insert(110);
            otroBST.insert(85);
            otroBST.insert(95);
        } catch (ItemDuplicated e) {
            e.printStackTrace();
        }

        System.out.println("\nÁrea del segundo árbol: " + otroBST.areaBST());
        System.out.println("¿Tienen la misma área?: " + sameArea(bst, otroBST));

        System.out.println("\nSegundo árbol:");
        otroBST.drawBST();
        
        LinkedBST<String> tree = new LinkedBST<>();

        try {
            tree.insert("Sales");
            tree.insert("Domestic");
            tree.insert("International");
            tree.insert("Canada");
            tree.insert("S. America");
            tree.insert("Overseas");
            tree.insert("Africa");
            tree.insert("Europe");
            tree.insert("Asia");
            tree.insert("Australia");
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // Imprimimos el árbol con sangría
        tree.parenthesize();
        
        tree.drawBST();

    }
}

