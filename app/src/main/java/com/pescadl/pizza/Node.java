package com.pescadl.pizza;

/**
 * Created by dayoung on 8/21/2015.
 */
public class Node{
    String name;
    int numPeople;
    String numPizzas;

    public Node(){
        name = "";
        numPeople = 0;
        numPizzas = "";
    }

    public Node(String n, int p, String f){
        name = n;
        numPeople = p;
        numPizzas = f;
    }

    @Override
    public String toString(){
        return String.format("{name=%s, people=%d, fraction=%s}", name, numPeople, numPizzas);
    }
}
