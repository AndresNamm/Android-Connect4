README
Intro: This is a connect 4 game created for a HKU course. 
The logic is based on a self created GameEngine
The graphics is based on GridView

youtube test link: https://youtu.be/du2DpW5vEdM




REFERENCES


Display 

https://developer.android.com/guide/topics/ui/layout/gridview.html
https://android--code.blogspot.hk/2015/08/android-gridview-selected-item-color.html

Intro to Android Game Design

http://siliconangle.com/blog/2015/07/30/an-updated-list-of-the-best-android-developer-tools-for-your-amazing-mobile-game/
http://www.kilobolt.com/day-1-introduction-to-android.html


DESIGN:

the program consists of 4 classes. 


GameEngine  -
	This is the most important class responsible for the gameLogic. Display changes according to the changes represented here. 
	Responsible for game logic. 
	Manages underlying board. 
	Manages turns.
	Manages Button adding (Checks whether theres room in the column, adds to the correct location/ removing (by Undo)
	Checks Win conditions.
	Position referencing starts from 0
	ReturnNumber larger than boardsize means error. 


GameActivity - 
	Sets up the Second view. 
	This is the view between the GameEngine and The ImageAdapter - Sets them both up.  
	Retrieves buttonclicks from ImageAdapter and passes them accordingly to the GameEngine
	Handles buttonclicks outside the Gridview.
 
ImageAdapter  

	Medium between the GridView and underlying images. 
 
MainActivity 
	StartScreen. 
	Passes intent to start GameActivity 


Typical Scenario: 

Scenario. Start game and play

0) 	MainView passes Intent to start GameActivity
1)	Screen loads GameActivity , which created object gridview. Gridview sets up an ImageAdapter to set pictures to the Grid - gridview.setAdapter(new ImageAdapter(this))– IMAGEADAPTER pushed onto me by AndroidDesign. Initially Gamengine will have a 7 * 8 2D array, which is 
	Main activities in second step
	a.	Set up GameEngine and responsiveGridView
	b.	Connect them 
Start Loop 
2)	If user pushes on a specific column. A tile should be added to it In case there room and gamelogic allows it. GameEngine should change the underlying array
a.	Add tile if theres room -  GameEngine  
b.	Change underlying.array to either 2 or 1 dependent on player.   Gameengine
c. 	Change the display - GameActivity, ImageAdapter. 

3)	Gamengine should Check if win/loose and give try to another player
End Loop if win. 





