package Notepad;

public class Function_Edit {
	Gui gui;
	public Function_Edit(Gui gui) {
		this.gui = gui;
	}
	
	public void undo() {
		gui.um.undo();
	}
	
	public void redo(){
		gui.um.redo();
	}
}
