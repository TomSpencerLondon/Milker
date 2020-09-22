package controlPanel;

import milker.MilkerProcess;
import milker.MilkerProcessImpl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MilkerControlPanel extends JFrame {
  private Font font = new Font("Courrier New", Font.PLAIN, 18);
  private Border lineBorder = BorderFactory.createLineBorder(Color.black);
  private JPanel processMonitor = new JPanel();
  private JPanel waitingPanel = new JPanel(new GridLayout(2,1));
  private JPanel processingPanel = new JPanel();
  private JPanel milkedPanel = new JPanel(new GridLayout(2,1));
  private JPanel buttons = new JPanel();
  private JLabel cowsWaitingTitle = new JLabel();
  private JLabel cowsWaiting = new JLabel();
  private JLabel processing = new JLabel();
  private JLabel cowsMilkedTitle = new JLabel();
  private JLabel cowsMilked = new JLabel();
  private JButton startButton = new JButton();
  private JButton stopButton = new JButton();
  private MilkerControlPanelConnector connector;

  public MilkerControlPanel(MilkerControlPanelConnector connector) {
    this.connector = connector;
  }

  public static void main(String args[]) {
    MilkerProcess process = new MilkerProcessImpl();
    MilkerControlPanelConnector connector = new MilkerControlPanelConnector(process);
    new MilkerControlPanel(connector).start();
  }

  public void start() {
    setFonts();

    processMonitor.add(waitingPanel);
    processMonitor.add(processingPanel);
    processMonitor.add(milkedPanel);

    waitingPanel.setBorder(lineBorder);
    waitingPanel.add(cowsWaitingTitle);
    waitingPanel.add(cowsWaiting);

    processingPanel.add(processing);

    milkedPanel.setBorder(lineBorder);
    milkedPanel.add(cowsMilkedTitle);
    milkedPanel.add(cowsMilked);
    add(processMonitor);

    buttons.add(startButton);
    buttons.add(stopButton);
    add(buttons);

    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        connector.startButtonClicked();
      }
    });

    stopButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        connector.stopButton();
      }
    });

    setLayout(new FlowLayout());
    setSize(400, 150);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    loadControlPanelValues();
    setDynamicLabels();
    setStaticLabels();

    while (true) {
      sleep();
      connector.updateDynamicValues();
      setDynamicLabels();
    }
  }

  private void setFonts() {
    cowsWaitingTitle.setFont(font);
    cowsWaiting.setFont(font);
    cowsMilkedTitle.setFont(font);
    cowsMilked.setFont(font);
    processing.setFont(font);
    startButton.setFont(font);
    stopButton.setFont(font);
  }

  private void loadControlPanelValues() {
    connector.loadStaticValues();
    connector.updateDynamicValues();
  }

  private void setDynamicLabels() {
    processing.setText(connector.processingFlag);
    cowsWaiting.setText(connector.cowsWaiting);
    cowsMilked.setText(connector.cowsMilked);
  }

  private void setStaticLabels() {
    setTitle(connector.controlPanelTitle);
    cowsWaitingTitle.setText(connector.cowsWaitingTitle);
    cowsMilkedTitle.setText(connector.cowsMilkedTitle);
    startButton.setText(connector.startButtonText);
    stopButton.setText(connector.stopButtonText);
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
    }
  }
}
