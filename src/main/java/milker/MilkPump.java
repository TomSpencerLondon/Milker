package milker;

public class MilkPump {
  private PumpRegister pumpRegister;

  public void engage() {
    for (int speed=1; speed<=6; speed++) {
      setPumpSpeed(speed);
      sleep(500);
    }
    setPumpSpeed(7);
  }

  protected void setPumpSpeed(int speed) {
    pumpRegister.setSpeed(speed);
  }

  protected void sleep(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) { }
  }

  protected void setPumpRegister(PumpRegister pumpRegister) {
    this.pumpRegister = pumpRegister;
  }
}
