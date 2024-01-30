/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca1studentstatus;

/**
 *
 * @author Kev
 */
public class StudentDataValidation {
    //3 lines of input to validate
    private String line1;
    private String line2;
    private String line3;
    
    
    //4 data points required to create a new student if data is valid
    private String firstName;
    private String surname;
    private String studentNumber;
    private int numberClasses;
    
    //Message to display details of invalid data
    private String errorMessage;
    
    
    public StudentDataValidation(String[]lines){
        line1 = lines[0];
        line2 = lines[1];
        line3 = lines[2];
        
        //Initialise attributes
        firstName = "";
        surname = "";
        studentNumber = "Test";
        numberClasses = 0;
        
    }
    
    
    //Create a new student
    public Student createStudent(){
        Student student = new Student(firstName,surname,studentNumber,numberClasses);
        
        return student;
        
    }
    
    public boolean dataIsValid(){
        
        if( !validateStudentName(line1) ||
            !validateNumberOfClasses(line2) ||
            !validateStudentNumber(line3)){
           return false;
        }
        return true;
    }
    
    public boolean validateStudentName(String fullNameEntered){
      /*First name must be letters only; 
        The second name can be letters and/or numbers 
        and must be separated from the first name by a single space */
        
        //Assume data is valid until an error is found
        boolean isValid = true;
        
         //Verify exactly one space
        int countSpaces = 0;
          for(char c: fullNameEntered.toCharArray()){
                if(Character.isWhitespace(c)){
                     countSpaces ++;
                }
          }
        
        if(countSpaces != 1){
            errorMessage += "Exactly one space required between first name and surname.\n";
            isValid = false;
        }
       
        
        //Get the first name and check it is only letters
        for(char c: fullNameEntered.toCharArray()){
            if(!Character.isWhitespace(c)){
                
                if( !Character.isLetter(c)){
                    errorMessage += "First name can only contain letters(Aa-Zz).\n";
                    isValid = false;
                }else{
                    firstName = firstName + c;
                }
                
            }else{
                break;
            }
        }
       
        //Get the surname
        surname = fullNameEntered.substring(firstName.length() +1);
       
        //Verify surname cotains only letters and numbers
        for(char c: surname.toCharArray()){
           if(!Character.isLetterOrDigit(c)){
               errorMessage += "Surname can only contain letters and numbers(Aa-Zz,0-9).\n";
               isValid = false;
           }
        }
        
        return isValid;
    }
    
    public boolean validateNumberOfClasses(String numberEntered){
        try{
            numberClasses = Integer.parseInt(numberEntered);
            
        }catch(NumberFormatException ex){
            errorMessage += "Invalid number of classes entered on line 2. \n ";
            return false;
        }
        if(numberClasses > 0 && numberClasses < 9){
             return true;
        }
           
        return false;
    }
    
    public boolean validateStudentNumber(String studentNumberEntered){
        /*The student “number” must be a minimum of 6 characters
          with the first 2 characters being numbers, the 3rd  and 4th characters (and possibly 5th )
          being a letter, and everything after the last letter character being a number. 
          Ensure that the student number year is at least 2020 (i.e. that the number starts with 20 or higher) 
          Ensure that the number after the letter(s) is reasonable – i.e. that it is between 1 and 200*/
        

        //Assume data is valid until an error is found
        boolean isValid = true;
        
        //Verify student number has at least 6 characters
        if(studentNumberEntered.length()< 6){
            errorMessage += "Student number must be at least 6 characters";
            return false;
        }
        
        //Verify 2 numbers at start
        String firstTwoCharacters = studentNumberEntered.substring(0, 2);
        int year = 0;
        boolean validNumber = false;
        try{
            year = Integer.parseInt(firstTwoCharacters);
            
        }catch(NumberFormatException ex){
            errorMessage += "Student number must begin with 2 numbers, minimum 20. \n ";
            isValid = false;
            validNumber = false;
        }
        
        //Verify year is at least 2020
        if(year < 20){
            if(validNumber == false)//Prevent duplicate error message
                errorMessage += "Student number must begin with 2 numbers, minimum 20. \n ";
            isValid = false;
        }
        
        //Verify the 3rd  and 4th characters (and possibly 5th ) are letters
        
        if(!Character.isAlphabetic(studentNumberEntered.charAt(2))
           ||!Character.isAlphabetic(studentNumberEntered.charAt(3))){
            errorMessage += "The 3rd and 4th characters of student number must be letters.";
            isValid = false;
        }
        
        boolean fifthIsChar = false;
        
        //Verify 5th character is a letter OR a number
        if(!Character.isLetterOrDigit(studentNumberEntered.charAt(4))){
            errorMessage += "The 5th character of student number must be a letter or number.";
            isValid = false;
        }else{
            if(!Character.isAlphabetic(studentNumberEntered.charAt(4))){
               fifthIsChar = true; //check if it is a letter or number to correctly count digits at end
            }
        }
        
        
        //Verify last 3 characters are numbers in the range 001-200
        String charctersAfterLastLetter = "";
        
        if(fifthIsChar){
             charctersAfterLastLetter = studentNumberEntered.substring(6);
             
        }else{
             charctersAfterLastLetter = studentNumberEntered.substring(5);
        }
        
        int finalDigits = 0;
        boolean validFinalDigits = true;
        try{
            finalDigits = Integer.parseInt(charctersAfterLastLetter);
            
        }catch(NumberFormatException ex){
            errorMessage += "Student number must end with 1-3 numbers(Maximum 200). \n ";
            isValid = false;
            validFinalDigits = false;
        }
        
        if(validFinalDigits){//Prevent duplicate error msg
            if(!(finalDigits > 0 && finalDigits < 201)){
                errorMessage += "Student number must end with 1-3 numbers(Maximum 200). \n ";
                isValid = false;
            }
        }
        
        //No errors found, set the student number 
        if(isValid){
            studentNumber = studentNumberEntered;
            return true;
        }else{//error found, print error message
            System.out.println(errorMessage);
            return false;
        }
    }
    
}
