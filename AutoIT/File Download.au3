#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.5
 Author:         Samrat

 Script Function:
	File Download

#ce ----------------------------------------------------------------------------

#include <MsgBoxConstants.au3>
#include <WinAPIFiles.au3>

; Download a file in the background.
; Wait for the download to complete.

Func Example()
    ; Save the downloaded file to the temporary folder.
    Local $sFilePath = "C:\Users\Samrat\Downloads\IEDriver.zip"

    ; Download the file in the background with the selected option of 'force a reload from the remote site.'
    Local $hDownload = InetGet("https://goo.gl/9Cqa4q", $sFilePath, $INET_FORCERELOAD)

EndFunc
