package civitas.celestis.geometry.vertex;

import civitas.celestis.math.vector.Vector2;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex2</h2>
 * <p>A two-dimensional vertex.</p>
 */
@Immutable
public class Vertex2 implements Vertex<Vector2> {
    /**
     * Creates a new vertex.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     */
    public Vertex2(@Nonnull Vector2 a, @Nonnull Vector2 b, @Nonnull Vector2 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new vertex from another.
     *
     * @param other Vertex to copy
     */
    public Vertex2(@Nonnull Vertex2 other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    @Nonnull
    private final Vector2 a;
    @Nonnull
    private final Vector2 b;
    @Nonnull
    private final Vector2 c;

    @Nonnull
    @Override
    public Vector2 a() {
        return a;
    }

    @Nonnull
    @Override
    public Vector2 b() {
        return b;
    }

    @Nonnull
    @Override
    public Vector2 c() {
        return c;
    }

    @Nonnull
    @Override
    public List<Vector2> points() {
        return List.of(a, b, c);
    }

    @Nonnull
    @Override
    public Vector2 centroid() {
        return a.add(b).add(c).divide(3);
    }

    @Override
    public Iterator<Vector2> iterator() {
        return points().iterator();
    }
}
