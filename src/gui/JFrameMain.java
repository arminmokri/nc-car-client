package gui;

import config.Config;
import global.GlobalVariable;
import java.awt.Toolkit;

public class JFrameMain extends javax.swing.JFrame {

    public JFrameMain(String args[]) {
        initComponents();
        start(args);
    }

    private void start(String[] args) {

        try {
            GlobalVariable.config = new Config(args);
            SetSize();
            update();
            OnConnect();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void update() {
        try {

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void SetSize() {
        setSize(880, 600);
        setLocationCenter();
    }

    private void setLocationCenter() {
        setLocation(x(), y());
    }

    private int x() {
        int w = this.getSize().width;
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - w) / 2;
        return x;
    }

    private int y() {
        int h = this.getSize().height;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - h) / 2;
        return y;
    }

    public void OnConnect() {
        HiddenAll();
        jPanelConnect.start();
        jPanelConnect.setVisible(true);
    }

    public void OnSignIn() {
        HiddenAll();
        jPanelSignIn.start();
        jPanelSignIn.setVisible(true);
    }

    public void OnMain() {
        HiddenAll();
        jPanelMain.start();
        jPanelMain.setVisible(true);
    }

    private void HiddenAll() {
        jPanelConnect.setVisible(false);
        jPanelSignIn.setVisible(false);
        jPanelMain.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelConnect = new gui.JPanelConnect();
        jPanelSignIn = new gui.JPanelSignIn();
        jPanelMain = new gui.JPanelMain();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("nc-car-control");
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.CardLayout());
        jPanel1.add(jPanelConnect, "card2");
        jPanel1.add(jPanelSignIn, "card3");
        jPanel1.add(jPanelMain, "card4");

        getContentPane().add(jPanel1, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (javax.swing.UIManager.getSystemLookAndFeelClassName().equals(info.getClassName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                GlobalVariable.jFrameMain = new JFrameMain(args);
                GlobalVariable.jFrameMain.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private gui.JPanelConnect jPanelConnect;
    private gui.JPanelMain jPanelMain;
    private gui.JPanelSignIn jPanelSignIn;
    // End of variables declaration//GEN-END:variables

}
