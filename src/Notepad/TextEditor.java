package Notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.undo.UndoManager;

public class TextEditor extends JFrame implements ActionListener{

 JTextArea textArea;
 JScrollPane scrollPane;
 JLabel fontLabel;
 JSpinner fontSizeSpinner;
 JButton fontColorButton;
 JComboBox fontBox;
 
 JMenuBar menuBar;
 JMenu fileMenu,editMenu,theme,help;
 JMenuItem openItem;
 JMenuItem saveItem;
 JMenuItem exitItem;
 JMenuItem newItem;
 JMenuItem saveAsItem;
 JMenuItem undoItem,redoItem,cutItem,copyItem,pasteItem;
 JMenuItem dark,light,aboutEditor;
 UndoManager um = new UndoManager();
 ImageIcon img = new ImageIcon("./assets/icon.png");
 
 
 TextEditor(){
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setTitle("Shodan");
  this.setIconImage(img.getImage());
  this.setForeground(Color.black);
  this.setFont(new Font("NewsGoth BT", 0, 18));
  this.setSize(500, 500);
  this.setLayout(new FlowLayout());
  this.setLocationRelativeTo(null);
  this.setResizable(false);
  
  createTextArea();
  fontLabel = new JLabel("Font: ");
  fontLabel.setForeground(Color.black);
  fontLabel.setFont(new Font("NewsGoth BT", 0, 15));
  
  fontSizeSpinner = new JSpinner();
  fontSizeSpinner.setForeground(Color.black);
  fontSizeSpinner.setFont(new Font("NewsGoth BT", 0, 15));
  fontSizeSpinner.setPreferredSize(new Dimension(50,25));
  fontSizeSpinner.setValue(20);
  fontSizeSpinner.addChangeListener(new ChangeListener() {

   @Override
   public void stateChanged(ChangeEvent e) {
    
    textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue())); 
   }
   
  });
  
  fontColorButton = new JButton("Color");
  fontColorButton.setIcon(new ImageIcon("./assets/mcolor.png"));
  fontColorButton.setForeground(Color.RED);
  fontColorButton.setFont(new Font("NewsGoth BT", 0, 15));
  fontColorButton.addActionListener(this);
  
  String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  
  fontBox = new JComboBox(fonts);
  fontBox.setForeground(Color.black);
  fontBox.setFont(new Font("NewsGoth BT", 0, 15));
  fontBox.addActionListener(this);
  fontBox.setSelectedItem("Arial");
  
  // ------ menubar ------
  
   menuBar = new JMenuBar();
   fileMenu = new JMenu("File");
   fileMenu.setIcon(new ImageIcon("./assets/file.png"));
   fileMenu.setBackground(Color.white);
   fileMenu.setForeground(Color.black);
   fileMenu.setFont(new Font("NewsGoth BT", 0, 18));
   newItem = new JMenuItem("New");
   newItem.setIcon(new ImageIcon("./assets/newfile.png"));
   newItem.setForeground(Color.black);
   newItem.setFont(new Font("NewsGoth BT", 0, 18));
   openItem = new JMenuItem("Open");
   openItem.setIcon(new ImageIcon("./assets/openfile.png"));
   openItem.setForeground(Color.black);
   openItem.setFont(new Font("NewsGoth BT", 0, 18));
   saveItem = new JMenuItem("Save");
   saveItem.setIcon(new ImageIcon("./assets/savefile.png"));
   saveItem.setForeground(Color.BLACK);
   saveItem.setFont(new Font("NewsGoth BT", 0, 18));
   exitItem = new JMenuItem("Exit");
   exitItem.setIcon(new ImageIcon("./assets/exit.png"));
   exitItem.setForeground(Color.black);
   exitItem.setFont(new Font("NewsGoth BT", 0, 18));
   
   
   newItem.addActionListener(this);
   openItem.addActionListener(this);
   saveItem.addActionListener(this);
   exitItem.addActionListener(this);
   
   fileMenu.add(newItem);
   fileMenu.add(openItem);
   fileMenu.add(saveItem);
   fileMenu.add(exitItem);
 
   menuBar.add(fileMenu);
   
   editMenu = new JMenu("Edit");
   editMenu.setIcon(new ImageIcon("./assets/edit.png"));
   editMenu.setForeground(Color.BLACK);
   editMenu.setFont(new Font("NewsGoth BT", 0, 18));
   undoItem = new JMenuItem("Undo");
   undoItem.setIcon(new ImageIcon("./assets/undo.png"));
   undoItem.setForeground(Color.black);
   undoItem.setFont(new Font("NewsGoth BT", 0, 18));
   redoItem = new JMenuItem("Redo");
   redoItem.setIcon(new ImageIcon("./assets/redo.png"));
   redoItem.setForeground(Color.black);
   redoItem.setFont(new Font("NewsGoth BT", 0, 18));
   cutItem = new JMenuItem("Cut");
   cutItem.setIcon(new ImageIcon("./assets/cut.png"));
   cutItem.setForeground(Color.black);
   cutItem.setFont(new Font("NewsGoth BT", 0, 18));
   copyItem = new JMenuItem("Copy");
   copyItem.setIcon(new ImageIcon("./assets/copy.png"));
   copyItem.setForeground(Color.black);
   copyItem.setFont(new Font("NewsGoth BT", 0, 18));
   pasteItem = new JMenuItem("Paste");
   pasteItem.setIcon(new ImageIcon("./assets/paste.png"));
   pasteItem.setForeground(Color.black);
   pasteItem.setFont(new Font("NewsGoth BT", 0, 18));
   
   undoItem.addActionListener(this);
   redoItem.addActionListener(this);
   cutItem.addActionListener(this);
   copyItem.addActionListener(this);
   pasteItem.addActionListener(this);
   
   editMenu.add(undoItem);
   editMenu.add(redoItem);
   editMenu.add(cutItem);
   editMenu.add(copyItem);
   editMenu.add(pasteItem);
 
   menuBar.add(editMenu);
  
   theme = new JMenu("Theme");
   theme.setIcon(new ImageIcon("./assets/selectioncolor.png"));
   theme.setForeground(Color.black);
   theme.setFont(new Font("NewsGoth BT", 0, 18));
   dark = new JMenuItem("Dark");
   dark.setIcon(new ImageIcon("./assets/black.png"));
   dark.setForeground(Color.black);
   dark.setFont(new Font("NewsGoth BT", 0, 18));
   light = new JMenuItem("Light");
   light.setIcon(new ImageIcon("./assets/white.png"));
   light.setForeground(Color.black);
   light.setFont(new Font("NewsGoth BT", 0, 18));
   dark.addActionListener(this);
   light.addActionListener(this);
   
   theme.add(dark);
   theme.add(light);
   
   menuBar.add(theme);
   
   help = new JMenu("Help");
   help.setIcon(new ImageIcon("./assets/help.png"));
   help.setForeground(Color.black);
   help.setFont(new Font("NewsGoth BT", 0, 18));
   aboutEditor = new JMenuItem("About Editor");
   aboutEditor.setIcon(new ImageIcon("./assets/about.png"));
   aboutEditor.setForeground(Color.black);
   aboutEditor.setFont(new Font("NewsGoth BT", 0, 18));
   aboutEditor.addActionListener(this);
   help.add(aboutEditor);
   menuBar.add(help);
   
  // ------ /menubar ------
   
  this.setJMenuBar(menuBar);
  this.add(fontLabel);
  this.add(fontSizeSpinner);
  this.add(fontColorButton);
  this.add(fontBox);
  this.add(scrollPane);
  this.setVisible(true);
 }
 
 public void createTextArea() {
	  textArea = new JTextArea();
	  textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());
			}
		});
		
		 textArea.addCaretListener(new CaretListener() {
	        @Override
			 public void caretUpdate(CaretEvent ce) {
	            System.out.println("All text: " + textArea.getText());
	            if (textArea.getSelectedText() != null)
	               System.out.println("Selected text: " + textArea.getSelectedText());
	            else
	               System.out.println("Selected text: ");
	         }

			
	      });

	  textArea.setLineWrap(true);
	  textArea.setWrapStyleWord(true);
	  textArea.setFont(new Font("Arial",Font.PLAIN,20));
	  
	  scrollPane = new JScrollPane(textArea);
	  scrollPane.setPreferredSize(new Dimension(451,450));
	  scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  }
	 
 
 @Override
 public void actionPerformed(ActionEvent e) {
  
  if(e.getSource()==fontColorButton) {
   JColorChooser colorChooser = new JColorChooser();
   
   Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
   
   textArea.setForeground(color);
  }
  
  if(e.getSource()==fontBox) {
   textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
  }
  
  if(e.getSource()==newItem) {
		textArea.setText("");
		this.setTitle("Untitled");

  }
  
  if(e.getSource()==openItem) {
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new File("."));
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
   fileChooser.setFileFilter(filter);
   
   int response = fileChooser.showOpenDialog(null);
   
   if(response == JFileChooser.APPROVE_OPTION) {
    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
    Scanner fileIn = null;
    
    try {
     fileIn = new Scanner(file);
     if(file.isFile()) {
      while(fileIn.hasNextLine()) {
       String line = fileIn.nextLine()+"\n";
       textArea.append(line);
      }
     }
    } catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    finally {
     fileIn.close();
    }
   }
  }
  if(e.getSource()==saveItem) {
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setCurrentDirectory(new File("."));
   
   int response = fileChooser.showSaveDialog(null);
   
   if(response == JFileChooser.APPROVE_OPTION) {
    File file;
    PrintWriter fileOut = null;
    
    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
    try {
     fileOut = new PrintWriter(file);
     fileOut.println(textArea.getText());
    } 
    catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    finally {
     fileOut.close();
    }   
   }
  }
  if(e.getSource()==exitItem) {
   System.exit(0);
  }
  
  if(e.getSource()==undoItem) {
	  this.um.undo();
  }
  
  if(e.getSource()==redoItem) {
	  this.um.redo();
  }
  if(e.getSource()==cutItem) {
	textArea.cut();
  }
  if(e.getSource()==copyItem) {
	  textArea.copy();
  }
  if(e.getSource()==pasteItem) {
	  textArea.paste();
  }
  if(e.getSource()==dark) {
	  textArea.setBackground(Color.BLACK);
	  textArea.setForeground(Color.WHITE);
  }
  
  if(e.getSource()==light) {
	  textArea.setBackground(Color.WHITE);
	  textArea.setForeground(Color.BLACK);
  }
  
  if(e.getSource()==aboutEditor) {
	  JOptionPane.showMessageDialog(this, "This Notepad is built in Swing and AWT which is part of java technology.");
  }
  
 }
}