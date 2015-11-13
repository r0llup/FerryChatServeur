/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ferrychatserveur;

import helpers.MD5Helper;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Sh1fT
 */

public final class Protocole {
    protected Serveur_Chat parent;
    protected List<String> nicks;

    /**
     * Creates new instance Protocole
     * @param parent
     */
    public Protocole(Serveur_Chat parent) {
        this.setParent(parent);
        this.setNicks(new LinkedList<String>());
    }

    public Serveur_Chat getParent() {
        return this.parent;
    }

    protected void setParent(Serveur_Chat parent) {
        this.parent = parent;
    }

    public List<String> getNicks() {
        return this.nicks;
    }

    protected void setNicks(List<String> nicks) {
        this.nicks = nicks;
    }

    /**
     * Démarrage de l'application : un comptable se faire reconnaître
     * @param name
     * @param password
     * @return Boolean
     */
    public boolean login(String name, String password) {
        try {
            CallableStatement psLogin = this.getParent().getMysqlConnection()
                .prepareCall(this.getParent().getLoginQuery());
            psLogin.setString(1, name);
            psLogin.setString(2, MD5Helper.getEncodedPassword(password));
            return psLogin.executeQuery().first();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.getParent(), ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}