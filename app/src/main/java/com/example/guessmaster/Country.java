package com.example.guessmaster;//Oliver Kramer (20210068)

public class Country extends Entity {
    private String capital;

    //default constructor
    public Country(){
        super();
        capital = "";
    }

    //constructor
    public Country(String name, Date born, String capital, double difficulty){
        //calls super to ensure all fields are populated
        super(name, born, difficulty);
        setCapital(capital);
    }

    //copy constructor
    public Country(Country country){
        //calls super copy constructor
        super(country);
        capital = country.getCapital();
    }

    //mutator method for capital
    public void setCapital(String capital){
        //ensure there is a value for capital and set that value to current object
        if (capital == null){
            System.out.println("Fatal Error");
            System.exit(0);
        }else{
            this.capital = capital;
        }
    }

    //accessor method for capital
    public String getCapital(){
        return capital;
    }

    //clone method
    public Country clone(){
        //return copy constructor
        return new Country(this);
    }

    //toString method
    public String toString(){
        return (super.toString() + "\nCapital: " + capital);
    }

    //entityType method
    public String entityType(){
        return ("This entity is a country!");
    }
}
