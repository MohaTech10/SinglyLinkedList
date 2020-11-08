import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E obj) {
            data = obj;
            next = null;
        }
    }
     Node<E> head;  // to point to the first node always and because of it we can make the new node added point the first one by it cuz it will be point to the first and will lead us to it in the heap
    private int numNodes;
     Node<E> tail;  // to always point to the last Node and keeptrack of and remebmber it

    public LinkedList() {
        head = tail = null;
        numNodes = 0;
    }
    
    private class IteratorHelper implements Iterator<E> {

        Node<E> index;
        IteratorHelper() {
            index = head;
        }
        @Override
        public boolean hasNext() {
            return (index != null);   // if head not null means are list contains value so has next
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E val = index.data;
            index = index.next;  // Moving the pointer to the next node
            return val;
        }
    }

    public boolean isEmpty() {

         return head == null;
    }

    // O(1)
    public E getLast() {
        if (isEmpty()) return null;
        return tail.data;
    }
    // O(1)
    public E getFirst() {
        if (isEmpty()) return null;
        return head.data;
    }

    // O(1)
    public void addFirst(E data)  {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {  // First time inserting

            insertEmptyList(newNode);
            return;
        }
        newNode.next = head;
        head = newNode;
        numNodes ++;

    }

    // O(1)
    public void addLast(E data) {
        // add Last to node using o(1)
        Node<E> newNode = new Node<E>(data);
        if (isEmpty())  // First time inserting
        {
            insertEmptyList(newNode);
            return;
        }
        tail.next = newNode;
        tail = newNode;
        numNodes ++;
    }

    // O(1)
    public E removeFirst() {
        if (isEmpty())   // Duplicate code use isEmpty() method
            return null;
        E temp = head.data;
        if (head == tail)  // Single Element in LinkedList and in that case the tail and the head point to the same reference
            head = tail = null ;
        else
            head = head.next;
        numNodes --;
        return temp;
    }

    // O(N)
    public E removeLast() {
        if (isEmpty()) return null;
        if (head == tail) return removeFirst();
        Node<E> current = head, previous = null;
        while (current != tail)   // will stop if currentNode becomes the last one
        {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        numNodes--;
        return current.data;
    }
    // O(N)
    public E getValueAtNode(int nodeN) {
        if (nodeN < 0 || nodeN > numNodes)
            throw new IndexOutOfBoundsException();
        Node<E> currentNode = head;
        int counter = 0;
        while (currentNode != null) {  // We could use the for loop instead however this while loop achieve to us the built-in functionality of isEmpty condition
            if (counter == nodeN) {
                return currentNode.data;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return null;
    }

    // O(N)
    public E removeAtNode(int nodeNumber) {
        if (nodeNumber < 0 || nodeNumber > numNodes) throw new IndexOutOfBoundsException();
        Node<E> current = head, previous = null;
        int position = 0;
        while (current != null) {
            if (position == nodeNumber)
            {
                if (current == head) return removeFirst();
                if (current == tail) return removeLast();
                numNodes --;
                previous.next = current.next;
                return current.data;
            }
            position ++;
            previous = current;
            current = current.next;
        }
        return null;
    }

    // Linear search which takes o(N)
    public boolean find(E element) {
        Node<E> current = head;   // if no element added then this == null and will not take the loop
        while (current != null)
        {
            if (((Comparable<E> ) current.data).compareTo(element) == 0)
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // For redundant code and it;s private as a practice of encapsulation
    private void insertEmptyList(Node<E> node) {
        head = tail = node ;
        numNodes++;
    }
    public int getNumNodes() { return numNodes; }

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    public void makeEmpty() {
        this.head = tail = null;
    }


    void insertAt(int position, E obj) {
        if (position > numNodes || 0 > numNodes) throw new IndexOutOfBoundsException();
        if (position == 0 ) addFirst(obj);
        else
        {
            int i = 1;
            Node<E> current = head, newNode = new Node<>(obj);
            while (i < position)
            {
                i ++;
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            numNodes ++;
        }
    }
    void reveres() {
        Node<E> previous = null, current = head, nextNode = head;
        while (nextNode != null) {
            nextNode = nextNode.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        tail = head;
        head = previous;
    }

    public boolean singleElementBoundary() {
        return head == tail;
    }

}
