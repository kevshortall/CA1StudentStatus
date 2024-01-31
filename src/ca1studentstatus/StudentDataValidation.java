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
        numberClasses = 0;
        studentNumber = "";
        
        //Initialise error message
        String fullName = line1;
        String numClasses = line2;
        String studentNum = line3;
        errorMessage = "Invalid Data for Student: " + fullName + 
                       " >>> Classes: " + numClasses +
                       " >>> Student #: " + studentNum + "\n";
        
    }
    
    public Student createStudent(){
        //Create a new student
        Student student = new Student(firstName,surname,studentNumber,numberClasses);
        
        return student;
        
    }
    
    public boolean dataIsValid(){
        //Call each validation method to populate error messages
        boolean validName  = validateStudentName(line1);
        boolean validNumClasses = validateNumberOfClasses(line2);
        boolean validStudentNumber = validateStudentNumber(line3);
             
        if(!validName ||
           !validNumClasses ||
           !validStudentNumber ){
            //Print any error messages
            printErrorMessages();
            return false;
        }
        //No errors found
        return true;
    }
    
    private void printErrorMessages(){
        System.out.println(errorMessage + "\n");
    }
    
    public boolean validateStudentName(String fullNameEntered){
      /*First name must be letters only; 
        The second name can be letters and/or numbers 
        and must be separated from the first name by a single space */
      
      
       /* *******Following instructions exactly, names such as O'Connor would be invalid!!
                (apostrophe is not a letter). Allowing apostrophes and also spaces
                in surname to allow for names such as Da Silva.
     
      */
         
        //Assume data is valid until an error is found
        boolean isValid = true;
        
        //Get the first name and check it is only letters
        for(char c: fullNameEntered.toCharArray()){
            if(!Character.isWhitespace(c)){
                
                if( !Character.isLetter(c)){
                    errorMessage = errorMessage + "-First name can only contain letters(Aa-Zz).\n";
                    isValid = false;
                }else{
                    firstName = firstName + c;
                }
                
            }else{
                break;
            }
        }
        
        //Check that there is exactly a single space between first and second names
        
        //No space at all
        if(firstName.length() == fullNameEntered.length()){
             errorMessage = errorMessage + "-Must be a space between first name and surname.\n";
             return false;
        }
        
        //More than one space between names
        char firstLetterOfSurname = fullNameEntered.charAt(firstName.length() + 1);
        
        if(Character.isWhitespace(firstLetterOfSurname) ){
             errorMessage = errorMessage + "-Must be only a single space between first name and surname.\n";
             isValid = false;
        }
          
        //Get the surname 
        surname = fullNameEntered.substring(firstName.length() +1);
       
        //Verify surname contains only letters, numbers, apostrophes - allow spaces but not at start(already caught)
        for(char c: surname.toCharArray()){
           if(!Character.isLetterOrDigit(c) && c != '\'' && c != ' '){
               errorMessage = errorMessage + "-Surname can only contain letters, numbers and apostrophes.\n";
               isValid = false;
           }
        }
        
        return isValid;
    }
    
    public boolean validateNumberOfClasses(String numberEntered){
        try{
            numberClasses = Integer.parseInt(numberEntered);
            
        }catch(NumberFormatException ex){
            errorMessage = errorMessage + "-Invalid number of classes entered, must be between 1 and 8. \n";
            return false;
        }
        //Valid number
        if(numberClasses > 0 && numberClasses < 9){
             return true;
        }
        
        errorMessage = errorMessage + "-Invalid number of classes entered, must be between 1 and 8. \n";
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
            errorMessage = errorMessage + "-Student number must be at least 6 characters\n";
            return false;//return false here as subsequent checks depend on at least 6 characters
        }
        
        //Verify 2 numbers at start
        String firstTwoCharacters = studentNumberEntered.substring(0, 2);
        int year = 0;
        boolean validNumber = false;
        try{
            year = Integer.parseInt(firstTwoCharacters);
            
        }catch(NumberFormatException ex){
            errorMessage = errorMessage + "-Student number must begin with 2 numbers, minimum 20. \n";
            isValid = false;
            validNumber = false;//Prevent duplicate error message
        }
        
        //Verify year is at least 2020
        if(year < 20){
            if(validNumber)
                errorMessage = errorMessage + "-Student number must begin with 2 numbers, minimum 20. \n";
            isValid = false;
        }
        
        //Verify the 3rd  and 4th characters (and possibly 5th ) are letters
        
        if(!Character.isAlphabetic(studentNumberEntered.charAt(2))
           ||!Character.isAlphabetic(studentNumberEntered.charAt(3))){
            errorMessage = errorMessage + "-The 3rd and 4th characters of student number must be letters.\n";
            isValid = false;
        }
        
        //Need to check if it is a letter or number to correctly check digits at end
        boolean fifthIsChar = false;
        
        //Verify 5th character is a letter OR a number
        if(!Character.isLetterOrDigit(studentNumberEntered.charAt(4))){
            errorMessage = errorMessage + "-The 5th character of student number must be a letter or number.\n";
            isValid = false;
        }else{
            if(Character.isAlphabetic(studentNumberEntered.charAt(4))){
               fifthIsChar = true; 
            }
        }
      
        
        //Verify last 3 characters are numbers in the range 001-200
        String charctersAfterLastLetter;
        
        if(fifthIsChar){
             charctersAfterLastLetter = studentNumberEntered.substring(5);
            
        }else{
             charctersAfterLastLetter = studentNumberEntered.substring(4);
               
        }
       
        int finalDigits = 0;
        boolean validFinalDigits = true;
        try{
            finalDigits = Integer.parseInt(charctersAfterLastLetter);
            System.out.println(finalDigits);
            
        }catch(NumberFormatException ex){
            errorMessage = errorMessage + charctersAfterLastLetter+"-Student number must contain 3-5 letters after the first 2 numbers). \n";
            isValid = false;
            validFinalDigits = false;
        }
        
        if(validFinalDigits){//Prevent duplicate error msg
            if(!(finalDigits > 0 && finalDigits < 201)){
                errorMessage = errorMessage + "-Student number must end a number between 1 and 200. \n";
                isValid = false;
            }
        }
        
        //No errors found, set the student number 
        if(isValid){
            studentNumber = studentNumberEntered;
            return true;
        }else{//Errors found
            return false;
        }
    }
    
}
