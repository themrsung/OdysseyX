package civitas.celestis.math.matrix;

import javax.annotation.Nonnull;

/**
 * <h2>Matrix3</h2>
 * <p>A 3x3 matrix.</p>
 */
public class Matrix3 extends ArrayMatrix implements Matrix {
    /**
     * Creates a new empty matrix.
     */
    public Matrix3() {
        super(3, 3);
    }

    /**
     * Creates a new matrix from given values.
     *
     * @param values Values to use
     */
    public Matrix3(@Nonnull double[][] values) {
        super(values);

        if (rows() != 3 || columns() != 3) {
            throw new IllegalArgumentException("Given array does not have the dimensions 3x3.");
        }
    }

    /**
     * Creates a new matrix from another.
     *
     * @param other Matrix to copy
     */
    public Matrix3(@Nonnull Matrix other) {
        super(other);

        if (rows() != 3 || columns() != 3) {
            throw new IllegalArgumentException("Given array does not have the dimensions 3x3.");
        }
    }
}
