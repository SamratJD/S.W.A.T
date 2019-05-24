#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.5
 Author:         Samrat Nag

 Script Function:
	Window Authentication Handling IE

#ce ----------------------------------------------------------------------------
WinWaitActive("Windows Security")
Send("username")
Send("{TAB}")
Send("password")
Send("{ENTER}")