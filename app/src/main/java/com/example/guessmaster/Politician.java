package com.example.guessmaster;//Oliver Kramer (20210068)

public class Politician extends Person {
    private String party;

    //default constructor
    public Politician() {
        super();
        party = "";
    }

    //politician constructor
    public Politician(String name, Date born, String gender, String party, double difficulty) {
        //call super to populate all fields appropriately
        super(name, born, gender, difficulty);
        setParty(party);
    }

    //copy constructor
    public Politician(Politician politician) {
        //call base class copy constructor
        super(politician);
        party = politician.getParty();
    }

    //mutator method for party
    public void setParty(String party) {
        //ensure party has a value then set it
        if (party == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        } else {
            this.party = party;
        }
    }

    //accessor method for party
    public String getParty() {
        return party;
    }

    //clone method
    public Politician clone() {
        return new Politician(this);
    }

    //toString method
    public String toString() {
        return (super.toString() + "\nParty: " + party);
    }

    //entityType method
    public String entityType() {
        return ("This entity is a politician!");
    }
}
