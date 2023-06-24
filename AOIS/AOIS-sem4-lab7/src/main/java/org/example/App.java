package org.example;

import org.example.Processor.Memory.Exceptions.MemoryException;
import org.example.Processor.Memory.Memory;
import org.example.Processor.Processor;

public class App {
    public static void main(String[] args) throws MemoryException {
        System.out.println("Random numbers");
        System.out.println("Here is our memory");
        Memory memory = new Memory(73, 42, 87, 19, 58, 66, 91, 35, 79, 11, 95, 68, 26, 83, 51, 76);
        System.out.println(memory);
        System.out.println();


        Processor processor = new Processor(memory);

        System.out.println("Just print all cutSections");
        System.out.println(processor.printAllMemoryCutSections());

        System.out.println();
        System.out.println("The result of function 4");
        System.out.println(processor.function4(11, 14)); //Indexes of Sections

        System.out.println();
        System.out.println("The result of function 6");
        System.out.println(processor.function6(12, 9)); //Indexes of Sections

        System.out.println();
        System.out.println("The result of function 9");
        System.out.println(processor.function9(10, 5)); //Indexes of Sections


        System.out.println();
        System.out.println("The result of function 11");
        System.out.println(processor.function11(11, 14)); //Indexes of Sections

        System.out.println();
        System.out.println("Do the sum of found Sections by mask");
        System.out.println("Print cutSections before sum");
        System.out.println(processor.printAllMemoryCutSections());
        processor.sumOfFields("10*");
        System.out.println();
        System.out.println("Print cutSections after sum");
        System.out.println(processor.printAllMemoryCutSections());


        System.out.println();
        System.out.println("Sort out amazing memory with merge sort algorithm");
        memory.mergeSort();
        System.out.println(memory);

        System.out.println();
        System.out.println("Reverse order of memory");
        memory.reverse();
        System.out.println(memory);


    }
}
