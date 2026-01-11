Each class: 
- LittleZeldaGame: Main controller. Creates the map, runs the input loop and enforces rules.
- Location: Designs map. Has name, description, items, npc, interactiveItem and toString.
- Character: Designs movable character. Has name, xLoc, yLoc and movement methods.
- Player: Subclass of Character, designs playable character. Has health, satchelItems, add/remove/use for inventory and health effects methods.
- NonPlayableCharacter: Subclass of Character, designs non-playable character. Has mood.
- InteractiveItem: designs special, non-pick-upable items that Player can interact with. Has name, description and matchingItem.

Player Interactions: 
- move: takes input, calls Player move method, checks for cliffs, describes the location.
- pick up: asks item name, removes item from location, adds item to satchel.
- use: asks name, checks if in satchel, -----.
- NPC encounter: after moving, checks for npc in the location, applies behavior(dialogue or damage).