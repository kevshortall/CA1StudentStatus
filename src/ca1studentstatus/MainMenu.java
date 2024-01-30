/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1studentstatus;
import java.util.Scanner;

/**
 When in operation, the program will be given a file named “students.txt” – this contains the details of (fictitious) students in the following format: 

Line 1 – <First Name> <Second Name>
Line 2 – <Number of classes>
Line 3 – <Student number>

Your task is to: 
Read the data from the file and check that it is valid. The file may contain more than one student, so an appropriate loop should be used. The data must obey the following rules: 
the first name must be letters only; 
The second name can be letters and/or numbers and must be separated from the first name by a single space; 
the number of classes must be an integer value between 1 and 8 (inclusive), and 
the student “number” must be a minimum of 6 characters with the first 2 characters being numbers, the 3rd  and 4th characters (and possibly 5th ) being a letter, and everything after the last letter character being a number.

If the data is not valid, you should output a useful error message on screen to the user.

If the data is valid, then you have to output the data to a file named status.txt, in the following format:

<Student number> - <Second Name>
<Workload>

Where the <Workload> is determined by the number of classes, as follows:


Number of classes
Workload
1
Very Light
2
Light
3, 4, or 5
Part Time
6 or higher
Full Time




Examples:

Input:
Sam Weiss
5
22DIP1123


Would output as:
22DIP1123 – Weiss
Part Time

and

Steve Rodgers
7
20MSC1914

Would output as:
20MSC1914 – Rodgers
Full Time
* 
* 
* DISTINCTION WORK 

To gain a distinction (70% or higher) you must complete tasks 1) – 3) properly AND ALSO: 
Ensure that the student number year is at least 2020 (i.e. that the number starts with 20 or higher) 
Ensure that the number after the letter(s) is reasonable – i.e. that it is between 1 and 200
The program has a menu that lets the user decide between standard operation or adding (validated) data to the status.txt file via the console. (Invalid data should NOT be saved).


 * @author Kev
 */
public class MainMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        displayMenu();
        int option = getUserSelection();
        
        if(option == 1){
            
        }
        
        if(option == 2){
            
        }
        
        if(option == 3){
            
        }
        
        //Option 4 exit program
        if(option == 4){
            System.exit(0);
        }
    }
    
    
    private static int getUserSelection(){
        int optionSelected = 0;
        
        Scanner in = new Scanner(System.in);
        
        
        //Loop until a valid menu option entered
        do{
           try{
               //parse value selected to int
               optionSelected = Integer.parseInt(in.next());
               
               if(optionSelected > 0 && optionSelected < 5)
                   return optionSelected;
               else{
                displayInvalidOptionMessage();
               }
                   
               
           }catch(NumberFormatException ex){
               displayInvalidOptionMessage();
           }
          
        }while(optionSelected < 1 || optionSelected > 4);
        
        return optionSelected;
        
    }
    
    private static void displayInvalidOptionMessage(){
        System.out.println("Invalid input. Please enter a number from 1-4");
        displayMenu();
    }
    
    private static void displayMenu(){
        System.out.println("Select an option");
        System.out.println("1. Read and validate data from students.txt file");
        System.out.println("2. Manually enter student data");
        System.out.println("3. Display validated student status list");
        System.out.println("4. Exit");
    }
    
}
