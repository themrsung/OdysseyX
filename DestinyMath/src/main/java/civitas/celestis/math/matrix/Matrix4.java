package civitas.celestis.math.matrix;

import javax.annotation.Nonnull;

/**
 * <h2>Matrix4</h2>
 * <p>A 4x4 matrix.</p>
 */
public class Matrix4 extends ArrayMatrix implements Matrix {
    /**
     * Creates a new empty matrix.
     */
    public Matrix4() {
        super(4, 4);
    }

    /**
     * Creates a new matrix from given values.
     *
     * @param values Values to use
     */
    public Matrix4(@Nonnull double[][] values) {
        super(values);

        if (rows() != 4 || columns() != 4) {
            throw new IllegalArgumentException("Given array does not have the dimensions 4x4.");
        }
    }

    /**
     * Creates a new matrix from another.
     *
     * @param other Matrix to copy
     */
    public Matrix4(@Nonnull Matrix other) {
        super(other);

        if (rows() != 4 || columns() != 4) {
            throw new IllegalArgumentException("Given array does not have the dimensions 4x4.");
        }
    }
}
