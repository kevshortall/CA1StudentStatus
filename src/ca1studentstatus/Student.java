/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca1studentstatus;

/**
 *
 * @author Kev
 */
public class Student {
    private String fullName;
    private String firstName;
    private String surname;
    private String studentNumber;
    private int numberClasses;
    private String workload;

    public Student(String firstName, String surname, String studentNumber, int numberClasses) {
       
       setFirstName(firstName);
       setSurname(surname);
       setFullName();
       setStudentNumber(studentNumber);
       setNumberClasses(numberClasses);
       setWorkload();
    }
    
    public String getFullName() {
        return fullName;
    }

    private void setFullName() {
        this.fullName = this.firstName + " " + this.surname;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    private void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getNumberClasses() {
        return numberClasses;
    }

    private void setNumberClasses(int numberClasses) {
        this.numberClasses = numberClasses;
    }

    public String getWorkload() {
        return workload;
    }

    private void setWorkload() {

       if(numberClasses == 1)
           workload = "Very Light";
       else if(numberClasses == 2)
            workload = "Light";
       else if(numberClasses < 6)
            workload = "Part Time";
       else 
            workload = "Full Time";
     
    }
    
    public String getStudentStatus(){
       
        //Format required for outputting to status.txt file
        String status = studentNumber + " - " +  surname + "\n" + workload + "\n";
        
        return status;
        
    }
    
    public void printStudentStatus(){
        //<Student number> - <Second Name>   Format required
        //<Workload>
        System.out.println(studentNumber + " - " + surname);
        System.out.println(workload);
        System.out.println("\n");
    }
}
