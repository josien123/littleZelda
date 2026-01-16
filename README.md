# LittleZelda

LittleZelda is a text base game with the goal of finding Hestu's maracas. 

The player can move north, west south or east, can check status, inventory, look, pick up, use, ask for help and quit. The map is 4 by 4. There are multiple NPC's, items and interactive items(items you cant pick up or use, but)

To start type 'java LittleZeldagame'. First the player moves south to find hestu, who says to find his maracas. Then the player goes west to find a korok who will give information if given a korok seed. The player goes North to find the seed and brings it back to the korok. The korok says the key is around here. The player goes North East to find and unlock the chest and uses the maracas to win.

diagrams/classDiagramv2.pdf

## Local setup

Build and run with `javac`/`java` or your IDE. Example:

```bash
# compile
javac *.java

# run
java LittleZeldaGame
```

## GitHub

This repository was prepared for publishing. If you have the GitHub CLI (`gh`) installed and authenticated, the setup script will attempt to create a remote and push the initial commit automatically. If not, follow these commands to create and push a remote manually:

```bash
cd /path/to/littleZelda
git remote add origin https://github.com/<your-username>/littleZelda.git
git push -u origin main
```
