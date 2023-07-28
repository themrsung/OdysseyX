package civitas.celestis.math.matrix;

import javax.annotation.Nonnull;

/**
 * <h2>Matrix2</h2>
 * <p>A 2x2 matrix.</p>
 */
public class Matrix2 extends ArrayMatrix implements Matrix {
    /**
     * Creates a new empty matrix.
     */
    public Matrix2() {
        super(2, 2);
    }

    /**
     * Creates a new matrix from given values.
     *
     * @param values Values to use
     */
    public Matrix2(@Nonnull double[][] values) {
        super(values);

        if (rows() != 2 || columns() != 2) {
            throw new IllegalArgumentException("Given array does not have the dimensions 2x2.");
        }
    }

    /**
     * Creates a new matrix from another.
     *
     * @param other Matrix to copy
     */
    public Matrix2(@Nonnull Matrix other) {
        super(other);

        if (rows() != 2 || columns() != 2) {
            throw new IllegalArgumentException("Given array does not have the dimensions 2x2.");
        }
    }
}
