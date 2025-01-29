//Name: Cameron Bartlett
//Date: 01/21/2025
//Lab 2 - Objects - Snake Game



//Description: This is my portion of the group work for Lab 2. I made a linked list
with several functions for a Snake arcade game, and included is my test class where I've
made sure every function is working correctly. The Snake class contains an inner class to 
define each segment of the Snake, a constructor for the Snake, a reset to default
position for if the player wants to play again after a game over, an add head function to
help with movement, a grow Snake function for when the Snake runs over a piece of fruit,
a remove tail function which is used in tandem with add head to simulate a more realistic 
movement of the snake (This is means the snake moves by adding a new head node and deleting
the tail node left behind. If the segments updated position instead then the Snake would look
like it was teleporting instead of slithering), some getter methods for debugging and for
collision, and finally 3 collision checker methods, one for checking if the Snake collided
with itself, one for checking if the Snake collided with a wall, and lastly a method that
combines the first two and does both checks simultaneously.