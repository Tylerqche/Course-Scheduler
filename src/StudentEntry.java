import java.lang.Object;

public class StudentEntry {
    private String studentID;
    private String firstName;
    private String lastName;

    public StudentEntry(String studentID, String firstName, String lastName)
    {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStudentID() 
    {
        return this.studentID;
    }
    public String getFirstName() 
    {
        return this.firstName;
    }
    public String getLastName() 
    {
        return this.lastName;
    }
    
        public String lastFirstName()
    {
        return this.lastName + ", " + this.firstName;
    }
    
        public String fullID()
    {
        return lastFirstName() + " " + this.studentID;
    }
 
    // string representation of object `StudentEntry`
    @Override
    public String toString () {
      return String.format(
        "%s,%s",
        getLastName(),
        getFirstName()
      );
    }
  
}