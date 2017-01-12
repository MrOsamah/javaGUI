// package finalGuiProject; uncomment this
import javax.swing.JFrame;

public class TextEditorMain
{
   public static void main( String[] args )
   { 
      TextEditorFrame menuFrame = new TextEditorFrame(); // create TextEditorFrame 
      menuFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      menuFrame.setSize( 800, 400 ); // set frame size
      menuFrame.setVisible( true ); // display frame
   } 
} 