package com.teamdev.calculator.desktopUI;

import com.teamdev.calculator.impl.Calculator;
import com.teamdev.calculator.impl.EvaluationException;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/*
    This class implements process of creating root window for application, menu, buttons and others elements of GUI.
 */
public class DesktopUI extends JFrame{
    private static final Logger logger = LoggerFactory.getLogger(DesktopUI.class);
    JTextField inputExpression;
    JPanel functionPanel;
    String memory;
    JFrame frame;

    public DesktopUI() throws ParseException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel()); //Set new Look and Feel from Synthetica library.
        createJFrame(); //Create root window.
        createMenu(); //Create root menu.
        JPanel rootPanel = createJPanel(Color.WHITE, null, 0, 0, 0, 0);
        inputExpression = new JTextField();
        inputExpression.setBounds(15, 10, 310, 80);
        inputExpression.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        inputExpression.addKeyListener(new PressEnter());
        rootPanel.add(inputExpression);
        frame.add(rootPanel);
        functionPanel = createJPanel(Color.WHITE, new GridLayout(2, 5, 5, 5), 15, 100, 310, 80);
        JPanel buttonPanel = createJPanel(Color.WHITE, new GridLayout(4, 5, 5, 5), 15, 190, 310, 250);
        createFunctionsButton(functionPanel);
        createButtons(buttonPanel);
        rootPanel.add(functionPanel);
        rootPanel.add(buttonPanel);
    }
    //This method create new root JFrame and set size, visibility, resizable, operation after closing Window options.
    public void createJFrame(){
        frame = new JFrame("Calculator.");
        frame.setSize(350, 520);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    //This method create Jpanel and set Bounds, background color, layout manager options.
    public JPanel createJPanel(Color backgroundColor, LayoutManager layoutManager, int x, int y, int width, int height){
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(layoutManager);
        if (x!=0 && y!=0 && width !=0 && height !=0){
            panel.setBounds(x, y, width, height);
        }
        return panel;
    }
    //This method create Menu bar and all menus, menu items.
    //Menu bar contain menus. Menus contain menu items.
    public void createMenu(){
        JMenuBar horizontalMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); horizontalMenu.add(fileMenu);
            JMenuItem exit = new JMenuItem("Exit");
                exit.addActionListener(new ButtonPressed());
                fileMenu.add(exit);
        JMenu editMenu = new JMenu("Edit"); horizontalMenu.add(editMenu);
            JMenuItem copyToClipboard = new JMenuItem("Copy");
                copyToClipboard.addActionListener(new ButtonPressed());
                editMenu.add(copyToClipboard);
            JMenuItem paste = new JMenuItem("Paste");
                paste.addActionListener(new ButtonPressed());
                editMenu.add(paste);
        JMenu themeMenu = new JMenu("Theme"); horizontalMenu.add(themeMenu);
            ButtonGroup buttonThemeGroup = new ButtonGroup();
            JMenuItem orangeThemeMenu = new JRadioButtonMenuItem("Orange theme");
                buttonThemeGroup.add(orangeThemeMenu);
                orangeThemeMenu.addActionListener(new ButtonPressed());
                themeMenu.add(orangeThemeMenu);
            JMenuItem blueThemeMenu = new JRadioButtonMenuItem("Blue theme");
                buttonThemeGroup.add(blueThemeMenu);
                blueThemeMenu.addActionListener(new ButtonPressed());
                themeMenu.add(blueThemeMenu);
        JMenu helpMenu = new JMenu("Help"); horizontalMenu.add(helpMenu);
            JMenuItem viewHelp = new JMenuItem("View help");
                viewHelp.addActionListener(new ButtonPressed());
                helpMenu.add(viewHelp);
        frame.setJMenuBar(horizontalMenu);
    }
    //This method create next buttons: 0 1 2 3 4 5 6 7 8 9 0 + - / * . , ^ = ( ).
    public void createButtons(JPanel panel){
        StringTokenizer stringTokenizer = new StringTokenizer("7 8 9 ( ) 4 5 6 * / 1 2 3 + - , 0 . ^ =");
        while (stringTokenizer.hasMoreTokens()){
            JButton button = new JButton(stringTokenizer.nextToken());
            button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
            button.addActionListener(new ButtonPressed());
            panel.add(button);
        }
    }
    //This method create buttons for functions: abs max min sum sqrt pi M+ M- MRC CE.
    public void createFunctionsButton(JPanel panel){
        StringTokenizer stringTokenizer = new StringTokenizer("abs M+ M- MRC CE max min sum sqrt pi");
        String[] toolTips = {
                "Return absolute value of number in brackets. abs(-10) = 10.",
                "Remember last result.",
                "Delete value from memory.",
                "Paste value from memory to expression.",
                "Clear expression.",
                "Return maximum value of numbers in brackets. max(10;30;20) = 30.",
                "Return minimum value of numbers in brackets. min(10;30;20) = 10.",
                "Return result of addition all numbers in brackets. sum(10;30;20) = 60.",
                "Return result of square root of numbers in bracket. sqrt(81) = 9;",
                "Return pi value. pi() = 3.1415926536"
        };
        int count = 0;
        while (stringTokenizer.hasMoreTokens()){
            JButton button = new JButton(stringTokenizer.nextToken());
            button.addActionListener(new ButtonPressed());
            button.setToolTipText(toolTips[count]);
            panel.add(button);
            count++;
        }
    }
    //This method set Look and Feel or catch exception.
    public void setLookAndFeel(LookAndFeel lookAndFeel){
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (UnsupportedLookAndFeelException exception) {
            logger.error("Unsupported Look and Feel exception.", exception);
        }
    }
    //This method create new Look and Feel. It's obtaining name of new Look and Feel and return new LookAndFeel object.
    public LookAndFeel createLookAndFeel(String lookAndFeelName){
        LookAndFeel lookAndFeel = null;
        try{
            switch (lookAndFeelName){
                case "Blue theme": lookAndFeel =  new SyntheticaBlueMoonLookAndFeel(); break;
                case "Orange theme": lookAndFeel =  new SyntheticaOrangeMetallicLookAndFeel(); break;
            }
        } catch (ParseException exception){
            logger.error("Parse Exception in Look And Feel.", exception);
        }
        return lookAndFeel;
    }
    //This inner class process of buttons and menu items presses event.
    private class ButtonPressed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            switch (event.getActionCommand()){
                case "Exit": System.exit(0); break;
                case "Copy":
                    StringSelection stringSelection = new StringSelection(inputExpression.getText());
                    clipboard.setContents(stringSelection, stringSelection); break;
                case "Paste":
                    try {
                        pasteStringInExpression((String) clipboard.getContents(this).getTransferData(DataFlavor.stringFlavor));
                    } catch (UnsupportedFlavorException exception) {
                        logger.error("Unsupported flavor exception.", exception);
                    } catch (IOException exception) {
                        logger.error("IO exception.", exception);
                    } break;
                case "Orange theme": setLookAndFeel(createLookAndFeel("Orange theme")); frame.repaint(); break;
                case "Blue theme": setLookAndFeel(createLookAndFeel("Blue theme")); frame.repaint(); break;
                case "View help": new HelpFrame();
                case "CE": inputExpression.setText(""); inputExpression.grabFocus(); break;
                case "=": calculate(); inputExpression.grabFocus(); break;
                case "M+":
                    memory = inputExpression.getText();
                    inputExpression.setText("");
                    inputExpression.grabFocus();
                    JButton button = (JButton)functionPanel.getComponent(1);
                    button.setEnabled(false); break;
                case "M-":
                    memory = "";
                    button = (JButton)functionPanel.getComponent(1);
                    button.setEnabled(true);
                    inputExpression.grabFocus(); break;
                case "MRC":
                    pasteStringInExpression(memory);
                    inputExpression.grabFocus(); break;
                default : {
                    if (event.getActionCommand().equals("min")
                            || event.getActionCommand().equals("max")
                            || event.getActionCommand().equals("sum")
                            || event.getActionCommand().equals("sqrt")
                            || event.getActionCommand().equals("abs")
                            || event.getActionCommand().equals("pi")){
                        pasteStringInExpression(event.getActionCommand()+"()");
                        if (event.getActionCommand().equals("pi")){
                            inputExpression.setCaretPosition(inputExpression.getCaretPosition());
                        }
                        else {
                        inputExpression.setCaretPosition(inputExpression.getCaretPosition()-1);
                        }
                        inputExpression.grabFocus();
                    }
                    else {
                        pasteStringInExpression(event.getActionCommand());
                        inputExpression.grabFocus();
                    }
                }
            }
        }
    }
    //This method need to returning caret to position between open and close bracket in function.
    //It paste copy of expression string after open bracket if press function buttons.
    public void pasteStringInExpression(String pasteString){
        String endOfExpression = "";
        if (inputExpression.getCaretPosition() != inputExpression.getText().length()){
            endOfExpression = inputExpression.getText().substring(inputExpression.getCaretPosition());
        }
        try {
            inputExpression.setText(inputExpression.getText(0, inputExpression.getCaretPosition())
                    + pasteString + endOfExpression);
            inputExpression.setCaretPosition(inputExpression.getCaretPosition() - endOfExpression.length());

        } catch (BadLocationException exception) {
            logger.error("Bad location exception.", exception);
        }
    }
    //This method create new Calculator object and call it method evaluate.
    //If expression have errors - method catch they and logging this event to console and logging.log file.
    public void calculate(){
        Calculator calculator = new Calculator();
        try {
            logger.info("Input expression: {}.", inputExpression.getText());
            BigDecimal result = calculator.evaluate(inputExpression.getText());
            if (result.equals(new BigDecimal(0).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros()))
                result = new BigDecimal(0);
            logger.info("Result of expression: {} = {}", inputExpression.getText(), result);
            inputExpression.setText(Double.toString(result.doubleValue()));

        }
        catch (EvaluationException exception) {
            String possibleStateString = exception.getPossibleStateString();
            JOptionPane.showMessageDialog(frame, exception.getMessage() + " in position: " + exception.getErrorPosition()
                    + ".\nMissed " + possibleStateString, "Evaluation Error.", JOptionPane.ERROR_MESSAGE);
            logger.error("Evaluation exception. {}.\nMissed: {}", exception.getMessage(), possibleStateString, exception);
            inputExpression.setCaretPosition(exception.getErrorPosition() - 1);
            inputExpression.grabFocus();
        }
        catch (NoSuchElementException exception){
            JOptionPane.showMessageDialog(frame, exception.getMessage(), "Invalid operator count error.", JOptionPane.ERROR_MESSAGE);
            logger.error("Invalid operator count exception. {}", exception);
        }
        catch (IllegalArgumentException exception){
            JOptionPane.showMessageDialog(frame, exception.getMessage(), "Division by zero error.", JOptionPane.ERROR_MESSAGE);
            logger.error("Division by zero error. {}", exception);
        }
    }
    //This inner class need to process of presses Enter button.
    private class PressEnter implements KeyListener{

        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER){
                calculate();
            }
        }

        @Override
        public void keyTyped(KeyEvent event) {
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
    }
    //This inner class create help frame.
    public class HelpFrame{
        JEditorPane helpPane;
        String helpString;
        PressLink pressLink;

        public HelpFrame(){
            JFrame helpFrame = new JFrame("Calculator help");
            pressLink = new PressLink();
            helpFrame.setVisible(true);
            helpFrame.setBounds(frame.getX()+frame.getWidth()+10, frame.getY(), 350, 520);
            helpFrame.setResizable(false);
            JPanel helpPanel = createJPanel(Color.WHITE, null, 0, 0, 0, 0);
            helpFrame.add(helpPanel);
            helpString = "";
            try {
                File helpFile = new File("CalculatorHelp.htm");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(helpFile));
                String str;
                while ((str = bufferedReader.readLine()) != null){
                    helpString = helpString + str;
                }
                bufferedReader.close();
            } catch (IOException exception){
                logger.error("IO Ecxeption.", exception);
            }
            helpPane = new JEditorPane("text/html", helpString);
            removeTags();
            helpPane.setCaretPosition(0);
            JScrollPane scrollPane = new JScrollPane(helpPane);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBounds(0, 0, 342, 475);
            scrollPane.setWheelScrollingEnabled(true);
            helpPanel.add(scrollPane);
            helpPane.setAutoscrolls(true);
            helpPane.addMouseListener(pressLink);
            helpPane.addMouseMotionListener(pressLink);
            helpPane.setEditable(false);
            helpPane.setBackground(Color.WHITE);
        }
        //This method removes all HTML tags from help file.
        private void removeTags(){
            char[] chars = helpString.toCharArray();
            char[] outputChars = new char[chars.length];
            int count = 0;
            for (int i=0; i<chars.length; i++){
                if (chars[i] == '<'){
                    if (chars[i+1] != 'a'){
                        while (chars[i] != '>'){
                            i++;
                        }
                    } else {
                        if (chars[i+3] == 'h'){
                            while (chars[i] != '>'){
                                i++;
                            }
                        } else {
                            while (chars[i+1] != '"'){
                                i++;
                            }
                        }
                    }
                } else {
                    outputChars[count] = chars[i];
                    count++;
                }
            }
            helpString = String.copyValueOf(outputChars);
        }
        //This inner class need to process of Hyperlink click event.
        //When user click to hyperlink process of mouse pressed add new HyperlinkListener.
        //When document go for hyperlink or user move mouse, HyperlinkListener is removing.
        private class PressLink implements HyperlinkListener, MouseListener, MouseMotionListener{

            @Override
            public void hyperlinkUpdate(HyperlinkEvent event) {
                String link = event.getDescription().substring(1);
                link = "\"" + link + "\"";
                helpPane.setCaretPosition(helpString.indexOf(link));
                helpPane.removeHyperlinkListener(pressLink);
            }

            @Override
            public void mousePressed(MouseEvent event) {
                helpPane.addHyperlinkListener(pressLink);
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                helpPane.removeHyperlinkListener(pressLink);
            }

            @Override
            public void mouseClicked(MouseEvent event) {
            }

            @Override
            public void mouseReleased(MouseEvent event) {
            }

            @Override
            public void mouseEntered(MouseEvent event) {
            }

            @Override
            public void mouseExited(MouseEvent event) {
            }

            @Override
            public void mouseDragged(MouseEvent event) {
            }
        }
    }
}
