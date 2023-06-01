# Reading Companion App

This is an application to help those who like reading books, very long books. 
Some readers might find it difficult to remember everything that happens in a novel,
especially if it packed with many characters and locations. There might also be those who had read a book or series of
books a long time ago, and would like to refresh their memory.
This application allows just that by letting the readers keep track of:
- Characters
- Locations
- Plot Points

The Reading Companion also makes sure that no information is revealed before its time. It keeps track of where in a book
a reader is, and only reveals information that has been seen before that point.

This project is interesting to me, because I myself like reading a lot and also going back to book I have read a while
ago. In that case an application like this one would prove useful.

## User Stories

- As a user, I want to be able to display all the characters seen so far.
- As a user, I want to be able to display all the locations seen so far.
- As a user, I want to be able to display in order the plot points so far.
- As a user, I want to be able to mark the chapters of the book I have read, unlocking new information.
- As a user, I want to be able to skip to a chapter in the book.
- As a user, I want to be able to take my own notes, adding characters/locations/plot points to the list.
- As a user, I want to be able to create my own notes from scratch.
- As a user, I want to be able to save the characters/locations/plotPoints I have added to a file.
- As a user, I want to be able to save the current chapter of the book.
- As a user, I want to be able to load what I have previously saved on a file.
- As a user, I want to be able to have different save files that I can save to/load from.
- As a user, I want to be able to see the names of the books saved to those files.
- As a user, I want to be able to see if a save file is empty.
- As a user, I want to be able to see the cover of the book on the screen.
- As a user, I want to be able to change the image displayed as the book's cover.

## Phase 4: Task 2

The Log:
The characters, locations and the summary of plot were displayed.
Character: Kevin, was added to the book
Character: Kevin 2, was added to the book
Character: Kevin 3, was added to the book
Location: Canada, was added to the book
New plot point was added to chapter 1.
New plot point was added to chapter 2.
New plot point was added to chapter 9.
The characters, locations and the summary of plot were displayed.

## Phase 4: Task 3
I like most of the design presented in my diagram. However, if I had more time to work on the project:
- I would add the Observer Pattern to some of the ui classes. Currently, a lot of classes have instances of each other
only so that they can update information when a change is made. A lot of calls need to be made every time. The Observer
Pattern could prevent this.
- I would do more refactoring in some of the ui classes to increase cohesion. At the moment, there are some methods that
don't really belong to their class.
- I would improve the visuals I added in Phase 3. I could work more on the layouts of the panels displayed on screen.
