WinWait("Open", "", "20")
If WinExists("Open") Then
   ControlSetText("Open", "", "Edit1", $CmdLine[1]);
   Sleep(1000);
   ControlClick("Open", "", "&Open");
EndIf