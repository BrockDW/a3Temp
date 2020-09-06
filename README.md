# Team2-Week2
## Breakout
### Java + Swing
#### Teammates
* Xiao  Xiong
* Haozhang Yuan 
* Therisa Tran


## Intro


This version of breakout the user's goal is to have the ball escape to the right edge. Bricks are randomly generated from the MainScreen file to impede the ball's escape. The user must move the paddle **up and down with the arrow keys** to destroy these bricks to obtain a clear path to the other side. Upon escape, the user will be prompted with an end screen informing them of their win with the time the user took to beat the game. If the user allows the ball to escape behind the paddle (left wall), the user will be shown an end screen notifying that they had lost with the time for how long the user played.

## Obeserver Pattern

The observer pattern, a one to many relationship, was implemented. Rather than having every object asking whether the observable has change and updating according to that knowledge. The observable notifies the observers of the fact that the observable has changed.

In this instance our obserable is:
* MainScreen

The observers are: 
* Border (Top, Bottom, Right, Left Walls)
* Bricks
* Paddle
* Ball
* Clock


## Oberservations
For a more detailed explanation can be found at [note.txt](https://github.iu.edu/sraizada/breakout/blob/master/notes.txt)
