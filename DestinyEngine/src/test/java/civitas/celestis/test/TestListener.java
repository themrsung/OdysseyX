package civitas.celestis.test;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;

import javax.annotation.Nonnull;

public class TestListener implements Listener {
    @EventHandler(priority = EventHandler.Priority.MONITOR)
    public void onTestEvent(@Nonnull TestEvent event) {
        System.out.println("Hello world!");
    }
}
