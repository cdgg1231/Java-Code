/*
 * CS 2050 - Computer Science II - Summer 2021
 * Instructor: Thyago Mota
 * Description: Activity 26 - Hashtable
 */

import java.util.Arrays;
import java.util.Iterator;

public class Hashtable<K,V> implements Iterable<V> { //iterator returning only values

    private static final int SIZE = 11;
    private HashNode<K, V> table[]; // static array with hash nodes. a linkedlist with many tables
    //the indexes of this table match the hashcode values

    public Hashtable() {
        table = new HashNode[SIZE];  //need to initialize (instantiate) the array
    }

    //hash function
    private int hash(K key) {  //take a key return a value
        return Math.abs(key.hashCode()) % SIZE;   //modulus 11 of they key. hashcode will return an int base on the obj
        //abs so it won't be negative
    }

    public void put(K key, V value) { //pass a key and a value
        int index = hash(key); //this is to associate the right index in the array

        // The process is like adding in linkedlist, but not adding immodestly the head node.

        // TODOd: search the for a (key, value) association
        // if it exists, replace the value of the node
        // if not, just add a new node to create the association
        HashNode<K, V> current = table[index]; //index was the output of key hash association with index locaton
        boolean found = false;  //flag variable
        while (current != null) {
            if (key.equals(current.getKey())) { //key is the one trying to find > current.getKey
                found = true; //this tells that we found the key
                break;
            }
            current = current.getNext();
        }
        if (found)
            current.setValue(value); //if key already there change the new  value associated with the key
        else {  // not found > add the new asssociatin by creating new Node
            HashNode<K, V> newNode = new HashNode<>(key, value); // this creates new node like linkedlist add
            newNode.setNext(table[index]);  // this does head insert to point to the head of the linked list
            table[index] = newNode;  // then make the new node the new head
        }
    }
    //similar to put. find the key or not
    public V get(K key) { // returns the key >>>>>>> the V: Value return is type V
        int index = hash(key);  //figure out the index location
        HashNode<K, V> current = table[index];
        boolean found = false;
        while (current != null) {
            if (key.equals(current.getKey())) {
                found = true;
                break;
            }
            current = current.getNext();  //otherwise it loops and loops
        }
        if (found)
            return current.getValue();
        return null; // if not return null
    }

    //helper method for toString
    private String linkedListToString(HashNode<K,V> head) { //pass the head for each tostring you want to pass
        String out = "";
        HashNode<K, V> current = head;
        while (current != null) {
            out += current + " ";
            current = current.getNext();
        }
        // optional (get rid off the last space)
        out = out.trim();
        return out;
    }

    @Override
    public String toString() { //of many likedlist
        String out = "";
        for (int i = 0; i < SIZE; i++)
            out += i + " -> " + linkedListToString(table[i]) + "\n"; //for each index print the linkedlist then\nenter
        return out.trim();
    }

    @Override
    // iterator is going to return an iterator of the key
    //then use get to get individial keys
    //go through each index and iterate over each of the linkedlist
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int index = 0; //start with index 0 ,
            // but first make sure to return all the values first
            HashNode<K, V> current = table[index];

            @Override
            public boolean hasNext() { //depending if there is an association or not
                while (true) {  //trying to find if there is next or not
                    if (index == SIZE)  // when index reach size return false right away. nothing else to iterate over
                        break; //when ever you break the loop you return false
                    if (current == null) {
                        index++; // need to advance index
                        if (index < SIZE) //if index is still valid update current
                            current = table[index];
                    }
                    else
                        return true; // not null return true. It checks index
                }
                return false;
            }

            @Override
            public V next() {
                if (hasNext()) { //if it has next we need to return
                    V value = current.getValue(); //create a value
                    current = current.getNext(); //advance value
                    return value;
                }
                return null; //if there is nothing to return.. return null
            }
        };
    }
}