A gesture recognition system using pattern recognition and RFID technology
Because the signal recived from RFID tag will change with the movement of tag, so we attach RFID tag to each finger and continuesly moniter the signal from the tag.
Onece we buffered seveal signals, we can extract feture from the tag, and then apply pattern recognition teconology to it.
In this project, we already implement these gestures:
1.Play the piano.
2.Play the rock game.
3.Rotation Scale Movement etc.

If you want to add a new gesture recognition module, you just need to do several steps:
1.Creat A class Extend the AbstractActionChecker.
2.Implement the checkAction method and exitmethod.
3.Add this your actionchecker to ActionManager Class.
