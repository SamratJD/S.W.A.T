#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.5
 Author:         Samrat Nag

 Script Function:
	Window Authentication Handling Firefox

#ce ----------------------------------------------------------------------------
WinWaitActive("Authentication Required")
Send("username")
Send("{TAB}")
Send("password")
Send("{ENTER}")