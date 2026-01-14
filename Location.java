import java.util.ArrayList;

public class Location {

  // Private instance variables
  // -------------------------------------------------------------------------

  private String name;
  private String description;
  private ArrayList<String> items; // Using String for simplicity, or change to Item class
  private boolean hasNPC;
  private boolean hasInteractiveItem;
  private NonPlayableCharacter npc;
  private InteractiveItem interactiveItem;

  // Constructor-----------------------------------------------------------------------------------------------
  public Location(String name, String description) {
    this.name = name;
    this.description = description;
    this.items = new ArrayList<>();
    this.npc = new NonPlayableCharacter();
    this.interactiveItem = new InteractiveItem();
  }

  // Getters and
  // Setters------------------------------------------------------------------------------------
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NonPlayableCharacter getNonPlayableCharacter() {
    return npc;
  }

  public void setNonPlayableCharacter(NonPlayableCharacter npc) {
    this.npc = npc;
  }

  public InteractiveItem getInteractiveItem() {
    return interactiveItem;
  }

  public void setInteractiveItem(InteractiveItem interactiveItem) {
    this.interactiveItem = interactiveItem;
  }

  public boolean getHasNPC() {
    return hasNPC;
  }

  public void setHasNPC(boolean hasNPC) {
    this.hasNPC = hasNPC;
  }

  public boolean getHasInteractiveItem() {
    return hasInteractiveItem;
  }

  public void setHasInteractiveItem(boolean hasInteractiveItem) {
    this.hasInteractiveItem = hasInteractiveItem;
  }

  // Inventory management
  // methods--------------------------------------------------------------------------------------
  public boolean checkIfItemExists(String item) {
    return items.contains(item);
  }

  public void addItem(String item) {
    items.add(item);
  }

  public boolean removeItem(String item) {
    return items.remove(item);
  }

  // get string
  // methods----------------------------------------------------------------------------------------------
  public String getItemsString() {
    if (items.isEmpty()) {
      return "no items";
    }

    String locationItem = items.size() == 1 ? "item " : "items ";
    for (String i : items) {
      locationItem = locationItem + (i + " ");
    }
    return "the following " + locationItem.trim();

    // return "items: " + items.toString();
  }

  public String getNonPlayableCharacterString() {
    // System.out.println.npc.getName();
    if (this.npc.getName().compareTo("") == 0) {
      return ", and no one is around.";
    }
    return ", you see " + npc.getName();// add descrpition?
  }

  public String getInteractiveItemString() {
    if (this.interactiveItem.getName().compareTo("") == 0) {
      return ", nothing you can interact with";
    }
    return ", and you see " + interactiveItem.getName();// add descrpition?
  }

  // toString
  // method----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "\n------------ you look around ------------\nYou are at " + this.name + ",\n" + this.description + "\nYou see " + getItemsString()
        + getInteractiveItemString() + getNonPlayableCharacterString();// + "in the north you see" + Location[Player.getXLoc()][Player.getYLoc() - 1].getName() + "to the east you see" + Location[Player.getXLoc() - 1][Player.getYLoc()].getName() + "to the west you see" + Location[Player.getXLoc() + 1][Player.getYloc()].getName() + "to the south there is" + Location[Player.getXLoc()][Player.getYLoc() + ].getName();


  }
}



// "in the north you see" + map[mainPlayer.getXloc][mainPlayer.getYloc - 1].getname + "to the east you see" + map[mainPlayer.getXloc -1][mainPlayer.getYloc].getname + "to the west you see" + map[mainPlayer.getXloc + 1][mainPlayer.getYloc].getname + "to the south there is" + map[mainPlayer.getXloc][mainPlayer.getYloc + 1].getname;
//"in the north you see" + Location[Player.getXLoc()][Player.getYLoc() - 1].getName() + "to the east you see" + Location[Player.getXLoc() - 1][Player.getYLoc()].getName() + "to the west you see" + Location[Player.getXLoc() + 1][Player.getYloc()].getName() + "to the south there is" + Location[Player.getXLoc()][Player.getYLoc() + ].getName();
