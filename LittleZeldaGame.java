import java.util.Scanner;
import java.util.Random;

public class LittleZeldaGame {

  //Constants (hardcoded) ------------------------------------------------------------------------------------------------------
  
  private final static int X_MAP_SIZE = 4;
  private final static int Y_MAP_SIZE = 4;
  private final static int PLAYER_STARTING_X_LOC = 2;
  private final static int PLAYER_STARTING_Y_LOC = 2;

  //variables------------------------------------------------------------------------------------------------------
  
  private Location[][] map = new Location[X_MAP_SIZE][Y_MAP_SIZE];
  private int playerXloc = PLAYER_STARTING_X_LOC;
  private int playerYLoc = PLAYER_STARTING_Y_LOC;
  private int npcXloc = 3;
  private int npcYLoc = 3;
  private String direction;
  private boolean isPlayingGame = true;
  
  Scanner input = new Scanner(System.in);
  private Player mainPlayer = new Player();
  private NonPlayableCharacter hestu = new NonPlayableCharacter();
  private NonPlayableCharacter bokoblin = new NonPlayableCharacter();
  private NonPlayableCharacter korok = new NonPlayableCharacter();
  private InteractiveItem chest = new InteractiveItem();
      
  
  //----------------START METHOD-----------------
  public void start() {
    createMap();
    createCharacters();
    runIntro();
    gameLogic();
  }

  
  //----------------CREATES 4 BY 4 MAP-----------------
      
  private void createMap() {


      // makes all locations empty feilds
      
      for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                map[row][col] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");
            }
        }




















    
    //states name of location and what is there-WILL RANDOMIZE?
      //maracas
        map[0][0] = new Location("A chest!", "The maracas msut be inside, but you'll need a key ot unlock it!");
    
      //nothing
        map[0][1] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //korok
        map[0][2] = new Location("a korok", "It's a small leaf animal thing and it wants to talk to you.");

      //nothing
        map[0][3] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[1][0] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //bokoblin site
        map[1][1] = new Location("Bokoblin site", "Oh no! A bokoblin has set up camp here! He attack you and your health decreases.");
      
      //nothing
        map[1][2] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[1][3] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[2][0] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[2][1] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");
        map[2][1].addItem("korok seed");
      //start
        map[2][2] = new Location("Starting point", "This is where you started. Just boring crops and stuff, nothing of interest.");

      //hestu
        map[2][3] = new Location("Hestu", "You see Hestu. He seems panicked!");

      //korok seed
        map[3][0] = new Location("korok seed", "Just boring crops and stuff, nothing of inter- whats that? A small seed is lying on the ground!");

      //nothing
        map[3][1] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[3][2] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");

      //nothing
        map[3][3] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");
    }

  //----------------CREATES ALL CHARACTERS AND ITEMS-----------------

  private void createCharacters() {

    //hard coded npcs
    hestu.setName("Hestu");
    map[2][3].setNonPlayableCharacter(hestu);
    map[2][3].setHasNPC(true);

        
    korok.setName("Korok");
    map[3][3].setNonPlayableCharacter(korok);
    map[3][3].setHasNPC(true);

    //hard code main player
    mainPlayer.addToSatchel("bread");
    mainPlayer.addToSatchel("key"); // will have the korok have the key later-just for test now.
    mainPlayer.setHealth(96);

    //random location npcs
    bokoblin.setName("Bokoblin");
    int bokoblinX = mapRandomInt(0, X_MAP_SIZE-1);
    int bokoblinY = mapRandomInt(0, Y_MAP_SIZE-1);
    while (map[bokoblinX][bokoblinY].getHasNPC() || (bokoblinX == PLAYER_STARTING_X_LOC && bokoblinY == PLAYER_STARTING_Y_LOC)) {
      System.out.println("boko x = " + bokoblinX + " boko y = " + bokoblinY);
      bokoblinX = mapRandomInt(0, X_MAP_SIZE-1);
      bokoblinY = mapRandomInt(0, Y_MAP_SIZE-1);
      

    }
    map[bokoblinX][bokoblinY].setNonPlayableCharacter(bokoblin);
    map[bokoblinX][bokoblinY].setHasNPC(true);
    System.out.println("boko x = " + bokoblinX + " boko y = " + bokoblinY);

    
    chest.setName("a golden chest");
    chest.setDescription("blah blah blah chest description");
    chest.setMatchingItem("key");
    
    map[2][3].setInteractiveItem(chest);
    
  }

  private void gameLogic(){
      
      
      /* 
      
      ----------------------------------------------------- MAIN GAME LOOP --------------------------------------------------------
      
      */

      while (isPlayingGame) {
        System.out.println("\nWhat would you like to do? Hint: type [help] for commands\n");
        direction = input.nextLine();
        switch(direction) {
          
          //move north
          case "move north":
            mainPlayer.moveNorth();
            if(checkForCliffs()) {
              mainPlayer.moveSouth();
              System.out.println("Oh no, there's a cliff there! You can't move that way.");
            }
            break;
          
          //move east
          case "move east":
            mainPlayer.moveEast();
            if(checkForCliffs()) {
              mainPlayer.moveWest();
              System.out.println("Oh no, there's a cliff there! You can't move that way.");
            }
            break;
          
          //move south
          case "move south":
            mainPlayer.moveSouth();
            if(checkForCliffs()) {
              mainPlayer.moveNorth();
              System.out.println("Oh no, there's a cliff there! You can't move that way.");
            }
            break;
          
          //move west
          case "move west":
            mainPlayer.moveWest();
            if(checkForCliffs()) {
              mainPlayer.moveEast();
              System.out.println("Oh no, there's a cliff there! You can't move that way.");
            }
            break;
          
          /*
          CHECK STATUS: prints health
          */
          case "check status":
            System.out.println(mainPlayer.checkStatus());
            break;
          
          /*
          INVENTORY: prints inventory arraylist
          */
          case "inventory":
            mainPlayer.getInventory();
            break;
          
          /*
          USE: asks what would you like to use, checks if in satchel, uses
          */
          case "use":
            System.out.println("what would you like to use?: ");
            String itemToUse = input.nextLine();
            if (mainPlayer.checkIfInSatchel(itemToUse)) {
              switch(itemToUse) {
                case "bread":
                  mainPlayer.removeFromSatchel(itemToUse);
                  mainPlayer.addHealth(10);
                  break;
                case "key":
                  mainPlayer.removeFromSatchel(itemToUse);
                  //find chest?
                  break;
                case "korok seed":
                  mainPlayer.removeFromSatchel(itemToUse);
                  //korok talk
                default:
                  System.out.println("sorry, doesn't work");
                }
              System.out.println("used " + itemToUse);
            }
            else{
              System.out.println("Sorry you either don't have that item or can't use it");
            }
            break;
          
          /*--------------------------------
            LOOK: prints what is in the area
            */
          case "look":
            System.out.println(map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].toString());
            break;
          
          /*-------------------------------------------------------------------------------------------
            PICK UP: asks what would you like to pick up, removes item from map, adds item to inventory
            */
          case "pick up":
            System.out.println("what would you like to pick up?: ");
            String itemPickedUp = input.nextLine();
            if (map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].removeItem(itemPickedUp)) {
              mainPlayer.addToSatchel(itemPickedUp);
              System.out.println("You've picked up the " + itemPickedUp + ". It is in your satchel.");
            }
            else{
              System.out.println("Sorry, that doesn't work try again.");
            }
            break;
          
          /*
          HELP: prints all the commands
          */
          case "help":
            help();
            break;
          
          /*
          QUIT: ends game loop
          */
          case "quit":
            isPlayingGame = false;
            System.out.println("Wow you want to quit? Loser.");
            break;
          
          /*
          goes back to start
          */
          default:
            System.out.println("Uh you can't do that, try something else.");
          }
          
        
        //test - take out later  
        System.out.println("Your coordinates: " + mainPlayer.getXLoc() + ", " + mainPlayer.getYLoc());
        
        /* 
        
        ---------------------------------------------- NPC ECOUNTER -------------------------------------------------

          */
  
        if(map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getHasNPC() == true) {
          NonPlayableCharacter npc = map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getNonPlayableCharacter();
          String nameOfNPC = npc.getName();
          switch(nameOfNPC) {
              
            case "Hestu":
              System.out.println("\nHey its hestu! Have you seen my maracas?");
              break;
              
            case "Korok": 
              System.out.println("\nYa-ha-ha! I'm a korok! Give me a korok seed for information.");
              break;
              
            case "Bokoblin":
              mainPlayer.subtractHealth(50);
              System.out.println("\nOH NO! Crap, you just stumbled upon a bokoblin! Your health decreased by 50.");
              break;
              
            default: 
              //logic?
              //no npc here? unknown npc?
          }
        }
          if(mainPlayer.getHealth() <= 0) {
            isPlayingGame = false;
            System.out.println("\nOop you died. Game over.");
          }
        }
  }
  /*
  
  --------------------------------------------------- END OF GAME LOOP -------------------------------------------------------------------

  */
  
  // message that runs at the start of the game
   private void runIntro() {
      String message = "Welcome to little zelda. You are at (2, 2)," + "\ntype name: ";
      System.out.println(message);
      String name = input.nextLine();
      System.out.println("hi " + name + ", welcome!" );
    }

  //command that shows all the commands to the user
   private void help() {
      String message = "\n------------------------------------------------------\ncommamnds :\n\nmove north / move east / move south / move west\ncheck status / inventory / look / use / pick up / help\n\n------------------------------------------------------";
      System.out.println(message);
    }

  //checks if player is going off map.
  private boolean checkForCliffs() {
    if((mainPlayer.getXLoc() > X_MAP_SIZE - 1) ||(mainPlayer.getXLoc() < 0) ||(mainPlayer.getYLoc() > Y_MAP_SIZE - 1) ||(mainPlayer.getYLoc() < 0)) {
      return true;
    }else {
      return false;
    }
  }
  


  public int mapRandomInt(int min, int max) {
        Random r = new Random();
        //System.out.println("Generated numbers are within "+ min +" to "+ max);
        int randomNumber = r.nextInt(max - min + 1) + min;
        //System.out.println(randomNumber);
        return randomNumber;
}



}

