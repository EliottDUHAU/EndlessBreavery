# Design pattern diagrams

![GameLoop pattern](/documents/diagrams/Gameloop_Observer.png)
The Game class will be responsible for the game loop. It will notify the observer classes (Player, Enemies, Cards) of the game state. The observer classes will then update their state accordingly on each frame using an observer pattern.

![Sprite patterns](/documents/diagrams/Sprites.png)
The Sprite class will be responsible for rendering the game objects and animations. The game objects will be composed of a Sprite object. The player sprite will be using decorator patterns to handle different attack animations.

![Entities patterns](/documents/diagrams/Entities.png)
The Entities class represents dynamic game objects like player or enemies. The enemies will be created using a factory pattern in a spawner class.

![Hitbox and Collison patterns](/documents/diagrams/Hitboxes.png)
The hitbox and wall classes implement collision detection using the java shapes.

![Controller patterns](/documents/diagrams/Controllers.png)
For ease of access to some instances we set up a few controller classes. Most of them are for objects that will only be instantiated once, those controllers will implement the singleton pattern. 
