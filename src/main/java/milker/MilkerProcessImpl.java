package milker;

import controlPanel.MilkerControlPanelConnector;

public class MilkerProcessImpl implements MilkerProcess {
  private boolean started = false;
  private int cowsMilked = 0;
  private int cowsWaiting = 100;

  public void start() {
    started = true;
  }

  public void stop() {
    started = false;
  }

  public void loadStaticValues(MilkerControlPanelConnector connector) {
    connector.controlPanelTitle = "Milker Control Panel";
    connector.cowsWaitingTitle = "Cows Waiting";
    connector.cowsMilkedTitle = "Cows Milked";
    connector.startButtonText = "Start";
    connector.stopButtonText = "Stop";
  }

  public void updateDynamicValues(MilkerControlPanelConnector connector) {
    connector.cowsWaiting = "" + (started ? cowsWaiting-- : cowsWaiting);
    connector.processingFlag = started ? "...processing..." : "STOPPED";
    connector.cowsMilked = "" + (started ? cowsMilked++ : cowsMilked);
  }
}

