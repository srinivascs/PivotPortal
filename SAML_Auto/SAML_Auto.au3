#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.12.0
 Author:  Manohar Basavaiah <manohar.x.basavaiah@rrd.com>

 Script Function: This script is used to launch Pivot SAML tests using AutoIt


#ce ----------------------------------------------------------------------------
#include <Constants.au3>

;$Envi = "STAGE"
$Envi = StringUpper($CmdLineRaw)


Run (@ScriptDir & '\SAML Test Harness')
Sleep(2000)
WinSetState("Post Saml Assertion", "", @SW_MAXIMIZE)
AutoItSetOption('MouseCoordMode', 0)

WinWait('Post Saml Assertion', '', 10)
WinActivate('Post Saml Assertion')

ControlClick("Post Saml Assertion", "", "[Name:btnOpen]")
;MouseClick('primary', 172, 451, 1, 0)

WinWait('Open', '', 10)
Sleep(300)
ControlFocus("Open", "", "Edit1")
ControlSetText("Open", "", "Edit1", "C:\Pivot_Portal\SAML_Auto\Test Config - "&$Envi&" - ABC Company.xml")
Sleep(2000)
ControlClick("Open", "", "Button1")
Sleep(500)

WinWait('Post Saml Assertion', '', 10)
WinActivate('Post Saml Assertion')
ControlClick("Post Saml Assertion", "", "[Name:btnSelectCertficate]")
Sleep(2000)

WinWait('Select Certificate', '', 10)
Sleep(300)

WinActivate('Select Certificate')
ControlClick("Select Certificate", "", "[Name:btnSelect]")
Sleep(2000)

WinWait('Open', '', 10)
ControlFocus("Open", "", "Edit1")
ControlSetText("Open", "", "Edit1", "C:\Pivot_Portal\SAML_Auto\Pivot QA SAML Signing Cert.p12")
Sleep(300)
ControlClick("Open", "", "Button1")
Sleep(300)

WinActivate('Select Certificate')
ControlSetText("Select Certificate", "", "[Name:txtPassword]", "")
;MouseClick('primary', 126, 78, 2, 0)
Sleep(1300)
ControlSetText("Select Certificate", "", "[Name:txtPassword]", "Q@4P1v0t")
Sleep(300)

WinActivate('Select Certificate')
ControlClick("Select Certificate", "", "[Name:btnOk]")
Sleep(300)

;This is PostAssertion
WinWait('Post Saml Assertion', '', 10)
WinActivate('Post Saml Assertion')
ControlClick("Post Saml Assertion", "", "[Name:btnPostAssertion]")
Sleep(4000)

WinWait('Security Alert', '', 10)
If WinExists('Security Alert') Then
   WinActivate('Security Alert')
   Sleep(300)
   ControlClick("Security Alert", "", "Button1")
   Sleep(300)
EndIf

WinWait('Security Alert', '', 10)
If WinExists('Security Alert') Then
   WinActivate('Security Alert')
   Sleep(300)
   ControlClick("Security Alert", "", "Button1")
   Sleep(300)
EndIf


WinWait('Security Alert', '', 10)
If WinExists('Security Alert') Then
   WinActivate('Security Alert')
   Sleep(300)
   ControlClick("Security Alert", "", "Button1")
   Sleep(300)
EndIf

WinWait('Security Alert', '', 10)
If WinExists('Security Alert') Then
   WinActivate('Security Alert')
   Sleep(300)
   ControlClick("Security Alert", "", "Button1")
   Sleep(300)
EndIf



;Verify the results here
WinWait('[CLASS:Internet Explorer_Server]', '', 10)
If WinExists('[CLASS:Internet Explorer_Server]') Then
   ConsoleWrite("An error has occurred")
   exit 0
   ;ConsoleWrite("An error has occurred. If the problem persists please contact the site administer. Thank you. Error code: 854286403574427750-58e37f05")
EndIf

Local $sText = WinGetText("[ACTIVE]")
; Display the window text.
;MsgBox($MB_SYSTEMMODAL, "", $sText, )

If WinExists('[CLASS:Internet Explorer_Server]') Then
   MsgBox(4, "Page Error", "This page can’t be displayed, Proceeding further!", 10)
   WinActivate('Post Saml Assertion')
   MouseClick('primary', 411, 572, 1, 0)
EndIf

 ;WinClose('Post Saml Assertion')