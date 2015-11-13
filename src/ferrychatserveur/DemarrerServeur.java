/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ferrychatserveur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Sh1fT
 */

public final class DemarrerServeur extends Thread {
    protected Serveur_Chat parent;

    /**
     * Creates new instance DemarrerServeur
     * @param parent 
     */
    public DemarrerServeur(Serveur_Chat parent) {
        this.setParent(parent);
    }

    public Serveur_Chat getParent() {
        return this.parent;
    }

    protected void setParent(Serveur_Chat parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        try {
            this.getParent().setSSocket(new ServerSocket(this.getParent().getServerPort()));
            while(!this.isInterrupted()) {
                this.getParent().setCSocket(this.getParent().getSSocket().accept());
                this.getParent().getClientLabel().setText(
                    this.getParent().getCSocket().getInetAddress().getHostAddress());
                DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(this.getParent().getCSocket().getInputStream()));
                DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(this.getParent().getCSocket().getOutputStream()));
                StringBuilder info = new StringBuilder();
                info.setLength(0);
                byte b = 0;
                while ((b=dis.readByte()) != (byte) '\n') {
                    if (b != '\n')
                        info.append((char) b);
                }
                StringTokenizer st  = new StringTokenizer(info.toString(), ";");
                String commande = st.nextToken();
                String message = null;
                if (commande.compareTo("LOGIN") == 0) {
                    String username = st.nextToken();
                    String password = st.nextToken();
                    if (this.getParent().getProtocole().login(username, password)) {
                        message = this.getParent().getGroupHost() + ":" +
                            this.getParent().getGroupPort().toString();
                        this.getParent().getProtocole().getNicks().add(username);
                    } else
                        message = "ko";
                    dos.write((message + "\n").getBytes());
                } else if (commande.compareTo("GETNICKS") == 0) {
                    String nicks = "";
                    for (String nick : this.getParent().getProtocole().getNicks())
                        nicks += nick + ":";
                    dos.write((nicks + "\n").getBytes());
                } else if (commande.compareTo("GETNICK") == 0) {
                    String nick = st.nextToken();
                    dos.write((String.valueOf(this.getParent().getProtocole().
                        getNicks().contains(nick)) + "\n").getBytes());
                } else if (commande.compareTo("DELNICK") == 0) {
                    String nick = st.nextToken();
                    this.getParent().getProtocole().getNicks().remove(nick);
                }
                dos.flush();
                dos.close();
                dis.close();
                this.getParent().getCSocket().close();
                this.getParent().getClientLabel().setText("aucun");
            }
            this.getParent().getSSocket().close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getParent(), ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}