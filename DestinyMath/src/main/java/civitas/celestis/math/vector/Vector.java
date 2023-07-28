package civitas.celestis.math.vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <h2>Vector</h2>
 * <p>A superinterface for all vectors.</p>
 */
public interface Vector extends Serializable {
    /**
     * Adds a scalar to this vector.
     *
     * @param s Scalar to add
     * @return Resulting vector
     */
    @Nonnull
    Vector add(double s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s Scalar to subtract
     * @return Resulting vector
     */
    @Nonnull
    Vector subtract(double s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s Scalar to multiply with
     * @return Resulting vector
     */
    @Nonnull
    Vector multiply(double s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s Scalar to divide by
     * @return Resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    Vector divide(double s) throws ArithmeticException;

    /**
     * Gets the magnitude of this vector.
     *
     * @return Magnitude
     */
    @Nonnegative
    double magnitude();

    /**
     * Gets the squared magnitude of this vector.
     *
     * @return Squared magnitude
     */
    @Nonnegative
    double magnitude2();

    /**
     * Negates this vector.
     *
     * @return Negated vector
     */
    @Nonnull
    Vector negate();

    /**
     * Normalizes this vector to a unit vector.
     *
     * @return Normalized unit vector
     */
    @Nonnull
    Vector normalize();
}
