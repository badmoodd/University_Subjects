package org.example;


import org.example.HashMap.HashMap;

public class App {
    public static void main(String[] args) {

        HashMap map = new HashMap(4);
        System.out.println(map);

        map.put("Belarus", "Minsk");
        map.put("Russia", "Moscow");
        map.put("USA", "Washington");
        map.put("Canada", "Ottawa");
        map.put("Mexico", "Mexico City");
        map.put("Brazil", "Brasilia");
        map.put("Argentina", "Buenos Aires");
        map.put("UK", "London");
        map.put("France", "Paris");
        map.put("Germany", "Berlin");
        map.put("Italy", "Rome");
        map.put("China", "Beijing");
        System.out.println(map.size());
        System.out.println(map);

        System.out.println();
        System.out.println("Getting");
        System.out.println(map.get("USA"));

        System.out.println();
        System.out.println("Removing");
        map.remove("Germany");
        System.out.println(map);

        System.out.println();
        System.out.println("Reindexing");
        map.put("Germany", "Berlin");
        map.put("Japan", "Tokyo");
        System.out.println(map);

        HashMap n = new HashMap(5);
        n.put("Italy", "Rome");
        System.out.println(n);
        n.put("Italy", "Rome");
        System.out.println(n);


    }
}
