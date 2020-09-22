package milker;

import controlPanel.MilkerControlPanelConnector;

public interface MilkerProcess {
  public void start();
  public void stop();
  void loadStaticValues(MilkerControlPanelConnector connector);
  void updateDynamicValues(MilkerControlPanelConnector connector);
}
