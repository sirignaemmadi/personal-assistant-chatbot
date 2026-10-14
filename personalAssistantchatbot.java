import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PersonalAssistantChatbot extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;

    public PersonalAssistantChatbot() {
        setTitle("Personal Assistant Chatbot");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 244, 249));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel heading = new JLabel("Personal Assistant Chatbot");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(35, 65, 110));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setText(
            "Assistant: Hello! Welcome to your Personal Assistant Chatbot.\n" +
            "Assistant: Type 'help' to see what I can do.\n\n"
        );

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 15));
        sendButton.setBackground(new Color(35, 100, 180));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(heading, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        chatArea.append("You: " + message + "\n");
        inputField.setText("");

        String reply = getReply(message);
        chatArea.append("Assistant: " + reply + "\n\n");
    }

    private String getReply(String message) {
        String text = message.toLowerCase();

        if (text.contains("hello") || text.contains("hi")) {
            return "Hello! How can I help you today?";
        } else if (text.contains("your name")) {
            return "I am your Personal Assistant Chatbot.";
        } else if (text.contains("time")) {
            return "The current time is " +
                java.time.LocalTime.now()
                    .withNano(0).toString() + ".";
        } else if (text.contains("date")) {
            return "Today's date is " +
                java.time.LocalDate.now() + ".";
        } else if (text.contains("help")) {
            return "I can greet you, tell you the current date and time, " +
                "and respond to simple messages.";
        } else if (text.contains("bye")) {
            return "Goodbye! Have a nice day.";
        } else {
            return "I received your message. More assistant features " +
                "can be connected to the backend later.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PersonalAssistantChatbot().setVisible(true);
        });
    }
}