# GUI.Domino Game
This project contains two separate files, DominoCLI.jar and DominoGUI.jar,
the first version is a text-based command-line interface version of the game, and the
second version is a GUI.GUI-based implementation of the same game.

By: Will DeBernardi, CS 351, Spring 2021

## Usage
To use this program, run either DominoCLI.jar, or DominoGUI.jar.
### DominoCLI.jar
In this version of the game, the first thing that will be displayed in the command line is
the total number of dominoes currently in the boneyard, the player hand, and three options to
select from. The user can then type in one of the three options to progress the game. On the first
turn, the user is prompted to select a domino (from hand, starting with 0), then asked if they wish
to flip the domino (y/n). On subsequent turns, the user will also have a choice of which side of the
row they would like to place the domino. As the name suggests, the user can use "d" to withdraw a
domino from the boneyard and add it to their hand. Additionally, the user can type "q" to quit the
program.

### DominoGUI.jar
In this version, the user will be presented with a screen that shows the quantity of the boneyard and
player hand at the top of the screen. To the right of that is a label which shows the currently selected
domino from the player hand. By default, the selected domino displays as [0 | 0]. In order to change this
the user can simply click on one of the images of the player hand at the bottom of the screen. Once a new 
domino has been selected, the user can then place the domino. To the left of the hand are some controls
to fine-tune the placement. By default, the radio button labelled "Right", will be selected. This specifies
which side of the row that the domino will be placed on. The third radio button - labelled "Flip" - is the 
control to flip the selected domino before placement. 

## Bugs
- In both versions, when flipping the first placed domino, any subsequent placements will be seen as invalid
and thus will throw an error.
  
- In the CLI version, after placing to the left of the row, the formatting of the output string will become messed
up when trying to place to the right afterwards. It will simply add to the end of the first row, rather than appear
  to be aligned with the computer placed domino. If this happens, the domino that the user must place off of remains
  to be the last placed computer domino or left end of the row.
  
- In the GUI.GUI version, if a player places an invalid domino, it will still be removed from the hand images at the
bottom of the screen. Please don't place an invalid domino.