WinWait("Open", "", "30")
If WinExists("Open") Then
	  Sleep(1500);
	  ControlClick("Open", "", "[CLASS:ToolbarWindow32; INSTANCE:3]","right")
  	  Sleep(3000);
	  ControlSend("Open", "", "[CLASS:ToolbarWindow32; INSTANCE:3]","e")
	  Sleep(5000);
	  Send("{DEL}") ;
  	  Sleep(3000);
	  Send($CmdLine[1]);
	  Send("{ENTER}") ;
  	  Sleep(3000);
   EndIf

ControlClick("Open", "", "Edit1","left")
Sleep(3000);
Local $counter = 1
Do
  Send('"')
  Send($CmdLine[$counter])
  Send('"')
  $counter = $counter + 1
Until $counter = $CmdLine[0] + 1
Sleep(5000);
Send("{ENTER}")