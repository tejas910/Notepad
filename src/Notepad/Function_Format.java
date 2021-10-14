package Notepad;


public class Function_Format {
	Gui gui;
	public Function_Format(Gui gui) {
		this.gui = gui;
	}
	public void wordWrap() {
		if(gui.wordWrapOn==false) {
			gui.wordWrapOn=true;
			gui.textarea.setLineWrap(true);
			gui.textarea.setWrapStyleWord(true);
			gui.wrap.setText("Word Wrap: On");
		}
		else if(gui.wordWrapOn==true) {
			gui.wordWrapOn = false;
			gui.textarea.setLineWrap(false);
			gui.textarea.setWrapStyleWord(false);
			gui.wrap.setText("Word Wrap: Off");
		}
		
		
	}
	
}
