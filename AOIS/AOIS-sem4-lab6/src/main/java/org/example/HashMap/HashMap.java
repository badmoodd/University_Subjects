package org.example.HashMap;

import java.util.Objects;

public class HashMap {
    private Node[] table;
    private int size;
    private int capacity;

    private static class Node {
        final int hash;
        String key;
        String value;
        Node next;

        public Node(int hash, String key, String value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return hash == node.hash && key.equals(node.key) && value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

    }

    public HashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    capacity);
        }
        this.table = new Node[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void put(String key, String value) {
        int hash = Math.abs(key.hashCode());
        int index = hash % capacity;
        Node node = new Node(hash, key, value, null);

        Node head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        if (table[index] == null) {
            table[index] = node;
        } else {
            head = table[index];
            while (head.next != null) {
                head = head.next;
            }
            head.next = node;
        }
        ++size;
        float LOAD_FACTOR = 0.75f;
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
    }

    public String get(String key) {
        int hash = Math.abs(key.hashCode());
        int index = hash % capacity;

        if (table[index] == null) {
            return null;
        } else {
            Node head = table[index];

            while (head.next != null) {
                if ((hash == head.hash) && Objects.equals(head.key, key)) {
                    return head.value;
                }
                head = head.next;
            }

            if ((hash == head.hash) && Objects.equals(head.key, key)) {
                return head.value;
            } else {
                return null;
            }

        }
    }

    public void remove(String key) {
        int hash = Math.abs(key.hashCode());
        int index = hash % capacity;

        Node prev = null;
        Node curr = table[index];

        while (curr != null) {
            if (Objects.equals(curr.key, key)) {
                if (prev == null) {
                    table[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                --size;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize() {
        int newCapacity = capacity * 2;
        Node[] newTable = new Node[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Node head = table[i];
            while (head != null) {
                Node next = head.next;
                int newIndex = Math.abs(head.hash) % newCapacity;
                head.next = newTable[newIndex];
                newTable[newIndex] = head;
                head = next;
            }
        }

        this.capacity = newCapacity;
        this.table = newTable;
    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();

        if (!isEmpty()) {

            for (int i = 0; i < capacity; i++) {
                StringBuilder element = new StringBuilder();
                if (table[i] == null) {
                    element.append("[null]\n");
                    map.append(element);
                } else {
                    String keyString = table[i].key;
                    String valueString = table[i].value;
                    element.append(keyString).append("=").append(valueString);

                    if (table[i].next != null) {

                        Node node = table[i];
                        while (node.next != null) {
                            node = node.next;

                            keyString = node.key;
                            valueString = node.value;
                            element.append(" -> ").append(keyString).append("=").append(valueString);
                        }
                    }
                    map.append(element).append("\n");
                }
            }
            return map.toString();

        } else return null;
    }
}