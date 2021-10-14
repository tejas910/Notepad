package Notepad;

import java.awt.Color;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
	String Filename;
	String FileAddress;
	Gui gui;
	public Function_File(Gui gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		gui.textarea.setText("");
		gui.window.setTitle("Untitled");
		Filename = null;
		FileAddress = null;
	}
	public void open() {
		FileDialog fd = new FileDialog(gui.window,"Open",FileDialog.LOAD);
		fd.setVisible(true);
		if(fd.getFile()!=null) {
			Filename = fd.getFile();
			FileAddress = fd.getDirectory();
			gui.window.setTitle(Filename);
			
		}
		try {
			BufferedReader bf = new BufferedReader(new FileReader(FileAddress + Filename));
			gui.textarea.setText("");
			String line= null;
			while((line = bf.readLine())!= null) {
				gui.textarea.append(line + "\n");
			}
			bf.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void save() {
		if(Filename==null) {
			saveAs();
		}
		try {
			FileWriter fw = new FileWriter(FileAddress + Filename);
			fw.write(gui.textarea.getText());
			gui.window.setTitle(Filename);
			fw.close();			
		}catch(Exception e ) {
			System.out.println(e);
		}
	}
	public void saveAs() {
		FileDialog fd = new FileDialog(gui.window,"Save",FileDialog.SAVE);
		fd.setVisible(true);
		if(fd.getFile()!=null) {
			Filename = fd.getFile();
			FileAddress = fd.getDirectory();
			gui.window.setTitle(Filename);
		}
		try {
				FileWriter fw = new FileWriter(FileAddress + Filename);
				fw.write(gui.textarea.getText());
				fw.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public void exit() {
		System.exit(0);
	}
	
	public void darkTheme() {
		gui.textarea.setBackground(Color.BLACK);
		gui.textarea.setForeground(Color.WHITE);
	}
	public void lightTheme() {
		gui.textarea.setBackground(Color.white);
		gui.textarea.setForeground(Color.black);
	}
}
