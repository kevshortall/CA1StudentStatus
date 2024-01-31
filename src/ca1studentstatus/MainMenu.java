/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca1studentstatus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        //Display menu in console, option to exit is there to prevent infinite loop
        do{
            displayMenu();
        }while(true);
        
    }
    
    private static void displayInvalidOptionMessage(){
        System.out.println("Invalid input. Please enter a number from 1-3");
        displayMenu();
    }
    
    private static void displayMenu(){
        System.out.println("\nSelect an option:");
        System.out.println("1. Read and validate data from students.txt file");
        System.out.println("2. Manually enter student data");
        System.out.println("3. Exit");
        int option = getUserSelection();
        performSelectedAction(option);
    }
    
    private static int getUserSelection(){
        //Scanner to read from console
        Scanner in = new Scanner(System.in);
        
        //Loop until a valid menu option entered
        int optionSelected = 0;
        do{
           try{
               //Parse value selected to int
               optionSelected = Integer.parseInt(in.next());
               
               if(optionSelected > 0 && optionSelected < 4)
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
    
    private static void performSelectedAction(int option){
        //1.Read and validate data from students.txt file
         if(option == 1){
            readAndValidateStudentsfromFile();
           
         }
         
        //Manually enter student data
        if(option == 2){
            manuallyEnterStudent();
        }
 
        //Option 3 exit program
        if(option == 3){
            System.exit(0);
        }
    }
    
    private static void readAndValidateStudentsfromFile(){
            //Read from file and add valid students to array list
            ArrayList<Student> students = getValidStudentsFromFile();
            
            //Write valid student data to file
            writeToStudentStatusFile((students));
            
            //Print validated student list
            System.out.println("List of validated students:\n");
            for(Student s: students){
                s.printStudentStatus();
            }
    }
    
    private static void manuallyEnterStudent(){
        Scanner in = new Scanner(System.in);
        
        String line1;
        String line2;
        String line3;
        
        System.out.println("Please enter name of student");
        line1 = in.nextLine();
        
        System.out.println("Please enter number of classes taken");
        line2 = in.nextLine();
        
        System.out.println("Please enter student number");
        line3 = in.nextLine();
        
        addUnvalidatedStudentToFile(new String[]{line1,line2,line3});
        
        readAndValidateStudentsfromFile();
        
    }
    
    private static void addUnvalidatedStudentToFile(String[]lines){
        File studentsFile = new File("students.txt");
        FileWriter studentsFileWriter = null;
        
        try{
            studentsFileWriter = new FileWriter(studentsFile, true);
            for(String s: lines){
                studentsFileWriter.append(s);
                studentsFileWriter.append("\n");
            }
           
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
        try {
            studentsFileWriter.flush();
            studentsFileWriter.close();
		
	} catch (IOException ex) {
             System.out.println(ex.toString());
	}
    }
    
    private static ArrayList<Student> getValidStudentsFromFile(){
        //Array list of arrays(3 lines) of student data read from file
        ArrayList<String[]>unValidatedStudentDetails = new ArrayList<>();
        
        //Array list to store validated students
        ArrayList<Student>validatedStudents = new ArrayList<>();
       
        //Array list to store each line in file
        ArrayList<String> data = new ArrayList<>();
        
        //Read data from students.txt
        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("students.txt"));
                        
                        //Begin reading from file
                        
                        String line = reader.readLine();
			while (line != null) {
                                //Add each line to data array list
                                data.add(line);
				line = reader.readLine();
			}
                        //close the reader    
			reader.close();
		} catch (IOException e) {
                    //Print any IO errors to console
                    System.out.println(e.toString());
		}
                
                //Loop through data to get arrays of 3 student data points and add to array list to be validated
                int index = 1;
                String line1= "";
                String line2 = "";
                String line3 = "";
                
                for(String s: data){
                    
                    if(index == 1)
                        line1 = s;
                    if(index == 2)
                        line2 = s;
                    if(index == 3){
                        line3 = s;
                        String[] nextStudent = new String[]{line1,line2,line3};
                        unValidatedStudentDetails.add(nextStudent);
                    }
                   //reset index every 3rd pass
                     if(index == 3)
                         index = 0;
                     //increment index
                     index ++;
                }
                
                //Loop through each array of unvalidated student details
                for(String[] studentDetails : unValidatedStudentDetails){
                   
                    //Create student validation object for each array
                    StudentDataValidation studentData = new StudentDataValidation(studentDetails);
                    
                    //Add validated students to arraylist
                    if(studentData.dataIsValid()){
                        Student validatedStudent = studentData.createStudent();
                        validatedStudents.add(validatedStudent);
                    }
                
                }
                
                return validatedStudents;
    }
    
    private static void writeToStudentStatusFile(ArrayList<Student> students){
       
        BufferedWriter statusFileWriter = null;
        try{
            statusFileWriter = new BufferedWriter(new FileWriter("status.txt", false));
           
            for(Student student : students){
                statusFileWriter.append(student.getStudentStatus());
            }
           
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
        try {
            statusFileWriter.flush();
            statusFileWriter.close();
		
	} catch (IOException ex) {
             System.out.println(ex.toString());
	}
		
    }
    
}
