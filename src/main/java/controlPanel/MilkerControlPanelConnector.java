package controlPanel;

import milker.MilkerProcess;

public class MilkerControlPanelConnector {
  private MilkerProcess process;

  public MilkerControlPanelConnector(MilkerProcess process) {
    this.process = process;
  }

  //static values
  public String controlPanelTitle;
  public String cowsWaitingTitle;
  public String cowsMilkedTitle;
  public String startButtonText;
  public String stopButtonText;

  //dynamic values
  public String cowsWaiting;
  public String processingFlag;
  public String cowsMilked;

  public void startButtonClicked() {
    process.start();
  }

  public void stopButton() {
    process.stop();
  }

  public void loadStaticValues() {
    process.loadStaticValues(this);
  }

  public void updateDynamicValues() {
    process.updateDynamicValues(this);
  }
}
