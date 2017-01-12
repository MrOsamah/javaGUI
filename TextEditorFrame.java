// package finalGuiProject; uncomment this 
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class TextEditorFrame extends JFrame 
{
   private final Color[] colorValues = 
      { Color.BLACK, Color.BLUE, Color.RED, Color.GREEN,Color.PINK,Color.MAGENTA};   
   private JRadioButtonMenuItem[] colorItems; // color menu items
   private JRadioButtonMenuItem[] fonts; // font menu items
   private JCheckBoxMenuItem[] styleItems; // font style menu items
   private JLabel displayJLabel; // displays sample text
   private ButtonGroup fontButtonGroup; // manages font menu items
   private ButtonGroup colorButtonGroup; // manages color menu items
   private int style; // used to create style for font
   private JTextArea textArea; // used for editing
   private JScrollPane scrollPane = new JScrollPane(textArea); // used if there are too many rows
   private JFileChooser fileChooser = new JFileChooser(); // used to open and save files
   private BufferedWriter writer; // used to save the file into txt file
   private BufferedReader reader; // used to open an exiting file
   private KeyStroke keyStrokeAbout = KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_MASK); // CTRL+I
   private KeyStroke keyStrokeToExit = KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_MASK); // CTRL+E
   private KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK); // CTRL+S
   private KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_MASK);// CTRL+O
   private KeyStroke keyStrokeNew = KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_MASK); // CTRL+N
   private KeyStroke keyStrokeToContact = KeyStroke.getKeyStroke(KeyEvent.VK_K,KeyEvent.CTRL_MASK); //CTRL+K

   // no-argument constructor set up GUI
   public TextEditorFrame()
   {
      super( "Osamah's Text Editor" );     

      JMenu fileMenu = new JMenu( "File" ); // create file menu
      fileMenu.setMnemonic(KeyEvent.VK_F); // set mnemonic to F

      // new menu
	  JMenuItem newItem = new JMenuItem("New");
      newItem.setAccelerator(keyStrokeNew);
      fileMenu.add(newItem);
      newItem.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent ae) {
            textArea.setText("Hello World :)");
            textArea.setForeground(colorValues[0]);
            textArea.setFont(new Font("Serif",Font.PLAIN,18));
            repaint();
          }
      });
      
	  // open menu
      JMenuItem openItem = new JMenuItem("Open");
      openItem.setAccelerator(keyStrokeToOpen);
      fileMenu.add(openItem);
      openItem.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent ae) {
            JFrame parentFrame = new JFrame();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setDialogTitle("Specify a file to open");
            int userSelction = fileChooser.showOpenDialog(parentFrame);
            
            if(userSelction == JFileChooser.APPROVE_OPTION)
            {
                try {
                  File fileToOpen = fileChooser.getSelectedFile();
                  reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                  String line;
                  while((line = reader.readLine()) != null)
                  {
                      textArea.setText(line);
                  }
                  reader.close();
                  System.out.println("Opend: "+ fileToOpen.getAbsolutePath());
              }catch(IOException e)
              {
                e.printStackTrace();
              }} else if(userSelction == JFileChooser.CANCEL_OPTION){
                  System.out.println("Fail to open file.");
                
            }
          }
      });
      
	  // save menu
      JMenuItem saveItem = new JMenuItem("Save");
      saveItem.setAccelerator(keyStrokeToSave);
      fileMenu.add(saveItem);
      saveItem.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent ae) {
              JFrame parentFrame = new JFrame();
              fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
              fileChooser.setDialogTitle("Specify a file to save");
              int userSelction = fileChooser.showSaveDialog(parentFrame);
              
              if(userSelction ==  JFileChooser.APPROVE_OPTION)
              { try {
                  File fileToSave = new File(fileChooser.getSelectedFile()+".txt");
                  writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()+".txt"));
                  writer.write(textArea.getText());
                  writer.close();
                  System.out.println("Saved as: "+ fileToSave.getAbsolutePath());
                  JOptionPane.showMessageDialog(null, "File: " + fileToSave.getName() +
                          " is saved at " + fileToSave.getAbsolutePath());
              }catch(IOException e)
              {
                e.printStackTrace();
              }} else if(userSelction == JFileChooser.CANCEL_OPTION){
                  System.out.println("Fail to save file.");}
          }
      });
      
      fileMenu.addSeparator();

      JMenuItem exitItem = new JMenuItem( "Exit" ); // create exit item
      exitItem.setAccelerator(keyStrokeToExit);
      fileMenu.add( exitItem ); // add exit item to file menu
      exitItem.addActionListener(

         new ActionListener() 
         {  
            
            public void actionPerformed( ActionEvent event )
            {
               System.exit( 0 ); // exit application
            } 
         } 
      ); 

      JMenuBar bar = new JMenuBar(); // create menu bar
      setJMenuBar(bar); // add menu bar to application
      bar.add( fileMenu ); // add file menu to menu bar

      JMenu formatMenu = new JMenu("Format"); // create format menu
      formatMenu.setMnemonic(KeyEvent.VK_R); // set mnemonic to r

      
      String[] colors = { "Black", "Blue", "Red", "Green","Pink","Magenta" };

      JMenu colorMenu = new JMenu( "Color" ); // create color menu
      colorMenu.setMnemonic(KeyEvent.VK_C); // set mnemonic to C

      // create radio button menu items for colors
      colorItems = new JRadioButtonMenuItem[ colors.length ];
      colorButtonGroup = new ButtonGroup(); // manages colors
      ItemHandler itemHandler = new ItemHandler(); // handler for colors

      // create color radio button menu items
      for ( int count = 0; count < colors.length; count++ ) 
      {
         colorItems[ count ] = 
            new JRadioButtonMenuItem( colors[ count ] ); // create item
         colorMenu.add( colorItems[ count ] ); // add item to color menu
         colorButtonGroup.add( colorItems[ count ] ); // add to group
         colorItems[ count ].addActionListener( itemHandler );
      } // end for

      colorItems[ 0 ].setSelected( true ); // select first Color item

      formatMenu.add( colorMenu ); // add color menu to format menu
      formatMenu.addSeparator(); // add separator in menu

      // array listing font names
      String[] fontNames = { "Serif", "Monospaced", "SansSerif" };
      JMenu fontMenu = new JMenu( "Font" ); // create font menu
      fontMenu.setMnemonic(KeyEvent.VK_N); // set mnemonic to n

      // create radio button menu items for font names
      fonts = new JRadioButtonMenuItem[ fontNames.length ];
      fontButtonGroup = new ButtonGroup(); // manages font names

      // create Font radio button menu items
      for ( int count = 0; count < fonts.length; count++ ) 
      {
         fonts[ count ] = new JRadioButtonMenuItem( fontNames[ count ] );
         fontMenu.add( fonts[ count ] ); // add font to font menu
         fontButtonGroup.add( fonts[ count ] ); // add to button group
         fonts[ count ].addActionListener( itemHandler ); // add handler
      } // end for

      fonts[ 0 ].setSelected( true ); // select first Font menu item
      fontMenu.addSeparator(); // add separator bar to font menu

      String[] styleNames = { "Bold", "Italic" }; // names of styles
      styleItems = new JCheckBoxMenuItem[ styleNames.length ];
      StyleHandler styleHandler = new StyleHandler(); // style handler

      // create style checkbox menu items
      for ( int count = 0; count < styleNames.length; count++ ) 
      {
         styleItems[ count ] = 
            new JCheckBoxMenuItem( styleNames[ count ] ); // for style
         fontMenu.add( styleItems[ count ] ); // add to font menu
         styleItems[ count ].addItemListener( styleHandler ); // handler
      } // end for

      formatMenu.add( fontMenu ); // add Font menu to Format menu
      bar.add( formatMenu ); // add Format menu to menu bar
      
      JMenu infoMenu = new JMenu("Info");
      infoMenu.setMnemonic(KeyEvent.VK_I);
      bar.add(infoMenu);
      
      JMenuItem aboutItem = new JMenuItem("About");
      aboutItem.setAccelerator(keyStrokeAbout);
      infoMenu.add(aboutItem); // add about item to file menu
      aboutItem.addActionListener(new ActionListener() // anonymous inner class
         {  
           
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog(TextEditorFrame.this,
                  "This is Osamah's Text Editor by\n"
                          + "Osamah Alkhamees AKA \"MrOsamah\""
                          + "\nVersion:1.0"
                          + "\n\nWhat will come in the next Versions?"
                          + "\n*Size changing"
                          + "\n*More editing options"
                          + "\n*Selction only editing"
                          + "\n*Save style preferences"
                          + "\n*And a LOT more!",
                  "About", JOptionPane.PLAIN_MESSAGE );
            } 
         } 
      ); 
      
	  // contact menu
      JMenuItem contactUs = new JMenuItem("Contact");
      contactUs.setAccelerator(keyStrokeToContact);
      infoMenu.add(contactUs);
      contactUs.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
              JOptionPane.showMessageDialog(TextEditorFrame.this,
                      "Email:341100313@std.qu.edu.sa"
                              + "\n\nTwitter: @MrOsamah", "Contact",
                              JOptionPane.PLAIN_MESSAGE);
          }
      });
      
      
      textArea = new JTextArea("Hello World :)");
      textArea.setForeground(colorValues[0]);
      textArea.setFont(new Font("Serif",Font.PLAIN,18));
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      
      //add(textArea); does not work if I put it seprate, must be added withen scrollpane
      setPreferredSize(new Dimension(780, 380));
      add(new JScrollPane(textArea)); 
      
   } // end TextEditorFrame constructor

   // inner class to handle action events from menu items
   private class ItemHandler implements ActionListener 
   {
    
      public void actionPerformed( ActionEvent event )
      {
        
         for ( int count = 0; count < colorItems.length; count++ )
         {
            if ( colorItems[ count ].isSelected() ) 
            {
               textArea.setForeground(colorValues[count]);
               break;
            } 
         } 

        
         for ( int count = 0; count < fonts.length; count++ )
         {
            if ( event.getSource() == fonts[ count ] ) 
            {
               textArea.setFont( 
                  new Font( fonts[ count ].getText(), style, 18 ) );
            } 
         } 

         repaint(); // redraw application
      } 
   } 

   // inner class to handle item events from checkbox menu items
   private class StyleHandler implements ItemListener 
   {

      public void itemStateChanged( ItemEvent e )
      {
         String name = textArea.getFont().getName(); // current Font
         Font font; // new font based on user selections

  
         if ( styleItems[ 0 ].isSelected() && 
              styleItems[ 1 ].isSelected() )
            font = new Font( name, Font.BOLD + Font.ITALIC, 18 );
         else if ( styleItems[ 0 ].isSelected() )
            font = new Font( name, Font.BOLD, 18 );
         else if ( styleItems[ 1 ].isSelected() )
            font = new Font( name, Font.ITALIC, 18 );
         else
            font = new Font( name, Font.PLAIN,18);

         textArea.setFont( font );
         repaint(); // redraw application
      } 
   }
} 