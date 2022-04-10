package com.example.guessmaster;

//Oliver Kramer (20210068)
public abstract class Entity {
    private String name;
    private Date born;
    private double difficulty;

    //default constructor
    public Entity() {
        //default constructor
        name = "";
        born = new Date();
    }

    //base constructor
    public Entity(String name, Date born, double difficulty) {
        this(); //using default values if setEntity throws error
        setName(name);
        setBorn(born);
        setDifficulty(difficulty);
    }

    //copy constructor
    public Entity(Entity entity) {
        name = entity.getName();
        born = entity.getBorn();
    }

    //setDifficulty method
    public void setDifficulty(double difficulty) {
        //takes value from 0 to 1 inclusive and sets the difficulty
        if (difficulty <= 1 && difficulty >= 0) {
            this.difficulty = difficulty;
        } else {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }

    //accessor method for difficulty
    public double getDifficulty() {
        return difficulty;
    }

    //mutator method for name
    public void setName(String name) {
        //checks to ensure there is a field
        if (name == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        } else {
            this.name = name;
        }
    }

    //mutator method for born
    public void setBorn(Date born) {
        //creates new object to avoid data leaks
        this.born = new Date(born);
    }

    //accessor method for born
    public Date getBorn() {
        //returning a new reference to the date to ensure there is no privacy leak
        return new Date(born);
    }

    //accessor method for name
    public String getName() {
        return name;
    }

    //toString method
    public String toString() {
        return ("Name: " + name + "\nBorn on: " + born.toString());
    }

    //equals method
    public boolean equals(Object otherObj) {
        //takes object to override parent method
        //if the object is null or from a different class it cannot be the same
        if (otherObj == null) {
            return false;
        } else if (getClass() != otherObj.getClass()) {
            return false;
        } else { //if the elements are from the same class then return if they are equal in fields
            Entity otherEntity = (Entity) otherObj;
            return (name.equals(otherEntity.name) && born.equals(otherEntity.getBorn()));
        }
    }

    //getAwardedTicketNumber method
    public int getAwardedTicketNumber() {
        return (int) (difficulty * 100);
    }

    //abstract method entityType definition
    public abstract String entityType();

    //abstract method clone definition
    public abstract Entity clone();

    //welcomeMessage method
    public String welcomeMessage() {
        //print a message to welcome player
        return ("Welcome! Let's start the game! " + entityType());
    }

    //closingMessage method
    public String closingMessage() {
        //print a method for a correct guess
        return ("Congratulations! The detailed information of the entity you guessed is:\n" + toString());
    }

}
