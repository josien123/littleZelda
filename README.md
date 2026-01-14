# LittleZelda

Small Java text-adventure project for CSA jan 16. 
LittleZelda is a text base game with the goal of finding Hestu's maracas. to start type 'java LittleZeldagame'. First the player moves south to find hestu, who tells you to find his maracas. Then the player goes west to find a korok who will give information if given a korok seed. Player goes North to find the seed and brings it back to the korok. The korok gives a key and says it unlocks a chest. player goes North East to find and unlock the chest. use the maracas to win.

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
