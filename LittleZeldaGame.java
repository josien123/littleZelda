import java.util.Scanner;
import java.util.Random;

public class LittleZeldaGame {

  // Constants (hardcoded)
  // ------------------------------------------------------------------------------------------------------

  private final static int X_MAP_SIZE = 4;
  private final static int Y_MAP_SIZE = 4;
  private final static int PLAYER_STARTING_X_LOC = 2;
  private final static int PLAYER_STARTING_Y_LOC = 2;

  // variables------------------------------------------------------------------------------------------------------

  private Location[][] map = new Location[X_MAP_SIZE][Y_MAP_SIZE];
  private String direction;
  private boolean isPlayingGame = true;

  Scanner input = new Scanner(System.in);
  private Player mainPlayer = new Player();
  private NonPlayableCharacter hestu = new NonPlayableCharacter();
  private NonPlayableCharacter bokoblin = new NonPlayableCharacter();
  private NonPlayableCharacter korok = new NonPlayableCharacter("korok seed", "Helpful");
  private InteractiveItem chest = new InteractiveItem();
  // ----------------START METHOD-----------------
  public void start() {

    createBlankMap();
    fillOutMap();
    runIntro();
    gameLogic();
  }

  // ----------------CREATES 4 BY 4 MAP-----------------

  private void createBlankMap() {

    // makes all locations empty feilds

    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[0].length; col++) {
        map[row][col] = new Location("an empty field", "Just boring crops and stuff, nothing of interest.");
      }
    }
  }

  // ----------------CREATES ALL CHARACTERS AND ITEMS-----------------

  private void fillOutMap() {

    mainPlayer.setXLoc(PLAYER_STARTING_X_LOC);
    mainPlayer.setYLoc(PLAYER_STARTING_Y_LOC);

    // hard coded npcs
    // hestu

    hestu.setName("Hestu");
    hestu.setXLoc(2);
    hestu.setYLoc(3);
    map[2][3] = new Location("Hestu's hut", "You see Hestu. He seems panicked!");
    map[2][3].setNonPlayableCharacter(hestu);
    map[2][3].setHasNPC(true);

    // korok-------------------------------------------------------
    korok.setName("Korok");
    korok.setXLoc(0);
    korok.setYLoc(2);
    map[0][2] = new Location("a korok", "It's a small leaf animal thing and it wants to talk to you.");
    map[0][2].setNonPlayableCharacter(korok);
    map[0][2].setHasNPC(true);
    map[2][0].addItem("korok seed");

    // hard code main player---------------------------------------
    mainPlayer.addToSatchel("bread");
    mainPlayer.setHealth(96);

    // hard code interactive items---------------------------------
    chest.setName("a golden chest");
    chest.setDescription("blah blah blah chest description");
    chest.setMatchingItem("key");
    map[0][0].setInteractiveItem(chest);
    map[3][1].addItem("key");

    // random location npcs----------------------------------------
    bokoblin.setName("Bokoblin");
    int bokoblinX = mapRandomInt(0, X_MAP_SIZE - 1);
    int bokoblinY = mapRandomInt(0, Y_MAP_SIZE - 1);
    // ensure bokoblin doesn't spawn on top of another npc or starting location
    while (map[bokoblinX][bokoblinY].getHasNPC() || map[bokoblinX][bokoblinY].getHasInteractiveItem()
        || (bokoblinX == PLAYER_STARTING_X_LOC && bokoblinY == PLAYER_STARTING_Y_LOC)) {
      bokoblinX = mapRandomInt(0, X_MAP_SIZE - 1);
      bokoblinY = mapRandomInt(0, Y_MAP_SIZE - 1);
    }
    map[bokoblinX][bokoblinY] = new Location("Bokoblin site",
        "Oh no! A bokoblin has set up camp here! He attacks you and your health decreases.");
    map[bokoblinX][bokoblinY].setNonPlayableCharacter(bokoblin);
    map[bokoblinX][bokoblinY].setHasNPC(true);
  }

  private void gameLogic() {

    /*
     * 
     * ----------------------------------------------------- MAIN GAME LOOP--------------------------------------------------------
     * 
     */

    while (isPlayingGame) {
      System.out.println("Your coordinates: " + mainPlayer.getXLoc() + ", " + mainPlayer.getYLoc());
      System.out.println("Your health: " + mainPlayer.getHealth() + "/100");
      System.out.print("\nWhat would you like to do? Hint: type [help] for commands\n>");
      direction = input.nextLine();
      switch (direction) {

        // move north
        case "move north":
          mainPlayer.moveNorth();
          if (checkForCliffs()) {
            mainPlayer.moveSouth();
            System.out.println("Oh no, there's a cliff there! You can't move that way.");
          }
          break;

        // move east
        case "move east":
          mainPlayer.moveEast();
          if (checkForCliffs()) {
            mainPlayer.moveWest();
            System.out.println("Oh no, there's a cliff there! You can't move that way.");
          }
          break;

        // move south
        case "move south":
          mainPlayer.moveSouth();
          if (checkForCliffs()) {
            mainPlayer.moveNorth();
            System.out.println("Oh no, there's a cliff there! You can't move that way.");
          }
          break;

        // move west
        case "move west":
          mainPlayer.moveWest();
          if (checkForCliffs()) {
            mainPlayer.moveEast();
            System.out.println("Oh no, there's a cliff there! You can't move that way.");
          }
          break;

        /*
         * CHECK STATUS: prints health
         */
        case "check status":
          System.out.println(mainPlayer.checkStatus());
          break;

        /*
         * INVENTORY: prints inventory arraylist
         */
        case "inventory":
          mainPlayer.getInventory();
          break;

        /*
         * USE: asks what would you like to use, checks if in satchel, uses
         */
        case "use":
          System.out.print("\nwhat would you like to use?: \n>");
          String itemToUse = input.nextLine();
          if (mainPlayer.checkIfInSatchel(itemToUse)) {
            switch (itemToUse) {
              case "bread":
                mainPlayer.removeFromSatchel(itemToUse);
                mainPlayer.addHealth(10);
                break;
              case "key":
                mainPlayer.removeFromSatchel(itemToUse);
                if (chest.checkIfIsMatch("key") && map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getHasInteractiveItem()) {  
                  System.out.println("You used the key to open the chest! Inside you found the maracas!");
                  mainPlayer.addToSatchel("maracas");
                } else {
                  System.out.println("The key doesn't seem to work here.");
                }
                // use with chest
                break;
              case "korok seed":
                mainPlayer.removeFromSatchel(itemToUse);
                if (korok.checkIfIsMatch("korok seed") && map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getHasInteractiveItem()) {
                  System.out.println("You gave the korok a korok seed! He seems happy to help you.");
                  System.out.println(
                      "'Thanks for the seed! here's a hint to finding the maracas: they are in a chest around here somewhere. Find a key for you to open it!'");
                } else {
                  System.out.println("The seed doesn't seem to work here.");
                }
                // korok talk
                break;
              case "maracas":
                System.out.println("You play the maracas. Hestu appears and thanks you for finding them!");
                System.out.println("'Thank you so much! Now I can play my maracas again!'");
                System.out.println(
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n             YOU WIN! YOU HELPED HESTU FIND HIS MARACAS!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                isPlayingGame = false;
                mainPlayer.removeFromSatchel(itemToUse);
                // hestu reward
                break;
              default:
                System.out.println("sorry, doesn't work");
            }
            System.out.println("used " + itemToUse);
          } else {
            System.out.println("Sorry you either don't have that item or can't use it");
          }
          break;

        /*--------------------------------
          LOOK: prints what is in the area
          */
        case "look":
          System.out.println(map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].toString());
          seeSurroundings();
          break;

        /*-------------------------------------------------------------------------------------------
          PICK UP: asks what would you like to pick up, removes item from map, adds item to inventory
          */
        case "pick up":
          System.out.print("\nwhat would you like to pick up?: \n>");
          String itemPickedUp = input.nextLine();
          if (map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].removeItem(itemPickedUp)) {
            mainPlayer.addToSatchel(itemPickedUp);
            System.out.println("You've picked up the " + itemPickedUp + ". It is in your satchel.");
          } else {
            System.out.println("Sorry, that doesn't work try again.");
          }
          break;

        /*
         * HELP: prints all the commands
         */
        case "help":
          help();
          break;

        /*
         * QUIT: ends game loop
         */
        case "quit":
          isPlayingGame = false;
          System.out.println("Wow you want to quit? Loser.");
          break;

        /*
         * goes back to start
         */
        default:
          System.out.println("Uh you can't do that, try something else.");
      }

      // test - take out later

      // System.out.println("chest coordinates: " + chest.getXLoc() + ", " +
      // mainPlayer.getYLoc());

      /*
       * 
       * ---------------------------------------------- NPC ECOUNTER
       * -------------------------------------------------
       * 
       */

      if (map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getHasNPC() == true) {
        NonPlayableCharacter npc = map[mainPlayer.getXLoc()][mainPlayer.getYLoc()].getNonPlayableCharacter();
        String nameOfNPC = npc.getName();
        switch (nameOfNPC) {

          case "Hestu":
            System.out.println(
                "\n'Hey its hestu! Have you seen my maracas? I think they were stolen! Maybe you can find someone who knows where they are.'");
            break;

          case "Korok":
            System.out.println("\n'Ya-ha-ha! I'm a korok! Give me a korok seed for information.'");
            break;

          case "Bokoblin":
            mainPlayer.subtractHealth(20);
            System.out.println(
                "\nOH NO! Crap, you just stumbled upon a bokoblin and he lights you on fire! Your health decreased by 20.");
            break;

          default:
            // logic?
            // no npc here? unknown npc?
        }
      }
      if (mainPlayer.getHealth() <= 0) {
        isPlayingGame = false;
        System.out.println("\nOop you died. Game over.");
      }
      System.out.println();
    }
  }
  /*
   * 
   * --------------------------------------------------- END OF GAME LOOP
   * -------------------------------------------------------------------
   * 
   */

  // message that runs at the start of the game
  private void runIntro() {
    String message = "\n\n------------------------------------------------------\nWelcome to little zelda. You are at (2, 2)\ntype name: \n>";
    System.out.print(message);
    String name = input.nextLine();
    System.out.println("\nhi " + name + "!");
  }

  // command that shows all the commands to the user
  private void help() {
    String message = "\n------------------------------------------------------\ncommamnds :\n\nmove north / move east / move south / move west\ncheck status / inventory / look / use / pick up / help\n\n------------------------------------------------------";
    System.out.println(message);
  }

  // checks if player is going off map.
  private boolean checkForCliffs() {
    if ((mainPlayer.getXLoc() > X_MAP_SIZE - 1) || (mainPlayer.getXLoc() < 0) || (mainPlayer.getYLoc() > Y_MAP_SIZE - 1)
        || (mainPlayer.getYLoc() < 0)) {
      return true;
    } else {
      return false;
    }
  }

  public int mapRandomInt(int min, int max) {
    Random r = new Random();
    // System.out.println("Generated numbers are within "+ min +" to "+ max);
    int randomNumber = r.nextInt(max - min + 1) + min;
    // System.out.println(randomNumber);
    return randomNumber;
  }

  public void seeSurroundings() {
    String seeNorth = (mainPlayer.getYLoc() - 1) < 0 ? "a cliff" : map[mainPlayer.getXLoc()][mainPlayer.getYLoc() - 1].getName();
    String seeEast = (mainPlayer.getXLoc() + 1) > (X_MAP_SIZE - 1) ? "a cliff" : map[mainPlayer.getXLoc() + 1][mainPlayer.getYLoc()].getName();
    String seeSouth = (mainPlayer.getYLoc() + 1) > (Y_MAP_SIZE - 1) ? "a cliff" : map[mainPlayer.getXLoc()][mainPlayer.getYLoc() + 1].getName();
    String seeWest = (mainPlayer.getXLoc() - 1) < 0 ? "a cliff" : map[mainPlayer.getXLoc() - 1][mainPlayer.getYLoc()].getName();
    
    System.out.println("To the north you see " + seeNorth + ", to the east you see " + seeEast + ", to the south you see " + seeSouth
        + ", and to the west you see " + seeWest + ".");
    
  }

}
