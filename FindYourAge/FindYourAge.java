import static java.util.Calendar.YEAR;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FindYourAge extends JFrame 
{
   private JLabel nameLabel;
   private JLabel genderLabel;
   private JLabel dateLabel;
   private JTextField textField; 
   private JRadioButton maleJRadioButton;
   private JRadioButton femaleJRadioButton;
   private ButtonGroup radioGroup;
   private JComboBox dateComboBox;
   private String[] dates = {"2010","2009","2008","2007","2006","2005","2004"
   ,"2003","2002","2001","2000","1999","1998","1997","1996","1995","1994",
   "1993","1992","1991","1990","1989","1988","1987","1986","1985","1984",
   "1983","1982","1981","1980","1979","1978","1977","1976","1975","1974","1973"
   ,"1972","1971","1970","1969","1968","1967","1966","1965"};
   private String chosenDate = new String(); // save the string the user chose
   private JButton goButton;

  
   public FindYourAge()
   {
      super("Find Your Age!");
      setLayout( new FlowLayout() );
      
      nameLabel = new JLabel("Name:");
      add(nameLabel);
      textField = new JTextField(25); // user can enter his name
      add(textField);
      
      genderLabel = new JLabel("Gender:");
      maleJRadioButton = new JRadioButton( "Male", true );
      femaleJRadioButton = new JRadioButton( "Female", false );
      add(genderLabel);
      add( maleJRadioButton );
      add( femaleJRadioButton );
      
      radioGroup = new ButtonGroup();
      radioGroup.add( maleJRadioButton );
      radioGroup.add( femaleJRadioButton );
      
      
      dateLabel = new JLabel("Date of Birth:");
      dateComboBox = new JComboBox(dates);
      dateComboBox.setMaximumRowCount(5);
      dateComboBox.addActionListener(new ActionListener() {
         
          public void actionPerformed(ActionEvent ae) {
            JComboBox combobox =  (JComboBox)ae.getSource();
               chosenDate = (String) combobox.getSelectedItem();
          }
      });
      
      add(dateLabel);
      add(dateComboBox);
      
      goButton = new JButton("Go");
      goButton.addActionListener(new ActionListener() {
        
          public void actionPerformed(ActionEvent ae) {
             String name = textField.getText();
             
             // cast chosen date as integer and sustract it from current year
             int age = Calendar.getInstance().get(Calendar.YEAR) -
                     Integer.parseInt(chosenDate);
             
             if(maleJRadioButton.isSelected())
             {
                 name = "Mr. " + name;
             } else {
                 name = "Ms. " + name;
             }
             
             JOptionPane.showMessageDialog(FindYourAge.this, String.format("Hello"
                     + " " + name + ", you are " + age + " years old!",
                     ae.getActionCommand()));
          }
      });
      add(goButton);


    }
}

