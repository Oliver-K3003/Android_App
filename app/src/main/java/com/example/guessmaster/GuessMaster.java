//Oliver Kramer 20210068
package com.example.guessmaster;

//Importing required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.content.DialogInterface;
import java.util.Random;

//GuessMaster class
public class GuessMaster extends AppCompatActivity {
    //Creating instance variables
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private Button btnclearContent;
    private EditText userIn;
    private String user_input;
    private ImageView entityImage;
    String answer;

    private int numOfEntities;
    private Entity[] entities;
    private int numOfTickets;
    private int totaltik;
    int entityid = 0;



    //onCreate method for when app starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the correct .xml file to the layout
        setContentView(R.layout.activity_guess_master);

        //setting all the corresponding variables to the correct elements
        entityName = (TextView) findViewById(R.id.entityName);
        ticketsum = (TextView) findViewById(R.id.ticket);
        guessButton = (Button) findViewById(R.id.btnGuess);
        btnclearContent = (Button) findViewById(R.id.btnClear);
        userIn = (EditText) findViewById(R.id.guessInput);
        entityImage = (ImageView) findViewById(R.id.entityImage);

        //initializing the entities in the game
        Country usa= new Country("United States", new Date("July", 4, 1776), "Washington DC", 0.1);
        Person myCreator= new Person("myCreator", new Date("May", 6, 1800), "Male", 1);
        Politician trudeau = new Politician("Justin Trudeau", new Date("December",25,1971),"Male","Liberal", 0.25);
        Singer dion= new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November",6,1981),0.5);
        final GuessMaster gm = new GuessMaster();
        //adding the entities
        addEntity(trudeau);
        addEntity(usa);
        addEntity(dion);
        addEntity(myCreator);

        //doing some initial setup
        changeEntity();
        welcomeToGame(entities[entityid]);

        //setting the buttons to create actions based on click
        btnclearContent.setOnClickListener(new View.OnClickListener(){
            @Override
            //when clear is clicked the entity is changed
            public void onClick(View v){
                changeEntity();
            }
        });
        guessButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //when the guess is clicked the game is played
            public void onClick(View v){
                playGame();
            }
        });
    }

    //changeEntity method
    public void changeEntity(){
        //resetting the input and continuing the game
        userIn.setText("");
        ContinueGame();
    }

    //ImageSetter method
    public void ImageSetter(){
        //checks the entity name and sets the image based on which entity is currently set
        if(entities[entityid].getName().equals("Justin Trudeau")){
            entityImage.setImageResource(R.drawable.justint);
        }else if(entities[entityid].getName().equals("Celine Dion")){
            entityImage.setImageResource(R.drawable.celidion);
        }else if(entities[entityid].getName().equals("United States")){
            entityImage.setImageResource(R.drawable.usaflag);
        }else{
            entityImage.setImageResource(R.drawable.creator);
        }
    }

    //welcomeToGame method
    public void welcomeToGame (Entity entity){
        //takes the current entity and sends a welcome message based on this
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster Game v3");
        welcomealert.setMessage(entity.welcomeMessage());
        welcomealert.setCancelable(false);

        //this message can be cleared by pressing the START GAME button created here
        welcomealert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting ... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    //GUESSMASTER METHODS

    //GuessMaster constructor
    public GuessMaster() {
        numOfEntities = 0;
        entities = new Entity[10];
    }

    //addEntity method
    public void addEntity(Entity entity) {
        //adds the current entity to the array
        this.entities[numOfEntities++] = entity;
    }

    //playGame method (id parameter)
    public void playGame(int entityId) {
        //calls the playGame method with an entity based on an id
        playGame(entities[entityId]);
    }

    //playgame method (entity parameter)
    public void playGame(Entity entity) {
        //gets user input from editText box
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");

        //sets date based on user input
            Date date = new Date(answer);

            //based on user input a message is displayed
            if (date.precedes(entity.getBorn())) {
                //alert displayed that the date is too early and a later date is required
                AlertDialog.Builder earlyalert = new AlertDialog.Builder(GuessMaster.this);
                earlyalert.setTitle("Incorrect");
                earlyalert.setMessage("Try a later date");
                earlyalert.setCancelable(false);
                //Ok button to dismiss the above message
                earlyalert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getBaseContext(), "Continuing ... ", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = earlyalert.create();
                dialog.show();
            } else if (entity.getBorn().precedes(date)) {
                //alert displayed that the date is too late and an earlier date is required
                AlertDialog.Builder latealert = new AlertDialog.Builder(GuessMaster.this);
                latealert.setTitle("Incorrect");
                latealert.setMessage("Try an earlier date");
                latealert.setCancelable(false);
                //Ok button to dismiss the above message
                latealert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getBaseContext(), "Continuing ... ", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = latealert.create();
                dialog.show();
            } else {
                //upon correct guess
                //tickets are calculated based on difficulty and added to total
                numOfTickets = entity.getAwardedTicketNumber();
                totaltik += numOfTickets;
                //alert displayed to show user they won, printing the corresponding closingMessage
                AlertDialog.Builder winalert = new AlertDialog.Builder(GuessMaster.this);
                winalert.setTitle("You won");
                winalert.setMessage("BINGO! " + entity.closingMessage());
                winalert.setCancelable(false);
                //Continue button to dismiss the above message
                winalert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //displaying the tickets won this round
                        Toast.makeText(getBaseContext(), "Awarded Tickets: " + entity.getAwardedTicketNumber(), Toast.LENGTH_SHORT).show();
                        ticketsum.setText("Total Tickets: " + totaltik);
                        ContinueGame();
                    }
                });
                AlertDialog dialog = winalert.create();
                dialog.show();
        }
    }

    //ContinueGame method
    public void ContinueGame(){
        //gets random entity id, sets the image, text, and clears user input
        entityid = genRandomEntityId();
        ImageSetter();
        entityName.setText(entities[entityid].getName());
        userIn.getText().clear();
    }

    //playGame method (no parameter)
    public void playGame() {
        //calls playGame with current entityid
        playGame(entityid);
    }

    //genRandomEntityId method
    public int genRandomEntityId() {
        //generates a new random number corresponding to the number of entities that exist
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }
}