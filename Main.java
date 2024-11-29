package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float note_java;
        float note_DB;
        float note_Tw;
        float moyenne;


        System.out.println("Enter note java: ");
        note_java = sc.nextFloat();
        System.out.println("Enter note DB: ");
        note_DB = sc.nextFloat();
        System.out.println("Enter note Tw: ");
        note_Tw = sc.nextFloat();

        moyenne = (note_java + note_DB + note_Tw) / 3;
        System.out.println("Moyenne: " + moyenne);

    }
}