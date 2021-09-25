package frame;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.SocketCommu;

public class FrameMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameMain frame = new FrameMain();
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
    public FrameMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSendMessage = new JButton("Send Message");
        btnSendMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SendMessage Frame_send_message = new SendMessage();
            }
        });

        btnSendMessage.setBounds(23, 10, 105, 23);
        contentPane.add(btnSendMessage);

        JButton btnDownload = new JButton("Download Pic");
        btnDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // download picture
                String baseUrl = "https://web-ace.jp/img/youngaceup/contents/1000010/comic/";
                String picPre = "emiyasan_";
                String picApp = ".jpg";
                for (int i = 1; i < 2; i++) {
                    for (int j = 1; j <= 15; j++) {
                        String url = baseUrl + picPre
                                + SocketCommu.GetThreeString(i) + "_"
                                + SocketCommu.GetThreeString(j) + picApp;
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    SocketCommu.DownloadPicture(url);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }).start();
                        // SocketCommu.DownloadPictureByFileUtils(url);
                    }
                }
                JOptionPane.showConfirmDialog(null, "download has finished!");
            }
        });

        btnDownload.setBounds(23, 43, 105, 23);
        contentPane.add(btnDownload);
        
        JButton btnMethodTest = new JButton("Class Method");
        btnMethodTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClassMethod test = new ClassMethod();
                test.setVisible(true);
            }
        });
        btnMethodTest.setBounds(23, 76, 105, 23);
        contentPane.add(btnMethodTest);
        setVisible(true);
    }
}
