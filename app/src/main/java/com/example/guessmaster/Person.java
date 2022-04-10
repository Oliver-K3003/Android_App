package com.example.guessmaster;//Oliver Kramer (20210068)

public class Person extends Entity {
    private String gender;

    //default constructor
    public Person() {
        super();
        gender = "";
    }

    //person constructor
    public Person(String name, Date born, String gender, double difficulty) {
        //set base class parameters
        super(name, born, difficulty);
        setGender(gender);
    }

    //copy constructor
    public Person(Person person) {
        //call base class copy constructor
        super(person);
        gender = person.getGender();
    }

    //mutator method for gender
    public void setGender(String gender) {
        //ensure there is a value and set it
        if (gender == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        } else {
            this.gender = gender;
        }
    }

    //accessor method for gender
    public String getGender() {
        return gender;
    }

    //clone method
    public Person clone() {
        return new Person(this);
    }

    //toString method
    public String toString() {
        return (super.toString() + "\nGender: " + gender);
    }

    //entityType method
    public String entityType() {
        return ("This entity is a person!");
    }
}
