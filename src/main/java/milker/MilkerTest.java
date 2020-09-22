package milker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class MilkerTest implements Gate {
  private boolean pumping;
  private boolean opened = false;

  @Test
  public void milkLogic() throws Exception {
    Milker milker = new Milker() {
      protected void waitSeconds(int seconds) {
      }

      protected void engagePump() {
        pumping = true;
      }

      protected boolean checkSeal() {
        return true;
      }
    };

    milker.setGate(this);
    milker.milk();
    assertThat(pumping, is(true));
    assertThat(opened, is(true));
  }

  public void open() {
    opened = true;
  }

  public void close() {
  }
}
