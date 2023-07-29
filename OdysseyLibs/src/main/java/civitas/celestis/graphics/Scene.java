package civitas.celestis.graphics;

import civitas.celestis.geometry.ray.Ray3;
import civitas.celestis.geometry.vertex.Vertex3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * <h2>Scene</h2>
 * <p>
 * A three-dimensional scene.
 * Place this object inside an AWT component to ensure it runs on the GPU.
 * </p>
 */
public class Scene {
    //
    // Constructors
    //

    /**
     * Creates a new empty scene.
     */
    public Scene() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new scene with predefined vertices.
     *
     * @param vertices List of vertices
     */
    public Scene(@Nonnull List<Vertex3> vertices) {
        this.vertices = vertices;
    }

    //
    // Variables
    //

    @Nonnull
    private final List<Vertex3> vertices;

    //
    // Vertices
    //

    /**
     * Gets a list of all vertices in this scene.
     *
     * @return List of vertices
     */
    @Nonnull
    public List<Vertex3> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Adds a vertex to this scene.
     *
     * @param v Vertex to add
     */
    public void addVertex(@Nonnull Vertex3 v) {
        vertices.add(v);
    }

    /**
     * Adds multiple vertices to this scene.
     *
     * @param v Collection of vertices to add
     */
    public void addVertices(@Nonnull Collection<Vertex3> v) {
        vertices.addAll(v);
    }

    /**
     * Removes a vertex from this scene.
     *
     * @param v Vertex to remove
     */
    public void removeVertex(@Nonnull Vertex3 v) {
        vertices.remove(v);
    }

    /**
     * Removes multiple vertices from this scene.
     *
     * @param v Collection of vertices to remove
     */
    public void removeVertices(@Nonnull Collection<Vertex3> v) {
        vertices.removeAll(v);
    }

    /**
     * Clears all data in this viewport.
     */
    public void clear() {
        vertices.clear();
    }

    //
    // Raytracing
    //

    /**
     * Shoots a ray into this scene.
     *
     * @param ray   Ray to shoot
     * @param limit Maximum allowed number of reflections
     */
    public void shootRay(@Nonnull Ray3 ray, @Nonnegative long limit) {
        shootRayImpl(ray, limit, null);
    }

    /**
     * Internal method for recursive calling.
     *
     * @param ray    Ray to shoot
     * @param limit  Remaining number of reflections
     * @param origin Origin vertex of this ray
     */
    protected void shootRayImpl(@Nonnull Ray3 ray, @Nonnegative long limit, @Nullable Vertex3 origin) {
        final List<Vertex3> vertices = new ArrayList<>(getVertices().stream()
                .sorted(Comparator.comparing(v -> v.centroid().distance2(ray.origin())))
                .toList());

        if (origin != null) vertices.remove(origin);

        for (Vertex3 vertex : vertices) {
            final Ray3 reflection = ray.reflection(vertex);
            if (reflection != null) {
                shootRayImpl(reflection, limit - 1, vertex);
                return;
            }
        }

    }
}
