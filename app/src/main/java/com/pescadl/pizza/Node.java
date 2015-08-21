package com.pescadl.pizza;

/**
 * Created by dayoung on 8/21/2015.
 */
public class Node{
    String name;
    int people;
    String fraction;

    public Node(){
        name = "";
        people = 0;
        fraction = "";
    }

    public Node(String n, int p, String f){
        name = n;
        people = p;
        fraction = f;
    }

    @Override
    public String toString(){
        return String.format("{name=%s, people=%d, fraction=%s}", name, people, fraction);
    }
}
