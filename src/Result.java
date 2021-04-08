/*
 * Decompiled with CFR 0_123.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JLabel;

public class Result
extends Frame {
    public final int width = 300;
    public final int height = 60;
    private JLabel lblA;
    private JLabel lblQ;
    private JLabel lblQuery;

    public Result() {
        this.initComponents();
    }

    public JLabel getLblA() {
        return this.lblA;
    }

    public void setLblA(JLabel lblA) {
        this.lblA = lblA;
    }

    public JLabel getLblQ() {
        return this.lblQ;
    }

    public void setLblQ(JLabel lblQ) {
        this.lblQ = lblQ;
    }

    public JLabel getLblQuery() {
        return this.lblQuery;
    }

    public void setLblQuery(JLabel lblQuery) {
        this.lblQuery = lblQuery;
    }

    private void initComponents() {
        this.lblQuery = new JLabel();
        this.lblA = new JLabel();
        this.lblQ = new JLabel();
        this.setAlwaysOnTop(true);
        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.setBackground(new Color(240, 240, 240));
        this.setBounds(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200, 300, 60));
        this.setExtendedState(0);
        this.setLocation(new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200));
        this.setMaximizedBounds(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200, 300, 60));
        this.setMaximumSize(new Dimension(300, 60));
        this.setMinimumSize(new Dimension(300, 60));
        this.setUndecorated(true);
        this.setOpacity(0.3f);
        this.setPreferredSize(new Dimension(300, 60));
        this.setSize(new Dimension(300, 60));
        this.setState(0);
        this.setType(Window.Type.UTILITY);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                Result.this.exitForm(evt);
            }
        });
        this.lblQuery.setPreferredSize(new Dimension(300, 20));
        this.add((Component)this.lblQuery, "North");
        this.lblA.setPreferredSize(new Dimension(300, 20));
        this.add((Component)this.lblA, "South");
        this.lblQ.setPreferredSize(new Dimension(300, 20));
        this.add((Component)this.lblQ, "Center");
        this.pack();
    }

    private void exitForm(WindowEvent evt) {
        System.exit(0);
    }

    public void refresh() {
        this.dispose();
        this.lblQuery.setText("");
        this.lblA.setText("");
        this.lblQ.setText("");
        this.setAlwaysOnTop(true);
        this.setBackground(new Color(240, 240, 240));
        this.setBounds(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200, 300, 60));
        this.setExtendedState(0);
        this.setLocation(new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200));
        this.setMaximizedBounds(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 150, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() -200, 300, 60));
        this.setMaximumSize(new Dimension(300, 60));
        this.setMinimumSize(new Dimension(300, 60));
        this.setUndecorated(true);
        this.setOpacity(0.4f);
        this.setPreferredSize(new Dimension(300, 60));
        this.setSize(new Dimension(300, 60));
        this.setState(0);
        this.setType(Window.Type.UTILITY);
        this.lblQuery.setPreferredSize(new Dimension(300, 20));
        this.lblA.setPreferredSize(new Dimension(300, 20));
        this.lblQ.setPreferredSize(new Dimension(300, 20));
        this.pack();
    }

}

