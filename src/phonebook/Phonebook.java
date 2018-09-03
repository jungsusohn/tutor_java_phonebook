/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jungsu Sohn
 */
public class Phonebook {
    List newList;
    
    Phonebook() {
        this.newList = new List();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, FileNotFoundException, FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        List<Person> newList = new List<Person>();
        String input;
	boolean exit = false;

        do {
            System.out.println();
            System.out.format("****************************************%n");
            System.out.format("(F)etch \n(A)dd \n(P)rint List \nSearch by (N)ame \nSearch by (E)mail \n(D)elete \n(S)ave \n(Q)uit%n");
            System.out.format("****************************************%n");
            System.out.format("Please Enter a command: ");
            input = in.nextLine().toUpperCase();
            
            switch (input) {
                case "F":
                    File pb = new File("phoneBook.txt");
                    boolean empty = pb.exists() && pb.length() == 0;
                    // 위 불리언 변수의 "==0" 의 의미가 잘 이해가 안되요.
                    String name;
                    String phoneNumber;
                    String email;

                    if (empty == true) {
                        System.out.println("Nothing to fectch.");
                    } else {
                        BufferedReader br = new BufferedReader(new FileReader("phoneBook.txt"));
                        String line;

                        while (null != (line = br.readLine())) {
                            Person newNode = new Person();
                            String[] tokens = line.split(":|,");
                            name = tokens[1].trim();
                            phoneNumber = tokens[3].trim();
                            email = tokens[5].trim();
                            newNode.setName(name);
                            newNode.setNumber(phoneNumber);
                            newNode.setEmail(email);
                            newList.add(newNode);
                        }
                    }
                    break;
                case "A":
                    Person newNode = new Person();
                    System.out.println("Add an entry");
                    System.out.print("Enter a Name: ");
                    name = in.nextLine();
                    
                    System.out.print("Enter a Phone Number: ");
                    phoneNumber = in.nextLine();
                    
                    System.out.print("Enter an Email: ");
                    email = in.nextLine();
                    
                    newNode.setName(name);
                    newNode.setName(name);
                    newNode.setNumber(phoneNumber);
                    newNode.setEmail(email);
                    newList.add(newNode);
                    break;                                                                                        
                case "P": 
                    System.out.println("Print Phonebook");
                    newList.printList();
                    break; 
                case "N":
                    System.out.println("Enter a name to search: ");
                    name = in.nextLine();
                    boolean isFound = false;
                    for(int i = 0; i < newList.getLength(); i++) {
                        Person p = newList.getIdx(i);
                        if(p != null && p.getName().equals(name)) {
                            System.out.println(p);
                            isFound = true;
                        }
                    }
                    if(isFound == false) System.out.println("Not Found");
                    break;
                case "E":
                    System.out.println("Enter an Email to search: ");
                    email = in.nextLine();
                    isFound = false;
                    for(int i = 0; i < newList.getLength(); i++) {
                        Person p = newList.getIdx(i);
                        if(p != null && p.getEmail().equals(email)) {
                            System.out.println(p);
                            isFound = true;
                        }
                    }
                    if(isFound == false) System.out.println("Not Found");
                    break;                    
                case "D":
                    System.out.println("Enter a name to delete: ");
                    name = in.nextLine();
                    isFound = false;
                    for(int i = 0; i < newList.getLength(); i++) {
                        Person p = newList.getIdx(i);
                        if(p != null && p.getName().equals(name)) {
                            newList.delete(i);
                            isFound = true;
                        }
                    }
                    if(isFound == false) System.out.println("Not Found");
                    break;
                case "Q": 
                    System.out.println("Exiting Phonebook");
                    exit = true;
                    break;
                case "S":
                    BufferedWriter wr = null;
                    try { 
                        wr = new BufferedWriter(new FileWriter("phoneBook.txt", true));
                        if (newList.isEmpty()) {
                            System.out.println("Nothing on the Phonebook");
                        } else {
                            for(int i = 0 ; i < newList.getLength(); i++) {
                                wr.append(newList.getNode(i).toString());
                                wr.newLine();
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                        if (wr != null) {
                            try {
                                wr.close();
                            } catch (IOException ex) {
                            }
                        }
                    }
                    break;
                default: 
                    System.out.println("Unknown command");
            }
        }  while (!exit);
    }
}
