public class InteractiveItem {

  // variables-----------------------------------------------------------------------------------------------------------

  private String name;
  private String description;
  private String matchingItem;

  // constructors--------------------------------------------------------------------------------------------------------

  public InteractiveItem() {
    this.name = "unknown";
    this.description = "unknown";
    this.matchingItem = "unknown";
  }

  public InteractiveItem(String name, String description, String matchingItem) {
    this.name = name;
    this.description = description;
    this.matchingItem = matchingItem;
  }
  // getters and
  // setters-------------------------------------------------------------------------------------------------

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public void setMatchingItem(String matchingItem) {
    this.matchingItem = matchingItem;
  }

  public String getMatchingItem() {
    return this.matchingItem;
  }

  // methods

  public boolean checkIfIsMatch(String satchelItem) {
    return this.getMatchingItem().compareTo(satchelItem) == 0;
  }
}