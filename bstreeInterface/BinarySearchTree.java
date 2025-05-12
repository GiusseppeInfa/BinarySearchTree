package bstreeInterface;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public interface BinarySearchTree<E> {
	void insert(E data) throws ItemDuplicated;
	E search(E data) throws ItemNotFound;
	void delete(E data) throws ExceptionIsEmpty;
	boolean isEmpty();
}