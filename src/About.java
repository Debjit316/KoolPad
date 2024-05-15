import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener {

    About()
    {
        setTitle("About");

        ImageIcon padIcon = new ImageIcon((ClassLoader.getSystemResource("Jpad.png")));
        Image i = padIcon.getImage();
        setIconImage(i);

        setBounds(400, 140, 600, 500);
        // setting default Layout to null
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Sign.png"));
        Image i2 =i1.getImage().getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);

        // Creating i3 as Image belongs to awt package, whereas JLabel belongs to Swing package.
        // Therefore, i2 is converted to ImageIcon variable, and passed into JLabel as parameter.

        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon =new JLabel(i3);

        // Defining my own layout
        headerIcon.setBounds(40,30,500,150);

        add(headerIcon);


        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("Jpad.png"));
        Image i5 =i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);

        // Creating i3 as Image belongs to awt package, whereas JLabel belongs to Swing package.
        // Therefore, i2 is converted to ImageIcon variable, and passed into JLabel as parameter.

        ImageIcon i6 = new ImageIcon(i5);
        JLabel Icon =new JLabel(i6);

        // Defining my own layout
        Icon.setBounds(50,210,70,70);
        add(Icon);

        JLabel text = new JLabel ("<html>Bruce Wayne<br>KoolPad Version:- 1.0.0 <br>Build: Java Swing <br>Wayne Enterprises Ltd.</html>");
        text.setBounds(150,100,500,300);
        text.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        add(text);

        JButton b1 = new JButton(" Understood ");
        b1.setBounds(350,350,130,25);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);

    }

    public static void main(String[] args)
    {
     new About();
    }

}
