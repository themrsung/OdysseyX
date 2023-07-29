package civitas.celestis.math;

import civitas.celestis.geometry.ray.Ray3;
import civitas.celestis.geometry.vertex.Vertex3;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Numbers2</h2>
 * <p>Contains 3D numerical utility functions.</p>
 */
public final class Numbers3 {
    /**
     * Gets the signed volume of a tetrahedron.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     * @param d Point D
     * @return Signed volume of tetrahedron
     */
    public static double signedVolume(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Vector3 d) {
        return (1.0 / 6.0) * b.subtract(a).cross(c.subtract(a)).dot(d.subtract(a));
    }


    /**
     * Checks if given vertex and ray intersects.
     *
     * @param vertex Vertex to check
     * @param ray    Ray to check
     * @return {@code true} if the two objects intersect
     */
    public static boolean intersects(@Nonnull Vertex3 vertex, @Nonnull Ray3 ray) {
        final Vector3 p1 = vertex.a();
        final Vector3 p2 = vertex.b();
        final Vector3 p3 = vertex.c();

        final Vector3 q1 = ray.origin();
        final Vector3 q2 = ray.destination(ray.origin().distance2(vertex.centroid()));

        // Different sign
        final double a = signedVolume(q1, p1, p2, p3);
        final double b = signedVolume(q2, p1, p2, p3);

        // Same sign
        final double c = signedVolume(q1, q2, p1, p2);
        final double d = signedVolume(q1, q2, p2, p3);
        final double e = signedVolume(q1, q2, p3, p1);

        return a * b < 0 && c * d * e >= 0;
    }

    /**
     * Given an input vector and a surface normal, this returns the reflection vector.
     *
     * @param in     Input vector
     * @param normal Surface normal
     * @return Reflection vector
     */
    @Nonnull
    public static Vector3 reflection(@Nonnull Vector3 in, @Nonnull Vector3 normal) {
        return in.subtract(normal.multiply(2 * in.dot(normal)));
    }
    /**
     * Translates a 3D vector to a 2D coordinate system.
     * <p>
     * This assumes that Z represents depth,
     * and inverts Y to be compatible with the AWT coordinate system.
     * </p>
     *
     * @param v3          3D vector
     * @param focalLength Focal length to use
     * @return Translated 2D vector
     */
    @Nonnull
    public static Vector2 translate(@Nonnull Vector3 v3, @Nonnegative double focalLength) {
        final double z = ((focalLength / (focalLength + v3.z())));
        return new Vector2(
                z * v3.x(),
                z * -v3.y()
        );
    }
}
