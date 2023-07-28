package civitas.celestis.math.matrix;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

/**
 * <h2>Matrix</h2>
 * <p>A superinterface for all m*n arrays of doubles.</p>
 */
public interface Matrix extends Iterable<Double>, Serializable {
    /**
     * Gets a list of all values in this matrix,
     * mapped from left-to-right and top-to-bottom.
     *
     * @return List of values
     */
    @Nonnull
    List<Double> values();

    /**
     * Gets an array of values in this matrix,
     * mapped from left-to-right and top-to-bottom.
     *
     * @return Array of values
     */
    @Nonnull
    double[] array();

    /**
     * Gets the value in specified position.
     *
     * @param r Index of row
     * @param c Index of column
     * @return Value of position
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    double get(@Nonnegative int r, @Nonnegative int c) throws IndexOutOfBoundsException;

    /**
     * Gets the value in specified index.
     *
     * @param i Index to get
     * @return Value of index
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    double get(@Nonnull Index i) throws IndexOutOfBoundsException;

    /**
     * Gets a pointer to the value of specified position.
     *
     * @param r Index of row
     * @param c Index of column
     * @return {@link Pointer}
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    @Nonnull
    Pointer ptr(@Nonnegative int r, @Nonnegative int c) throws IndexOutOfBoundsException;

    /**
     * Gets a pointer to the value of specified position.
     *
     * @param i Index to get
     * @return {@link Pointer}
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    @Nonnull
    Pointer ptr(@Nonnull Index i) throws IndexOutOfBoundsException;

    /**
     * Sets the value in specified position.
     *
     * @param r Index of row
     * @param c Index of column
     * @param v Value to set to
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    void set(@Nonnegative int r, @Nonnegative int c, double v) throws IndexOutOfBoundsException;

    /**
     * Sets the value in specified position.
     *
     * @param i Index to set
     * @param v Value to set to
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    void set(@Nonnull Index i, double v) throws IndexOutOfBoundsException;

    /**
     * Fills this matrix with given value.
     *
     * @param v Value to fill with
     */
    void fill(double v);

    /**
     * Gets the size of this matrix.
     *
     * @return Size
     */
    @Nonnull
    Index size();

    /**
     * Gets the number of rows in this matrix.
     *
     * @return Number of rows
     */
    @Nonnegative
    int rows();

    /**
     * Gets the number of columns in this matrix.
     *
     * @return Number of columns
     */
    @Nonnegative
    int columns();

    /**
     * Returns a resized matrix with the values of {@code this}.
     *
     * @param rows    Number of rows
     * @param columns Number of columns
     * @return Resized matrix
     */
    @Nonnull
    Matrix resize(@Nonnegative int rows, @Nonnegative int columns);

    /**
     * Returns a resized matrix with the values of {@code this}.
     *
     * @param size New size
     * @return Resized matrix
     */
    @Nonnull
    Matrix resize(@Nonnull Index size);

    /**
     * Iterates through all values in this matrix and performs given action.
     *
     * @param action Action to perform
     */
    void forEach(@Nonnull BiConsumer<Index, Double> action);

    /**
     * Parses a string to a matrix.
     *
     * @param s String to parse
     * @return Matrix parsed from string
     * @throws NumberFormatException When the given string is not parsable to a matrix
     */
    @Nonnull
    static Matrix parse(@Nonnull String s) throws NumberFormatException {
        try {
            final Matrix m = ArrayMatrix.parseMatrix(s);

            if (m.rows() == 2 && m.columns() == 2) {
                return new Matrix2(m);
            } else if (m.rows() == 3 && m.columns() == 3) {
                return new Matrix3(m);
            } else if (m.rows() == 4 && m.columns() == 4) {
                return new Matrix4(m);
            }

            return m;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Given string is not a matrix.");
        }
    }

    /**
     * The index of a matrix. All matrices accept indices as parameters.
     *
     * @param row    Index of row
     * @param column Index of column
     */
    record Index(
            @Nonnegative int row,
            @Nonnegative int column
    ) {
        /**
         * Gets the area of this index.
         *
         * @return Area of index
         */
        @Nonnegative
        int area() {
            return row * column;
        }
    }

    /**
     * A pointer used to modify the values of a matrix.
     */
    interface Pointer {
        /**
         * Gets the value of the element this pointer is pointing to
         *
         * @return Current value
         */
        double get();

        /**
         * Gets the value then updates it to the return value of the first parameter.
         *
         * @param operation Operation to perform
         */
        void getAndUpdate(@Nonnull UnaryOperator<Double> operation);

        /**
         * Sets the value of the element this pointer is pointing to
         *
         * @param v Value to set to
         */
        void set(double v);
    }
}
