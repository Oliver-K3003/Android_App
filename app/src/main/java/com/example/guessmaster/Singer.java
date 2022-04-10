package com.example.guessmaster;//Oliver Kramer (20210068)

public class Singer extends Person {
    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    //default constructor
    public Singer() {
        super();
        debutAlbum = "";
        debutAlbumReleaseDate = new Date();
    }

    //singer constructor
    public Singer(String name, Date born, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
        //calls the base class constructor to assign all values
        super(name, born, gender, difficulty);
        setDebutAlbum(debutAlbum);
        setDebutAlbumReleaseDate(debutAlbumReleaseDate);
    }

    //copy constructor
    public Singer(Singer singer) {
        //invokes the base class copy constructor
        super(singer);
        debutAlbum = singer.getDebutAlbum();
        debutAlbumReleaseDate = singer.getDebutAlbumReleaseDate();
    }

    //mutator method for debutAlbum
    public void setDebutAlbum(String debutAlbum) {
        //ensures that the value exists and sets it
        if (debutAlbum == null) {
            System.out.println("Fatal Error");
            System.exit(0);
        } else {
            this.debutAlbum = debutAlbum;
        }
    }

    //accessor method for debutAlbum
    public String getDebutAlbum() {
        return debutAlbum;
    }

    //mutator method for debutAlbumReleaseDate
    public void setDebutAlbumReleaseDate(Date debutAlbumReleaseDate) {
        //creates new object to stop privacy leak
        this.debutAlbumReleaseDate = new Date(debutAlbumReleaseDate);
    }

    //accessor method for debutAlbumReleaseDate
    public Date getDebutAlbumReleaseDate() {
        //creates new object to stop privacy leak
        return new Date(debutAlbumReleaseDate);
    }

    //clone method
    public Singer clone() {
        return new Singer(this);
    }

    //toString method
    public String toString() {
        return (super.toString() + "\n Debut Album: " + debutAlbum + "\nDebut Album Release Date: " + debutAlbumReleaseDate);
    }

    //entityType method
    public String entityType() {
        return ("This entity is a singer!");
    }
}
