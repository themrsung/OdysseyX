package civitas.celestis.gui.component.viewport;

import civitas.celestis.geometry.vertex.ColoredVertex3;
import civitas.celestis.graphics.PolygonX;
import civitas.celestis.graphics.Scene;
import civitas.celestis.math.Numbers3;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.TangibleObject;
import civitas.celestis.world.World;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>Viewport</h2>
 * <p>Renders a world to a scene, then projects it to the screen.</p>
 */
public class Viewport extends JPanel {
    /**
     * Creates a new viewport.
     * @param layout Layout manager object
     * @param world World to render
     */
    public Viewport(@Nonnull LayoutManager layout, @Nonnull World world) {
        super(layout, true);

        this.scene = new Scene();
        this.world = world;
        this.origin = Vector3.ZERO;
        this.angle = Rotation.NO_ROTATION;
    }

    /**
     * Creates a new viewport.
     * @param world World to render
     */
    public Viewport(@Nonnull World world) {
        super(true);

        this.scene = new Scene();
        this.world = world;
        this.origin = Vector3.ZERO;
        this.angle = Rotation.NO_ROTATION;
    }

    /**
     * Renders the scene, then repaints the viewport.
     */
    public void renderAndRepaint() {
        render();
        repaint();
    }

    /**
     * Renders the scene.
     */
    public void render() {
        // Prevent modification of scene while painting
        if (painting) return;

        // Clear scene
        scene.clear();

        // Add object vertices
        world.getObjects().stream()
                .filter(TangibleObject.class::isInstance)
                .map(TangibleObject.class::cast)
                .forEach(o -> scene.addVertices(o.getVertices()));
    }

    /**
     * Paints the rendered scene to the viewport.
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        // Prevent unnecessary computation
        if (painting) return;

        // Mark state as painting
        painting = true;

        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Move origin to center
        g.translate(getWidth() / 2, getHeight() / 2);

        // Draw vertices
        scene.getVertices().stream()
                .map(v -> v.transform(origin, angle).inflate(inflation))
                .filter(v -> v.centroid().z() > 0)
                .sorted((v1, v2) -> Double.compare(v2.centroid().magnitude2(), v1.centroid().magnitude2()))
                .forEach(v -> {
                    // Create polygon object
                    final PolygonX polygon = new PolygonX();
                    v.forEach(p -> polygon.addPoint(Numbers3.translate(p, focalLength)));

                    // Draw polygon on-screen
                    g.setColor((v instanceof ColoredVertex3 cv) ? cv.color() : Color.CYAN);
                    g.fillPolygon(polygon);

                    ////////////////////////////
                    /////////// TEMP ///////////
                    ////////////////////////////

                    g.setColor(Color.BLACK);
                    g.drawPolygon(polygon);
                });

        painting = false;
    }

    //
    // Context
    //

    @Nonnull
    private final Scene scene;
    @Nonnull
    private World world;
    @Nonnull
    private Vector3 origin;
    @Nonnull
    private Rotation angle;

    //
    // Settings
    //

    @Nonnegative double focalLength = 500;
    @Nonnegative double inflation = 10;

    //
    // Marker
    //

    private boolean painting = false;

    /**
     * Gets the scene object of this viewport.
     * @return Scene object
     */
    @Nonnull
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the world this viewport is rendering.
     * @return World of viewport
     */
    @Nonnull
    public World getWorld() {
        return world;
    }

    /**
     * Gets the origin of this viewport.
     * @return Origin of viewport
     */
    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    /**
     * Gets the viewing angle of this viewport.
     * @return Angle of viewport
     */
    @Nonnull
    public Rotation getAngle() {
        return angle;
    }

    /**
     * Sets the world this viewport is rendering.
     * @param world World to render
     */
    public void setWorld(@Nonnull World world) {
        this.world = world;
    }

    /**
     * Sets the origin of this viewport.
     * @param origin Viewport origin
     */
    public void setOrigin(@Nonnull Vector3 origin) {
        this.origin = origin;
    }

    /**
     * Sets the viewing angle of this viewport.
     * @param angle Viewing angle
     */
    public void setAngle(@Nonnull Rotation angle) {
        this.angle = angle;
    }

    /**
     * Gets the focal length of this viewport.
     * @return Focal length
     */
    @Nonnegative
    public double getFocalLength() {
        return focalLength;
    }

    /**
     * Gets the inflation of this viewport.
     * @return Inflation
     */
    @Nonnegative
    public double getInflation() {
        return inflation;
    }

    /**
     * Sets the focal length of this viewport.
     * @param focalLength Focal length
     */
    public void setFocalLength(@Nonnegative double focalLength) {
        this.focalLength = focalLength;
    }

    /**
     * Sets the inflation of this viewport.
     * @param inflation Inflation
     */
    public void setInflation(@Nonnegative double inflation) {
        this.inflation = inflation;
    }
}
