package civitas.celestis;

import civitas.celestis.geometry.profile.SphericalGeometry;
import civitas.celestis.gui.component.viewport.Viewport;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.RealisticObject;
import civitas.celestis.object.TangibleObject;
import civitas.celestis.world.RealisticWorld;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

public class OdysseyTest {
    public static void main(@Nonnull String[] args) {
        Odyssey.start();

        final World world = new RealisticWorld(
                UUID.randomUUID(),
                "TestWorld",
                new Vector3(0, -9.807, 0),
                1.225
        );

        final TangibleObject o1 = new RealisticObject(
                UUID.randomUUID(),
                new Vector3(0, 0, 100),
                1,
                new SphericalGeometry(10)
        );

        o1.setRotationRate(new Rotation(Vector3.POSITIVE_Y, Math.toRadians(90)));

        world.addObject(o1);
        Odyssey.getWorldManager().addWorld(world);

        final JFrame frame = new JFrame("Test");
        final Viewport viewport = new Viewport(world);

        frame.add(viewport);

        frame.setSize(1280, 720);
        frame.setVisible(true);

        Odyssey.getScheduler().registerTask(delta -> viewport.renderAndRepaint());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Odyssey.stop();
            }
        });

    }
}
