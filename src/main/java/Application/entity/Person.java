package Application.entity;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    public Person(){}
    public Person(String firstName, String lastName, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    private String firstName;

    private String lastName;

    private String email;

    public boolean isValid(){

        if(this.email == null)
            return true;
        // ADD  regular expression to verify the Email
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
        );
        Matcher matcher = pattern.matcher(this.email);
        boolean matchFound = matcher.find();
        System.out.println("Regular expression result: " + matchFound);
        return this.email != null && this.firstName != null && this.lastName != null && matchFound;
    }

}
