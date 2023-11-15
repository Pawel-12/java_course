package com.solvd.laba.block1.task2;

import java.util.Collection;

public class MyLinkedList<T> {
    private static class Node<U> {
        private U value = null;
        private Node<U> next = null;


        public Node(U value) {
            this.value = value;
        }

        public U getValue() {
            return value;
        }

        public Node<U> getNext() {
            return next;
        }

        public void setValue(U value) {
            this.value = value;
        }

        public void setNext(Node<U> next) {
            this.next = next;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;

    private int size = 0;

    public MyLinkedList() {
    }

    public T get(int index) {
        if (head == null)
            return null;

        Node<T> temp = head;

        for (int i = 0; i < index; i++)
            if (temp.getNext() != null)
                temp = temp.getNext();
            else
                return null;

        return temp.getValue();
    }

    private Node<T> getNode(int index) {
        if (head == null)
            return null;

        Node<T> temp = head;

        for (int i = 0; i < index; i++)
            if (temp.getNext() != null)
                temp = temp.getNext();
            else
                return null;

        return temp;
    }

    public void add(T arg) {
        if (head != null) {
            Node<T> temp = head;

            while (temp.getNext() != null)
                temp = temp.getNext();

            temp.setNext(new Node<>(arg));
            this.tail = temp.getNext();
        } else
            tail = head = new Node<>(arg);

        size++;
    }

    public void addAll(Collection<T> c) {
        for (T val : c)
            add(val);
    }

    public void remove(int index) {
        if (head == null || getNode(index) == null)
            return;

        Node<T> before = getNode(index - 1);
        Node<T> after = getNode(index + 1);

        if (before != null)
            before.setNext(after);
        else
            head = head.getNext();

        size--;
    }

    public void set(int index, T value) {
        if (getNode(index) == null)
            return;

        getNode(index).setValue(value);
    }

    public T getHead() {
        return head.getValue();
    }

    public T getTail() {
        return tail.getValue();
    }

    public int size() {
        return size;
    }
}

/*
        MyLinkedList<Integer> list1 = new MyLinkedList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        System.out.println(list1.get(2));
        System.out.println(list1.get(3));
        System.out.println(list1.getHead());
        System.out.println(list1.getTail());
        System.out.println(list1.size());

        list1.remove(3);

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        System.out.println(list1.get(2));

        System.out.println(list1.size());
 */