/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Serveur_Chat.java
 *
 * Created on 30 oct. 2011, 13:25:55
 */

package ferrychatserveur;

import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utils.PropertiesLauncher;

/**
 *
 * @author Sh1fT
 */

public final class Serveur_Chat extends javax.swing.JFrame {
    protected DemarrerServeur demarrerServeur;
    protected ServerSocket sSocket;
    protected Socket cSocket;
    protected PropertiesLauncher propertiesLauncher;
    protected Connection mysqlConnection;
    protected java.sql.Statement mysqlStatement;
    protected Protocole protocole;

    /**
     * Creates new form Serveur_IFreetax
     */
    public Serveur_Chat() {
        this.initComponents();
        this.setDemarrerServeur(null);
        this.setSSocket(null);
        this.setCSocket(null);
        this.setPropertiesLauncher(new PropertiesLauncher(
            System.getProperty("file.separator") + "properties" +
            System.getProperty("file.separator") + "FerryChatServeur.properties"));
        this.initMySqlDB();
        this.setProtocole(new Protocole(this));
    }

    public DemarrerServeur getDemarrerServeur() {
        return this.demarrerServeur;
    }

    protected void setDemarrerServeur(DemarrerServeur demarrerServeur) {
        this.demarrerServeur = demarrerServeur;
    }

    public ServerSocket getSSocket() {
        return this.sSocket;
    }

    protected void setSSocket(ServerSocket sSocket) {
        this.sSocket = sSocket;
    }

    public Socket getCSocket() {
        return this.cSocket;
    }

    protected void setCSocket(Socket cSocket) {
        this.cSocket = cSocket;
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return this.propertiesLauncher;
    }

    protected void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public Connection getMysqlConnection() {
        return this.mysqlConnection;
    }

    protected void setMysqlConnection(Connection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    public java.sql.Statement getMysqlStatement() {
        return this.mysqlStatement;
    }

    protected void setMysqlStatement(java.sql.Statement mysqlStatement) {
        this.mysqlStatement = mysqlStatement;
    }

    public Protocole getProtocole() {
        return this.protocole;
    }

    protected void setProtocole(Protocole protocole) {
        this.protocole = protocole;
    }

    public JLabel getClientLabel() {
        return this.clientLabel;
    }

    public Integer getServerPort() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("serverPort"));
    }

    public String getGroupHost() {
        return this.getPropertiesLauncher().getProperties().getProperty("groupHost");
    }

    public Integer getGroupPort() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("groupPort"));
    }

    public String getMysqlHost() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlHost");
    }

    public Integer getMysqlPort() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("mysqlPort"));
    }

    public String getMysqlDbName() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlDbName");
    }

    public String getMysqlUsername() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlUsername");
    }

    public String getMysqlPassword() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlPassword");
    }

    public String getLoginQuery() {
        return this.getPropertiesLauncher().getProperties().getProperty("loginQuery");
    }

    /**
     * Initialise une connexion avec la base de données MySQL
     */
    protected void initMySqlDB() {
        String url = "jdbc:mysql://" + this.getMysqlHost() + ":" +
            this.getMysqlPort() + "/" + this.getMysqlDbName();
        String username = this.getMysqlUsername();
        String password = this.getMysqlPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.setMysqlConnection(DriverManager.getConnection(url, username, password));
            this.getMysqlConnection().setAutoCommit(false);
            this.setMysqlStatement(this.getMysqlConnection().createStatement());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Ferme la connexion avec la base de données MySQL
     */
    protected void closeMySqlDB() {
        try {
            this.getMysqlStatement().close();
            this.getMysqlConnection().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        demarrerToggleButton = new javax.swing.JToggleButton();
        arreterToggleButton = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etatLabel = new javax.swing.JLabel();
        clientLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        quitterButton = new javax.swing.JButton();

        buttonGroup1.add(this.demarrerToggleButton);
        buttonGroup1.add(this.arreterToggleButton);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serveur_Chat");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        demarrerToggleButton.setText("Démarrer");
        demarrerToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demarrerToggleButtonActionPerformed(evt);
            }
        });

        arreterToggleButton.setText("Arrêter");
        arreterToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arreterToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(demarrerToggleButton)
                .addGap(18, 18, 18)
                .addComponent(arreterToggleButton)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arreterToggleButton)
                    .addComponent(demarrerToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations"));

        jLabel1.setText("Client connecté :");

        jLabel2.setText("État du serveur :");

        etatLabel.setText("inconnu");

        clientLabel.setText("aucun");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(etatLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(clientLabel)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(etatLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clientLabel)))
        );

        quitterButton.setText("Quitter");
        quitterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(quitterButton)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quitterButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void demarrerToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demarrerToggleButtonActionPerformed
        if (this.getDemarrerServeur() == null) {
            this.setDemarrerServeur(new DemarrerServeur(this));
            this.getDemarrerServeur().start();
            this.etatLabel.setForeground(Color.green);
            this.etatLabel.setText("démarré");
        }
    }//GEN-LAST:event_demarrerToggleButtonActionPerformed

    private void arreterToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arreterToggleButtonActionPerformed
        if (this.getDemarrerServeur() != null) {
            this.getDemarrerServeur().interrupt();
            this.setDemarrerServeur(null);
            this.etatLabel.setForeground(Color.red);
            this.etatLabel.setText("arrêté");
            this.getClientLabel().setText("");
        }
    }//GEN-LAST:event_arreterToggleButtonActionPerformed

    private void quitterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitterButtonActionPerformed
        if (this.getDemarrerServeur() != null)
            this.getDemarrerServeur().interrupt();
        System.exit(0);
    }//GEN-LAST:event_quitterButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeMySqlDB();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Serveur_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Serveur_Chat().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton arreterToggleButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JToggleButton demarrerToggleButton;
    private javax.swing.JLabel etatLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton quitterButton;
    // End of variables declaration//GEN-END:variables
}