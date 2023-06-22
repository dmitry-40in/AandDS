public class hw_03 {
    public static void main(String[] args) {
       List list = new List();
       for (int i = 1; i <= 10; i++) {
        list.pushFront(i);
       }

       list.print();
       System.out.println();
       list.revert();
       list.print();
    }
}


class List {
    static Node head;
    
    static class Node {
        int value;
        Node next;
    }


    public void pushFront(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;       
    }


    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
    }


    public void revert() {
        Node current = head.next;
        head.next = null;
        while (current != null) {
            Node next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
    }
}