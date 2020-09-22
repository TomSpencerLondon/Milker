package milker;

public class Milker {
  private UdderSeal seal = new UdderSeal();
  private MilkPump pump = new MilkPump();
  private Gate gate = new CowGate();

  public void milk() {
    if (checkSeal()) {
      engagePump();
      waitSeconds(60);
    }
    gate.open();
  }

  protected void waitSeconds(int seconds) {
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
    }
  }

  protected void engagePump() {
    pump.engage();
  }

  protected boolean checkSeal() {
    return seal.check();
  }

  public void setGate(Gate gate) {
    this.gate = gate;
  }
}
