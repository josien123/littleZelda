import java.util.ArrayList;

public class NonPlayableCharacter extends Character{

  //variables-----------------------------------------------------------------------------------------------------------
  
  private String mood;

  //constructors--------------------------------------------------------------------------------------------------------

  public NonPlayableCharacter() {
    this.setName("");
    this.mood = "Helpful";
  }
  public NonPlayableCharacter(String mood) {
    this();
    this.mood = mood;
  }

  //getters and setters-------------------------------------------------------------------------------------------------
  
  public void setMood(String mood){
    this.mood = mood;
  }
  public String getMood() {
    return this.mood;
  } 
}