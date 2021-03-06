package com.unlu.erkin.lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by ERKIN on 25/01/15.
 */
public class LinkedListImpl<T> {

    public static void main(String[] args) {

        Node node;

        node = new Node(5);
        node.append(4);
        node.append(3);
        node.append(2);
        node.append(5);
        node.append(1);

        node.print();

        node = reverse(node, null);

        System.out.println("printing reverse: ");
        node.print();

        node = reverse(node, null);

        Node<Integer> lt = new Node<>();
        Node<Integer> gt = new Node<>();

        Node removed = removeElements(node, 5);

        removed.print();
    }

    public List<T> flatten (Node<T> head) {
        //if has a child go a process
        //get to next

        List<T> acc = new ArrayList<T>();
        Node current = head;

        while (current != null) {
            System.out.println("adding current val");
            acc.add((T) current.val);

            if (current.child != null) {
                System.out.println("adding child values");
                acc.addAll(flatten(current.child));
            }

            System.out.println("switching to next");
            current = current.next;
        }

        return acc;

    }

    public static class Node<T> {
        Node next;
        Node child;
        T val;

        public Node(){}

        public Node(T val) {
            this.val = val;
        }

        public void append(T val) {
            Node cur = this;
            while (cur.next != null) {
                cur = cur.next;
            }

            cur.next = new Node(val);
        }

        public void append(Node node) {
            Node cur = this;
            while (cur.next != null) {
                cur = cur.next;
            }

            cur.next = node;
        }

        public void print() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");

            Node cur = this;
            while (cur != null && cur.val != null) {
                builder.append(cur.val + ", ");
                cur = cur.next;
            }

            builder.append("]");

            System.out.println(builder.toString());
        }
    }

    private static Node removeDups(Node head) {
        Node cur = head;
        Set set = new HashSet<>();

        while (cur.next != null) {
            set.add(cur.val);
            cur = cur.next;
        }

        Node newHead = new Node();
        int i = 0;
        for (Object obj : set) {

            if (i == 0) {
                newHead.val = obj;
            }
            else {
                newHead.append(obj);
            }
            i++;
        }

        return newHead;
    }

    private static Node removeDupsWithoutBuffer(Node head) {
        Node cur = head;

        while (cur.next != null) {
            Node runner = cur.next;
            Node previous = cur;
            while (runner != null) {
                if (runner.val == cur.val) {
                    //delete
                    previous.next = runner.next;
                }

                runner = runner.next;
                previous = previous.next;
            }

            cur = cur.next;
        }

        return head;
    }

    private static Node nthToLastElem(Node head, int n) {
        Node cur = head;
        Stack<Node> nodeStack = new Stack<>();

        while (cur.next != null) {
            nodeStack.push(cur);
            cur = cur.next;
        }

        nodeStack.push(cur);

        Node ret = null;
        for (int i = 0; i < n; i++) {
            ret = nodeStack.pop();
        }

        return ret;
    }

    private static void deleteNode(Node elem) {
        Node next = elem.next;

        elem.val = next.val;
        elem.next = next.next;
    }

    private static int toInt(Node<Integer> head) {
        if (head == null) { return 0; }

        int digit = 0; int sum = 0;

        Node cur = head;
        while (cur != null) {
            int power = (int) Math.pow(10, digit++);
            sum += power * (int)cur.val;
            cur = cur.next;
        }

        return sum;
    }

    private static Node<Integer> toNode(int number) {
        int remainder = number;

        Node head = new Node(remainder % 10);
        remainder /= 10;

        while (remainder > 0) {
            head.append(remainder % 10);
            remainder /= 10;
        }

        return head;
    }

    public static Node addListsAsIntS(Node num1, Node num2) {
        int i1 = toInt(num1);
        int i2 = toInt(num2);

        int result = i1 + i2;

        return toNode(result);
    }

    public static Node findCyclicNode(Node head) {
        Set<Node> visitedNodes = new HashSet<>();

        Node cur = head;
        while (cur != null) {
            if (visitedNodes.contains(cur)) {
                return cur;
            }

            visitedNodes.add(cur);
            cur = cur.next;
        }

        return null;
    }

    public static Node reverse(Node head, Node carry) {
        if (head == null) {
            return carry;
        }

        Node remain = head.next;

        Node newHead;
        newHead = new Node(head.val);
        if (carry != null) {
            newHead.next = carry;
        }

        return reverse(remain, newHead);
    }

    private static Node addToLast(Node carry, Node head) {
        if (carry == null) {
            return head;
        }

        Node temp = carry;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = head;

        return carry;
    }

    private static void split(Node<Integer> head, int pivot, Node<Integer> lt, Node<Integer> gt) {
        Node<Integer> current = head;

        while (current != null) {
            if (current.val < pivot) {
                lt.val = current.val;
                lt.next = new Node();
                lt = lt.next;
            }
            else {
                gt.val = current.val;
                gt.next = new Node();
                gt = gt.next;
            }

            current = current.next;
        }
    }

    private static Node remove(Node<Integer> head, int val) {
        Node<Integer> current = head;

        Node prev = null;
        while (current != null) {
            if (current.val == val) {
               if (prev == null) {
                   return current.next;
               }
               else {
                   Node next = current.next;
                   prev.next = next;
                   break;
               }
            }
            else {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }

    private static Node removeElements(Node<Integer> head, int val) {
        Node<Integer> current = head;
        Node<Integer> currentHead = head;

        Node prev = null;
        while (current != null) {
            if (current.val == val) {
                if (prev == null) {
                    currentHead = current.next;
                }
                else {
                    Node next = current.next;
                    prev.next = next;
                }
            }
            else {
                prev = current;
            }
            current = current.next;
        }

        return currentHead;
    }
}
