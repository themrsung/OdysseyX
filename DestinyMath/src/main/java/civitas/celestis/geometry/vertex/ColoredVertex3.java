package civitas.celestis.geometry.vertex;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.rotation.Rotation;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>ColoredVertex3</h2>
 * <p>A 3D vertex with one mutable color component.</p>
 */
public class ColoredVertex3 extends Vertex3 {
    /**
     * Creates a new vertex.
     * @param a Point A of this vertex
     * @param b Point B of this vertex
     * @param c Point C of this vertex
     * @param color Color of this vertex
     */
    public ColoredVertex3(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Color color) {
        super(a, b, c);
        this.color = color;
    }

    /**
     * Creates a new vertex.
     * @param other Vertex to use as coordinates
     * @param color Color to use
     */
    public ColoredVertex3(@Nonnull Vertex3 other, @Nonnull Color color) {
        super(other);
        this.color = color;
    }

    @Nonnull
    private Color color;

    /**
     * Gets the current color of this vertex.
     * @return Current color
     */
    @Nonnull
    public Color color() {
        return color;
    }

    /**
     * Sets the color of this vertex.
     * @param color Color to use
     */
    public void color(@Nonnull Color color) {
        this.color = color;
    }

    @Nonnull
    @Override
    public ColoredVertex3 inflate(double s) {
        return new ColoredVertex3(super.inflate(s), color);
    }

    @Nonnull
    @Override
    public ColoredVertex3 transform(@Nonnull Vector3 origin, @Nonnull Rotation r) {
        return new ColoredVertex3(super.transform(origin, r), color);
    }

    @Nonnull
    @Override
    public ColoredVertex3 transform(@Nonnull Vector3 origin, @Nonnull Quaternion rq) {
        return new ColoredVertex3(super.transform(origin, rq), color);
    }
}
