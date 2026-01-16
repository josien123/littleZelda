1. inheritance
	1. character is the base for player and nonPlayableCharacter
	2. less code repeats
2. navigation
	1. 4x4 array
	2. integer coordinates 0-3
3. tracking
	1. booleans (hasNPC, hasItem, hasInteractiveItem) prevents conflicts
	2. gaining and losing health
	3. array list for items, adding and removing
4. switch statement
	1. loop for commands
	2. NPC behavior determined by matching names
	3. item use logic by item type
5. randoming
	1. bokoblin gets random coordinates
	2. checks for occupied locations to not spawn in