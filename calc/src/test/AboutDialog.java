package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {

   private static final long serialVersionUID = 1L;
   private final JPanel contentPanel = new JPanel();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      try {
         AboutDialog dialog = new AboutDialog();
         dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         dialog.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Create the dialog.
    */
   public AboutDialog() {
      setBounds(100, 100, 450, 300);
      getContentPane().setLayout(new BorderLayout(0, 0));
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel);
      contentPanel.setLayout(new BorderLayout(0, 0));
      {
         JLabel lblNewLabel = new JLabel("<html>\r\n<center>\r\n<h3> 작성자\r\n</h3>\r\n<h1> 이승철\r\n</h1>\r\n</center>\r\n</html>");
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         contentPanel.add(lblNewLabel, BorderLayout.CENTER);
      }
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setVisible(false);
               }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
         }
      }
   }

}