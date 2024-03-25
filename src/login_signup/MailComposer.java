package login_signup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MailComposer extends JFrame {
    private JTextField recipientField;
    private JTextField subjectField;
    private JTextArea messageArea;

    public MailComposer() {
        setTitle("Compose Mail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setSize(800, 600); 
        setVisible(true);
    }

    private void initComponents() {
        setBackground(new Color(255, 255, 255));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel inboxLabel = new JLabel("Inbox");
        inboxLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(inboxLabel, BorderLayout.NORTH);
        JPanel receivedMailsPanel = new JPanel();
        leftPanel.add(new JScrollPane(receivedMailsPanel), BorderLayout.CENTER);

        JPanel composePanel = new JPanel(new BorderLayout());
        composePanel.setBackground(new Color(255, 255, 255));

       
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(new Color(255, 87, 51)); 
        JLabel topicLabel = new JLabel("New Mail");
        topicLabel.setForeground(Color.WHITE); 
        topicPanel.add(topicLabel);

        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); 

        JLabel recipientLabel = new JLabel("Recipient:");
        inputPanel.add(recipientLabel, gbc);

        gbc.gridy++;
        JLabel subjectLabel = new JLabel("Subject:");
        inputPanel.add(subjectLabel, gbc);

        gbc.gridy++;
        JLabel messageLabel = new JLabel("Message:");
        inputPanel.add(messageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        recipientField = new JTextField();
        recipientField.setPreferredSize(new Dimension(200, 20));
        inputPanel.add(recipientField, gbc);

        gbc.gridy++;
        subjectField = new JTextField();
        subjectField.setPreferredSize(new Dimension(200, 20));
        inputPanel.add(subjectField, gbc);

        gbc.gridy++;
        gbc.weighty = 1.0; 
        messageArea = new JTextArea();
        messageArea.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(400, 200)); 
        inputPanel.add(scrollPane, gbc);

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(255, 87, 51));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.setBorder(BorderFactory.createBevelBorder(10));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMail();
            }
        });

        
        composePanel.add(topicPanel, BorderLayout.NORTH);
        composePanel.add(inputPanel, BorderLayout.CENTER);
        composePanel.add(sendButton, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, composePanel);
        splitPane.setResizeWeight(0.5);

        add(splitPane);
    }


    private void sendMail() {
        String recipient = recipientField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        System.out.println("Recipient: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        recipientField.setText("");
        subjectField.setText("");
        messageArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MailComposer();
            }
        });
    }
}
//