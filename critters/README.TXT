This is the Greenfoot version of the "critters" project of the AP Computer 
Science case study "GridWorld", originally written by Cay Horstmann.

The general changes are the same as in the "firstProject" and "BoxRunner"
projects (please see there for details). That is: all GridActor subclasses
are essentially unchanged from their original version.

This scenario contains the setup code for the "Critter", "ChameleonCritter",
and "CrabCritter" examples (originally contained in the CritterRunner,
ChameleonRunner, and CrabRunner classes).

By default, this scenario sets up the critter example, but that can easily be 
changed. 

The ActorWorld class contains three methods: critterSetup(), chameleonSetup(), 
and crabSetup(). These methods initialize the examples. In the default version, 
critterSetup() gets called from the ActorWorld constructor -- that's why you 
see this setup first.

To use the other examples, you can do one of two things:

 - change the critterSetup() call in the ActorWorld constructor to call one
   of the other setup methods; or
   
 - Choose one of the other setup methods (chameleonSetup() or crabSetup()) from
   the world object's popup menu. You can see the world object's menu by right-
   clicking on the name of the world (the label "ActorWorld" above the world grid).
