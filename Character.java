import java.util.ArrayList;

public class Character {

  // variables-----------------------------------------------------------------------------------------------------------

  private String name;
  private int xLoc;
  private int yLoc;

  // constructors----------------------------------------------------------------------------------------------------------

  public Character() {
    this.name = "Unknown";
    this.xLoc = 2;
    this.yLoc = 2;
  }

  public Character(String name) {
    this.name = name;
  }

  public Character(String name, int xLoc, int yLoc) {
    this.name = name;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  // getters and
  // setters--------------------------------------------------------------------------------------------------

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setXLoc(int xLoc) {
    this.xLoc = xLoc;
  }

  public int getXLoc() {
    return this.xLoc;
  }

  public void setYLoc(int yLoc) {
    this.yLoc = yLoc;
  }

  public int getYLoc() {
    return this.yLoc;
  }

  // methods--------------------------------------------------------------------------------------------------------------

  public void moveNorth() {
    yLoc--;
  }

  public void moveSouth() {
    yLoc++;
  }

  public void moveEast() {
    xLoc++;
  }

  public void moveWest() {
    xLoc--;
  }
}