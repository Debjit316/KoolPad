import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Jpad extends JFrame implements ActionListener{

    JTextArea area;
    String text;

    public JTextArea getTextArea(){
        return area;
    }

    // Swing's built in library to manage undo and redo functionalities
    UndoManager undoManager;

    Jpad(){

        setTitle("KoolPad");

        ImageIcon padIcon = new ImageIcon((ClassLoader.getSystemResource("Jpad.png")));
        Image icon = padIcon.getImage();
        setIconImage(icon);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.lightGray);

/////////////////////////////////////////////////////////////////////////////////////////////////////////
        JMenu file = new JMenu("File");
        file.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));

        JMenuItem newDoc = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        menuBar.add(file);

/////////////////////////////////////////////////////////////////////
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        menuBar.add(edit);

/////////////////////////////////////////////////////////////
        JMenu task = new JMenu("Task");
        task.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));

        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        JMenuItem redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

        task.add(undo);
        task.add(redo);

        menuBar.add(task);

///////////////////////////////////////////////////////////////////////////////////////////////

        JMenu format = new JMenu("Format");
        format.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));

        JMenu align = new JMenu("Align Text");

        JMenuItem alignleft = new JMenuItem("Left");
        alignleft.addActionListener(this);
        align.add(alignleft);

        JMenuItem alignright = new JMenuItem("Right");
        alignright.addActionListener(this);
        align.add(alignright);

        //redo.addActionListener(this);

        JMenuItem fontformat = new JMenuItem("Font");
        fontformat.addActionListener(this);


        //format.add(wordwrap);
        format.add(align);
        format.add(fontformat);

        menuBar.add(format);

/////////////////////////////////////////////////////////////////////////////////////////
        JMenu helpmenu = new JMenu("Help");
        helpmenu.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));

        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));

        helpmenu.add(help);
        menuBar.add(helpmenu);

////////////////////////////////////////////////////////////////////////////////////////////////////

        setJMenuBar(menuBar);

        undoManager = new UndoManager();

        area = new JTextArea();
        area.getDocument().addUndoableEditListener(new UndoableEditListener(){
            @Override
            public void undoableEditHappened(UndoableEditEvent e){
                undoManager.addEdit(e.getEdit());
            }
        });
        area.setFont(new Font("AERIEL", Font.PLAIN, 14 ));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(pane);

        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        // changes made above will be reflected by the setVisible function
        setVisible(true);
    }

    // Method Overriding
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")) {
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
            chooser.addChoosableFileFilter(restrict);

            int action=chooser.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }

            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save")){

            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            saveas.setDialogTitle("Save");

            int action=saveas.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename =new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outfile;
            try{
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }
            catch(Exception e){
                System.out.println("Something Wrong");
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }
            catch(Exception e){
                System.out.println("Something Wrong");
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All")){
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("Undo")){
            if(undoManager.canUndo()){
                undoManager.undo();
            }
        }
        else if(ae.getActionCommand().equals("Redo")){
            if(undoManager.canRedo()){
                undoManager.redo();
            }
        }
        else if(ae.getActionCommand().equals("Left")){
            area.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        else if(ae.getActionCommand().equals("Right")){
            area.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        else if(ae.getActionCommand().equals("About")){
                new About().setVisible(true);
        }
        else if(ae.getActionCommand().equals("Font")){
            //launch font menu
            new FontMenu(Jpad.this).setVisible(true);
        }
    }

    public static void main(String []args){

        new Jpad();
    }
}
