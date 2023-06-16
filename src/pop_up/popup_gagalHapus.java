/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pop_up;


public class popup_gagalHapus extends javax.swing.JFrame {

    
    public popup_gagalHapus() {
        initComponents();
        setLocationRelativeTo(this);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_oke = new javax.swing.JLabel();
        lblX = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        btn_oke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_okeMouseClicked(evt);
            }
        });
        getContentPane().add(btn_oke);
        btn_oke.setBounds(200, 150, 110, 40);

        lblX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXMouseClicked(evt);
            }
        });
        getContentPane().add(lblX);
        lblX.setBounds(460, 10, 30, 30);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_gagalHapus.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 510, 240);

        setSize(new java.awt.Dimension(511, 243));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_okeMouseClicked

    private void lblXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblXMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(popup_gagalHapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popup_gagalHapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popup_gagalHapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popup_gagalHapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new popup_gagalHapus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel btn_oke;
    private javax.swing.JLabel lblX;
    // End of variables declaration//GEN-END:variables
}
