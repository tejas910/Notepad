package Notepad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class Gui implements ActionListener {
	
	JFrame  window;
	//Text Area
	JTextArea textarea;
	JScrollPane scrollPane;
	boolean wordWrapOn = false;
	// Menubar
	JMenuBar menubar;
	JMenu menuFile, menuEdit, menuFormat, menuColor, menuColorPalete;
	// File Menu
	JMenuItem iNew, iOpen, iSave, iSavesAs, iExit;
	JMenuItem black,white;
	// File Format
	JMenuItem wrap,fontArial,fontCSMS,fontTNR, fontSize0, fontSize12, fontSize16, fontSize20, fontSize24,fontSize28;
	JMenu menuFont, menuFontSize;
	// File Edit
	JMenuItem undo,redo,cut,copy,paste;
	
	    JSpinner spinner;
	    
	Function_File file = new Function_File(this);
	Function_Format format =  new Function_Format(this);
	Function_Edit fe = new Function_Edit(this);
	UndoManager um = new UndoManager();
	
	public static void main(String[] args) {
		new Gui();
	}
	public Gui() {
		createWindow();
		createTextarea();
		menuBar();
		createFileMenu();
		createEditMenu();
		createColorMenu();
	//	createFormatMenu();
		window.setVisible(true);
	}
	public void createWindow() {
		window = new JFrame("Notepad");
		window.setSize(600,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.put("JFrame.activeTitleBackground", Color.red);
		
	}
	public void createTextarea() {
		textarea = new JTextArea();
		
		textarea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());
			}
		});
		
		 textarea.addCaretListener(new CaretListener() {
	        @Override
			 public void caretUpdate(CaretEvent ce) {
	            System.out.println("All text: " + textarea.getText());
	            if (textarea.getSelectedText() != null)
	               System.out.println("Selected text: " + textarea.getSelectedText());
	            else
	               System.out.println("Selected text: ");
	         }

			
	      });
		
		scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.add(scrollPane);
//		window.add(textarea);
	}
	
	public void menuBar() {
		menubar = new JMenuBar();
		window.setJMenuBar(menubar);
		
		menuFile = new JMenu("File");
		menubar.add(menuFile);
		
		menuEdit = new JMenu("Edit");
		menubar.add(menuEdit);
		
//		menuFormat = new JMenu("Format");
		
		menuColor = new JMenu("Theme");
		menubar.add(menuColor);
		
		menuColorPalete = new JMenu("Color");
		menuColorPalete.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Click");
				Color c=JColorChooser.showDialog(menuColorPalete,"Choose",Color.CYAN);  
			    textarea.setBackground(c);  	
			}
		});
		menubar.add(menuColorPalete);
		
		
//		spinner = new JSpinner();
//		spinner.setPreferredSize(new Dimension(50,25));
//		spinner.setValue(20);
//		spinner.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				textarea.setFont(new Font(textarea.getFont().getFamily(),Font.PLAIN, (int)spinner.getValue()));
//			}
//		});
//		
		
	}
	public void createFileMenu() {
		iNew = new JMenuItem("New");
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
		
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);
		
		iSavesAs = new JMenuItem("Save As");
		iSavesAs.addActionListener(this);
		iSavesAs.setActionCommand("Save As");
		menuFile.add(iSavesAs);
		
		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}
	
	public void createColorMenu() {
		black = new JMenuItem("Dark");
		black.addActionListener(this);
		black.setActionCommand("Dark");
		menuColor.add(black);
		
		white = new JMenuItem("Light");
		white.addActionListener(this);
		white.setActionCommand("Light");
		menuColor.add(white);
	}
	
	public void createEditMenu() {
		
		undo = new JMenuItem("Undo");
		undo.addActionListener(this);
		undo.setActionCommand("Undo");
		menuEdit.add(undo);
		
		redo = new JMenuItem("Redo");
		redo.addActionListener(this);
		redo.setActionCommand("Redo");
		menuEdit.add(redo);
		
		cut = new JMenuItem("Cut");
		cut.addActionListener(this);
		cut.setActionCommand("Cut");
		menuEdit.add(cut);
		
		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		copy.setActionCommand("Copy");
		menuEdit.add(copy);
		
		paste = new JMenuItem("Paste");
		paste.addActionListener(this);
		paste.setActionCommand("Paste");
		menuEdit.add(paste);
		
	}
	
	public void createFormatMenu() {
		wrap = new JMenuItem("Word Wrap: Off");
		wrap.addActionListener(this);
		wrap.setActionCommand("Word Wrap");
		menuFormat.add(wrap);
		
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		fontArial = new JMenuItem("Arial");
		fontArial.addActionListener(this);
		fontArial.setActionCommand("Arial");
		menuFont.add(fontArial);
		
		fontCSMS = new JMenuItem("Comic Sans MS");
		fontCSMS.addActionListener(this);
		fontCSMS.setActionCommand("Comic Sans MS");
		menuFont.add(fontCSMS);
		
		fontTNR = new JMenuItem("Times New Romen");
		fontTNR.addActionListener(this);
		fontTNR.setActionCommand("Times New Romen");
		menuFont.add(fontTNR);
		
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		fontSize0 = new JMenuItem("0");
		fontSize0.addActionListener(this);
		fontSize0.setActionCommand("0");
		menuFontSize.add(fontSize0);
		
		fontSize12 = new JMenuItem("12");
		fontSize12.addActionListener(this);
		fontSize12.setActionCommand("12");
		menuFontSize.add(fontSize12);
		
		fontSize16 = new JMenuItem("16");
		fontSize16.addActionListener(this);
		fontSize16.setActionCommand("16");
		menuFontSize.add(fontSize16);
		
		fontSize20 = new JMenuItem("20");
		fontSize20.addActionListener(this);
		fontSize20.setActionCommand("20");
		menuFontSize.add(fontSize20);
		
		fontSize24 = new JMenuItem("24");
		fontSize24.addActionListener(this);
		fontSize24.setActionCommand("24");
		menuFontSize.add(fontSize24);
		
		fontSize28 = new JMenuItem("28");
		fontSize28.addActionListener(this);
		fontSize28.setActionCommand("28");
		menuFontSize.add(fontSize28);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "New":
			file.newFile();
			break;
		case "Open":
			file.open();
			break;
		case "Save":
			file.save();
			break;
		case "Save As":
			file.saveAs();
			break;
		case "Exit":
			file.exit();
			break;
		case "Undo":
			fe.undo();
			break;
		case "Redo":
			fe.redo();
			break;
		case "Cut":
			textarea.cut();
			break;
		case "Copy":
			textarea.copy();
			break;
		case "Paste":
			textarea.paste();
			break;
		case "Dark":
			file.darkTheme();
			break;
		case "Light":
			file.lightTheme();
			break;
		case "Word Wrap":
			format.wordWrap();
			break;
		case "Color":

			break;
		default:
			break;
		}
	}
}
