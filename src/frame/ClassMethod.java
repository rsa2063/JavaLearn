package frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import utils.ClassMethodTest;

public class ClassMethod extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputString;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassMethod frame = new ClassMethod();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ClassMethod() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 520, 391);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnStringFormat = new JButton("String.format");
        btnStringFormat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                utils.ClassMethodTest test = new utils.ClassMethodTest();
                test.TestStringFormat();
            }
        });
        btnStringFormat.setBounds(10, 10, 132, 23);
        contentPane.add(btnStringFormat);
        
        inputString = new JTextField();
        inputString.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClassMethodTest.log("action performed");
                inputString.setText(inputString.getText().replace(" ", ""));
            }
        });

//        inputString.addInputMethodListener(new InputMethodListener() {
//            public void caretPositionChanged(InputMethodEvent event) {
//            }
//            public void inputMethodTextChanged(InputMethodEvent event) {
//                inputString.setText(inputString.getText().replace(" ", ""));
//                ClassMethodTest.log("changed");
//            }
//        });
        Document doc = inputString.getDocument();
        doc.addDocumentListener(new DocumentListener() {
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                ClassMethodTest.log("removed");
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                Document d = e.getDocument();
                try {
                    ClassMethodTest.log("inserted:" + d.getText(0, d.getLength()));
                    ClassMethodTest.log("inserted:" + d.getText(0, d.getLength()).replace(" ", ""));
                    inputString.setText(d.getText(0, d.getLength()).replace(" ", ""));
                } catch (Exception ex) {
                    
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                ClassMethodTest.log("changed");
                try {
                    inputString.setText(d.getText(0, d.getLength()));
                } catch (Exception ex) {
                    
                }
            }
        });
        inputString.setBounds(10, 43, 353, 236);
        contentPane.add(inputString);
        inputString.setColumns(10);
        
        JButton btnRemoveSpace = new JButton("Remove Space");
        btnRemoveSpace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inputString.setText(inputString.getText().replace(" ", ""));
            }
        });
        
        btnRemoveSpace.setBounds(173, 10, 122, 23);
        contentPane.add(btnRemoveSpace);
        
        JButton btnLambdaTest = new JButton("Lambda Test");
        btnLambdaTest.addActionListener((e) -> {
            
        });
        
        btnLambdaTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClassMethodTest.lambdaTest();
                Runnable r = () -> {
                    System.out.println("lambda test");
                };
                r.run();
            }
        });
        btnLambdaTest.setBounds(316, 10, 99, 23);
        contentPane.add(btnLambdaTest);
    }
}
