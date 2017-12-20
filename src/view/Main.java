package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame implements WindowListener{
    JLabel label;

    public Main(){

        super("TaTao Talk");

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.yellow);
        contentPane.setLayout(null);

        JButton button = new JButton("start");
        button.setBounds(50, 50, 100, 100);
        button.setBorder(new LineBorder(Color.black, 2));

        button.addActionListener((ActionEvent e) -> {
           new Login();
           super.dispose();
        });
        contentPane.add(button);

        //--------------------------------------------------
        label = new JLabel("---");
        label.setBounds(0,0,200,30);
        contentPane.add(label);
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                label.setText("x: "+x+ "  y: "+y);
            }
        });
        //--------------------------------------------------

        setBounds(100, 100, 375, 667);
        setResizable(false);
        setVisible(true);
        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
