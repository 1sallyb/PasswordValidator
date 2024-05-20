package passwordvalidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class PasswordValidatorForm extends javax.swing.JFrame 
{

    private static final String TEXT_FILE_NAME = "project123.txt";

    public PasswordValidatorForm() 
    {
        initComponents();
        createTextFile();
    }
    
    private static boolean checkPasswordRequirements(String password) 
    {
        boolean isValid = true;
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.");
            isValid = false;
        }
        if (!password.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(null, "Password must contain at least one lowercase letter.");
            isValid = false;
        }
        if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(null, "Password must contain at least one uppercase letter.");
            isValid = false;
        }
        if (!password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Password must contain at least one digit.");
            isValid = false;
        }
        if (!password.matches(".*[!@#$%^&*()-_=+\\|\\[{\\]};:'\",<.>/?`~].*")) {
            JOptionPane.showMessageDialog(null, "Password must contain at least one special character.");
            isValid = false;
        }
        return isValid;
    }
    
     private static String checkCommonPasswords(String password) 
     {
        try {
            File commonPasswordsFile = new File("common_password.txt");
            try (Scanner fileScanner = new Scanner(commonPasswordsFile)) {
                while (fileScanner.hasNextLine()) {
                    String commonPassword = fileScanner.nextLine();
                    if (password.contains(commonPassword)) {
                        return commonPassword;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Common passwords file not found.");
        }
        return null;
    }
     
     
     private static void createTextFile() 
     {
        try {
            File file = new File(TEXT_FILE_NAME);
            if (file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Text file created: " + file.getName());
            } else {
                JOptionPane.showMessageDialog(null, "Text file already exists.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while creating the text file.");
        }
    }
    
    private void validatePassword() 
    {
        String password = new String(PasswordField.getPassword());

        if (!checkPasswordRequirements(password)) {
            return;
        }
        String commonPassword = checkCommonPasswords(password);
        if (commonPassword != null) {
            JOptionPane.showMessageDialog(this, "The part that makes the password common: \"" + commonPassword + "\". Choose a stronger one.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Password is valid.");
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter your password (must be 8 characters, 1 upper, 1 lower, 1 special, and 1 number): ");

        PasswordField.setText("jPasswordField1");

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Check if your password is unique! ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButton1)
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       validatePassword();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordValidatorForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
