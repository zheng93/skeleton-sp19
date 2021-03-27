public class LinkedListDeque<T> {
    /** Nested class node. */
    public class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    /** attributes of LinkedListDeque. */
    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        Node pointerNode = other.sentinel.next;
        while(pointerNode != other.sentinel){
            this.addFirst((T)pointerNode.item);
            pointerNode = pointerNode.next;
        }
    }

    /** Methods of LinkedListDeque. */
    public void addFirst(T item){
        size += 1;
        sentinel.prev.next = new Node(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
    }

    public void addLast(T item){
        size += 1;
        Node newNode = new Node(item, sentinel.next, sentinel);
        newNode.next.prev = newNode;
        sentinel.next = newNode;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node currentNode = sentinel.prev;
        while(true){
            if(currentNode==sentinel){
                break;
            }
            else{
                System.out.println(currentNode.item);
                currentNode = currentNode.prev;
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if(this.size() == 0){
            return null;
        }
        Node firstNode = sentinel.prev;
        Node secondNode = firstNode.prev;
        T returnItem = firstNode.item;
        secondNode.next = sentinel;
        sentinel.prev = secondNode;
        size -= 1;
        return returnItem;
    }

    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }
        Node lastNode = sentinel.next;
        Node secondToLast = lastNode.next;
        T returnItem = lastNode.item;
        sentinel.next = secondToLast;
        secondToLast.prev = sentinel;
        return returnItem;
    }

    public T get(int index){
        if(index > this.size()-1){
            return null;
        }
        Node currentNode = sentinel;
        while(index >= 0){
            currentNode = currentNode.prev;
            index -= 1;
        }
        return currentNode.item;
    }

    public T getRecursiveHelper(int index, Node currentNode){
        if(index < 0){
            return currentNode.item;
        }
        return getRecursiveHelper(index-1, currentNode.prev);
    }

    public T getRecursive(int index){
        if(index > this.size()-1){
            return null;
        }
        return getRecursiveHelper(index, sentinel);
    }
}
