
package simko_fiks;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class splashs extends javax.swing.JFrame {
Timer timer;
ActionListener action;
splashs sp;


    public splashs() {
        initComponents();
        setLocationRelativeTo(this);
        aksi();
        timer = new Timer(100, action);
        timer.start();
        panel1.setVisible(false);
        panel1.setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splasscreen = new javax.swing.JProgressBar();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_splash/btn mulai normal.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        panel1.add(jLabel2);

        getContentPane().add(panel1);
        panel1.setBounds(790, 790, 340, 120);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nahi iki sangat fiks.gif"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 1080);

        setSize(new java.awt.Dimension(1918, 1071));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setVisible(false);
        new frmlogin1().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(splashs.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_splash/btn mulai entered.png")));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_splash/btn mulai normal.png")));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_splash/btn mulai press.png")));
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_splash/btn mulai entered.png")));
    }//GEN-LAST:event_jLabel2MouseReleased

    public static void main(String args[]) {
        
        
        
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splashs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panel1;
    private javax.swing.JProgressBar splasscreen;
    // End of variables declaration//GEN-END:variables

    private void aksi() {
        action =new ActionListener(){
            
        public void actionPerformed(ActionEvent e){
            splasscreen.setValue(splasscreen.getValue() + 3);
            splasscreen.setStringPainted(true);
            if(splasscreen.getPercentComplete()==1.0){
                timer.stop();
            panel1.setVisible(true);
            }
        }    
        };
                }
}
