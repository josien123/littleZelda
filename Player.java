import java.util.ArrayList;

public class Player extends Character{

//variables-------------------------------------------------------------------------------------------------------------
  
  private int health;
  private ArrayList<String> satchelItems;

//constructors----------------------------------------------------------------------------------------------------------
  
  public Player() {
    this.setName("unknown");
    this.health = 100;
    this.satchelItems = new ArrayList<String>();
  }

  public Player(String name) {
    this();
    this.setName("unknown");
  }

  //getters and setters-------------------------------------------------------------------------------------------------
  
  public void setHealth(int health){
    this.health = health;
  }

  public void getInventory() {
    System.out.println("Inventory: " + satchelItems);
  }

  public int getHealth() {
    return this.health;
  }

  //methods-------------------------------------------------------------------------------------------------------------
  
  public boolean checkIfInSatchel(String item){
    return satchelItems.contains(item);
  }
  public boolean removeFromSatchel(String item) {
    return satchelItems.remove(item);
  }
  public String checkStatus() {
    return "<3 \nYour health is: " + health + "/100.\n <3";
  }
    
  public void addHealth(int health){
    this.health += health;
    if(this.health > 100) {
      this.health = 100;
      System.out.println("\nWhat you ate increased your health to the maximum!");
    }else {
      System.out.println("\nThe item you used increased your health by " + health + ".");
    }
  }
  public void subtractHealth(int health){
    this.health -= health;
    if(this.health <= 0) {
      System.out.println("check. run 'you died' message");
    }
  }
  public void addToSatchel(String item) {
    satchelItems.add(item);
  }
}