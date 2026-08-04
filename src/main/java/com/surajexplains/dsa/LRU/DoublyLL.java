package com.surajexplains.dsa.LRU;

public class DoublyLL {

    Node head;
    Node tail;

    public DoublyLL() {
        this.head = new Node(-1);
        this.tail = new Node(-1);

        this.head.nextNode = tail;
        this.tail.prevNode = head;
    }

    public void addFirst(Node node){
        node.nextNode = head.nextNode;
        node.prevNode = head.nextNode.prevNode;
        head.nextNode.prevNode = node;
        head.nextNode = node;
    }


    public void removeLast(){
        Node temp = tail.prevNode.prevNode;
        tail.prevNode.prevNode.nextNode = tail;
        tail.prevNode = temp;

    }

    public void remove(Node n){
        n.prevNode.nextNode = n.nextNode;
        n.nextNode.prevNode = n.prevNode;
    }


    public static class  Node {

        private Node prevNode;
        private int data;
        private Node nextNode;

        public Node(int data) {
            this.prevNode = null;
            this.data = data;
            this.nextNode = null;
        }

        public int getData() {
            return data;
        }
    }

}
