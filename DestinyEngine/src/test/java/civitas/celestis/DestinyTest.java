package civitas.celestis;

import civitas.celestis.test.TestEvent;
import civitas.celestis.test.TestListener;

import javax.annotation.Nonnull;

public class DestinyTest {
    public static void main(@Nonnull String[] args) {
        DestinyEngine.start();

        DestinyEngine.getEventManager().registerListener(new TestListener());
        DestinyEngine.getEventManager().call(new TestEvent());

        DestinyEngine.getScheduler().registerTask(delta -> System.out.println("Hi world!"));
    }
}
