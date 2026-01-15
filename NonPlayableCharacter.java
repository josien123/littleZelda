public class NonPlayableCharacter extends Character {

  // variables-----------------------------------------------------------------------------------------------------------

  private String mood;
  private String matchingItem;

  // constructors--------------------------------------------------------------------------------------------------------

  public NonPlayableCharacter() {
    this.setMatchingItem("unknown");
    this.setName("");
    this.mood = "Helpful";
  }

  public NonPlayableCharacter(String matchingItem, String mood) {
    this();
    this.matchingItem = matchingItem;
    this.mood = mood;
  }

  // getters and
  // setters-------------------------------------------------------------------------------------------------

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getMood() {
    return this.mood;
  }

  public void setMatchingItem(String matchingItem) {
    this.matchingItem = matchingItem;
  }

  public String getMatchingItem() {
    return this.matchingItem;
  }
  // methods-------------------------------------------------------------------------------------------------------------

  public boolean checkIfIsMatch(String satchelItem) {
    return this.getMatchingItem().compareTo(satchelItem) == 0;
  }
}