package milker;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;


public class MilkPumpTest {
  String actions = "";

  @Test
  public void testPumpRampUp() throws Exception {
    MilkPump pump = new MilkPump() {
      protected void sleep(int milliseconds) {
        actions += "s";
      }

      protected void setPumpSpeed(int speed) {
        actions += speed;
      }
    };

    pump.setPumpRegister(new PumpRegisterDummy());
    pump.engage();
    assertThat(actions, is("1s2s3s4s5s6s7"));
  }
}
