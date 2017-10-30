import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Button {
    JPanel buttonPanel, textPanel, rightPanel, p, checkPanel;
    JButton b, enter, delete, clear, set;
    JCheckBox showPassword, shuffleButtons;
    private String buttonName, pin = "1111";
    public ArrayList<JButton> numButtons;
    JLabel text = new JLabel("");
    private String result = "", resultHide = "";
    List<Integer> nums = new ArrayList<>();
    private Boolean isPinCodeCorrect = false, isPasswordHide = true, isShuffle = false;

    public void display() {
        p = new JPanel(new BorderLayout());
        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        Font font = new Font("Verdana", Font.BOLD, 30);
        text = new JLabel();
        text.setFont(font);
        textPanel.add(text);
        textPanel.setBorder(BorderFactory.createTitledBorder("PIN CODE"));
        p.add(textPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridLayout(4,3));
        buttonPanel.setBorder(BorderFactory.createLineBorder (Color.black, 1));

        ButtonListener buttonListener = new ButtonListener();

        numButtons = new ArrayList();
        for(int i = 0; i < 10; i++){
            if (i < 9){
                buttonName = "" + (i + 1);
            }
            else if (i == 9)
                buttonName = "0";
            b = new JButton(buttonName);
            b.addActionListener (buttonListener);
            numButtons.add(b);
            buttonPanel.add(b);
            nums.add(i);
        }
        p.add(buttonPanel, BorderLayout.CENTER);
        rightPanel = new JPanel(new GridLayout(4,0));
        delete = new JButton("delete");
        rightPanel.add(delete);
        clear = new JButton("clear");
        rightPanel.add(clear);
        enter = new JButton("enter");
        rightPanel.add(enter);
        set = new JButton("set");
        rightPanel.add(set);

        RightPanelListener rightPanelListener = new RightPanelListener();
        delete.addActionListener (rightPanelListener);
        clear.addActionListener (rightPanelListener);
        enter.addActionListener (rightPanelListener);
        set.addActionListener (rightPanelListener);

        p.add(rightPanel, BorderLayout.EAST);

        checkPanel = new JPanel(new GridLayout(0,2));
        p.add(checkPanel, BorderLayout.SOUTH);
        showPassword = new JCheckBox("Show password");
        checkPanel.add(showPassword);
        shuffleButtons = new JCheckBox("Shuffle buttons");
        checkPanel.add(shuffleButtons);

        CheckBoxPanel checkBoxPanelListener = new CheckBoxPanel();
        showPassword.addItemListener (checkBoxPanelListener);
        shuffleButtons.addItemListener (checkBoxPanelListener);

        p.setVisible(true);
        rightPanel.setVisible(true);
        checkPanel.setVisible(true);
        textPanel.setVisible(true);
        buttonPanel.setVisible(true);
    }

    class CheckBoxPanel implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getItemSelectable();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (source == showPassword) {
                    text.setText(result);
                    isPasswordHide = false;
                }
                else if (source == shuffleButtons) {
                    isShuffle = true;
                }
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) {
                if (source == showPassword) {
                    text.setText(resultHide);
                    isPasswordHide = true;
                }
                else if (source == shuffleButtons) {
                    isShuffle = false;
                    for (int i = 0; i < 9 ; i++) {
                        numButtons.get(i).setText("" + (i+1));
                    }
                    numButtons.get(9).setText("" + 0);
                }
            }
        }
    }

    class RightPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent a) {
            Object source = a.getSource();
            if (source == delete) {
                if (result.isEmpty()) {
                    result = "";
                    resultHide = "";
                    text.setText("");
                }
                else {
                    result = result.substring(0, result.length() - 1);
                    resultHide = resultHide.substring(0, resultHide.length() - 1);
                    if (isPasswordHide)
                        text.setText(resultHide);
                    else
                        text.setText(result);
                }
            }
            else if (source == clear) {
                result = "";
                resultHide = "";
                text.setText("");
            }
            else if (source == enter) {
                if (result.equals(pin)) {
                    JOptionPane.showMessageDialog(null,
                            "pin code is correct");
                    isPinCodeCorrect = true;
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "pin code is incorrect");
                    isPinCodeCorrect = false;
                }
            }
            else if (source == set) {
                if (isPinCodeCorrect) {
                    if (result.length() < 4 || result.length() > 12)
                        JOptionPane.showMessageDialog(null,
                                "PIN code must be more than 4 and less than 12 numbers");
                    else {
                        pin = result;
                        isPinCodeCorrect = false;
                    }
                }
                else JOptionPane.showMessageDialog(null,
                        "you need to enter a PIN code before changing");
            }
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == numButtons.get(0))
                result += "" + numButtons.get(0).getText();
            else if (source == numButtons.get(1))
                result += "" + numButtons.get(1).getText();
            else if (source == numButtons.get(2))
                result += "" + numButtons.get(2).getText();
            else if (source == numButtons.get(3))
                result += "" + numButtons.get(3).getText();
            else if (source == numButtons.get(4))
                result += "" + numButtons.get(4).getText();
            else if (source == numButtons.get(5))
                result += "" + numButtons.get(5).getText();
            else if (source == numButtons.get(6))
                result += "" + numButtons.get(6).getText();
            else if (source == numButtons.get(7))
                result += "" + numButtons.get(7).getText();
            else if (source == numButtons.get(8))
                result += "" + numButtons.get(8).getText();
            else if (source == numButtons.get(9))
                result += "" + numButtons.get(9).getText();
            resultHide += "*";
            if (isPasswordHide)
                text.setText(resultHide);
            else
                text.setText(result);
            if (isShuffle) {
                Collections.shuffle(nums);
                for (int i = 0; i < numButtons.size(); i++) {
                    numButtons.get(i).setText("" + nums.get(i));
                }
            }
        }
    }
}

