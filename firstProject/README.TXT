This is the Greenfoot version of the AP Computer Science case study "GridWorld",
originally written by Cay Horstmann.

We have tried to make minimal changes to the original source code (even though
this means that the structure is slightly more complex than strictly needed in 
Greenfoot.)

The significant changes are:

 - The class 'Actor' from the original version is named 'GridActor' here 
   (because Greenfoot itself has an 'Actor' class, which has a similar role, 
   but is not identical to GridWorld's Actor).
   
 - The examinable classes are almost identical to the original, with the 
   exception of BoundedGrid. The only changes made are changed import statements.
   
 - BoundedGrid has changed significantly. Instead of implementing the grid, 
   it serves as a facade for Greenfoot's World. This is because in Greenfoot,
   the Greenfoot World implements the world grid. We have left BoundedGrid as
   a facade to offer the same interface to the actor subclasses as in the 
   original. UnboundedGrid is currently not included.
   
The setup code (originaly found in the 'Runner' classes, such as BugRunner, can  
be found at the end of the ActorWorld class, in the 'firstProjectSetup' method.

Here are some things you can try in Greenfoot:

 - Interactively place an actor, such as a bug or a rock, by right-clicking 
   the class in the clas diagram on the right. Select a constructor, then 
   place the object.
   
 - A shortcut for placing actors is to shift-click into the world. This places
   an actor of the class that is selected int the diagram on the right, so first
   select your class, then shift-click in the world.
   
 - Move an existing actor just by dragging it in the world.
 
 - Right-click an object (say, a bug) to invoke any of its methods.
 
 - Change the image of any actor by selecting 'Set image' from the class's 
   popup menu.
   
 - Create new actors by selecting 'New subclass' from the GridActor menu.
 
 - Publish your GridWorld to the web by selecting 'Export' from the scenario
   menu.

For more detail, see the Greenfoot tutorial, available from the Help menu.

Greenfoot adaptation by Michael Kolling and Poul Henriksen, May 2007.
