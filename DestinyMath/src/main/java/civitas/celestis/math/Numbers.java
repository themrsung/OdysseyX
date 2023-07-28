package civitas.celestis.math;

import civitas.celestis.math.matrix.*;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;

import javax.annotation.Nonnull;

/**
 * <h2>Numbers</h2>
 * <p>A numerical utility class.</p>
 */
public class Numbers {
    /**
     * Denotes explicitly that a given field requires a finite value.
     *
     * @param v Value to check
     * @return Value given as parameter
     */
    public static double requireFinite(double v) {
        if (!Double.isFinite(v)) throw new IllegalArgumentException("Given field requires a finite double.");

        return v;
    }

    /**
     * Gets the inverse square root of given number.
     *
     * @param x Number to inverse square root
     * @return Inverse square root
     */
    public static double isqrt(double x) {
        double result = x;
        double xhalf = 0.5d * result;

        long l = Double.doubleToLongBits(result);

        // Fast inverse square root
        l = 0x5fe6ec85e7de30daL - (l >> 1);

        result = Double.longBitsToDouble(l);

        // 4 iterations of Newton's method
        for (int i = 0; i < 4; i++) {
            result = result * (1.5d - xhalf * result * result);
        }

        return result;
    }

    //
    // Matrix Arithmetic
    //

    /**
     * Adds two matrices together.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Sum of two matrices
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    public static Matrix add(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        if (!m1.size().equals(m2.size())) {
            throw new ArithmeticException("Cannot perform addition of matrices with different dimensions.");
        }

        final Matrix result = new ArrayMatrix(m1.size());

        m1.forEach((i, v) -> result.set(i, v + m2.get(i)));

        return result;
    }

    /**
     * Adds two matrices together.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Sum of two matrices
     */
    @Nonnull
    public static Matrix2 add(@Nonnull Matrix2 m1, @Nonnull Matrix2 m2) {
        return new Matrix2(add((Matrix) m1, m2));
    }

    /**
     * Adds two matrices together.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Sum of two matrices
     */
    @Nonnull
    public static Matrix3 add(@Nonnull Matrix3 m1, @Nonnull Matrix3 m2) {
        return new Matrix3(add((Matrix) m1, m2));
    }

    /**
     * Adds two matrices together.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Sum of two matrices
     */
    @Nonnull
    public static Matrix4 add(@Nonnull Matrix4 m1, @Nonnull Matrix4 m2) {
        return new Matrix4(add((Matrix) m1, m2));
    }

    /**
     * Subtracts two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Difference of two matrices
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    public static Matrix subtract(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        if (!m1.size().equals(m2.size())) {
            throw new ArithmeticException("Cannot perform subtraction of matrices with different dimensions.");
        }

        final Matrix result = new ArrayMatrix(m1.size());

        m1.forEach((i, v) -> result.set(i, v - m2.get(i)));

        return result;
    }

    /**
     * Subtracts two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Difference of two matrices
     */
    @Nonnull
    public static Matrix2 subtract(@Nonnull Matrix2 m1, @Nonnull Matrix2 m2) {
        return new Matrix2(subtract((Matrix) m1, m2));
    }

    /**
     * Subtracts two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Difference of two matrices
     */
    @Nonnull
    public static Matrix3 subtract(@Nonnull Matrix3 m1, @Nonnull Matrix3 m2) {
        return new Matrix3(subtract((Matrix) m1, m2));
    }

    /**
     * Subtracts two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Difference of two matrices
     */
    @Nonnull
    public static Matrix4 subtract(@Nonnull Matrix4 m1, @Nonnull Matrix4 m2) {
        return new Matrix4(subtract((Matrix) m1, m2));
    }

    /**
     * Multiplies two matrices.
     * If the two matrices' dimensions are different, the resulting matrix will have a dimension of
     * {@code Math.max(m1.rows(), m2.rows())} and {@code Math.max(m1.columns(), m2.columns())}.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Product of two matrices
     * @throws ArithmeticException When multiplication is impossible (the dimensions are incompatible)
     */
    @Nonnull
    public static Matrix multiply(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        final int r1 = m1.rows();
        final int c1 = m1.columns();

        final int r2 = m2.rows();
        final int c2 = m2.columns();

        final Matrix result = new ArrayMatrix(Math.max(r1, r2), Math.max(c1, c2));

        try {
            for (int r = 0; r < r1; r++) {
                for (int c = 0; c < c2; c++) {
                    for (int i = 0; i < r2; i++) {
                        result.set(r, c, result.get(r, c) + m1.get(r, i) * m2.get(i, c));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ArithmeticException("Cannot perform multiplication of given matrices.");
        }

        return result;
    }

    /**
     * Multiplies two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Product of two matrices
     */
    @Nonnull
    public static Matrix2 multiply(@Nonnull Matrix2 m1, @Nonnull Matrix2 m2) {
        return new Matrix2(multiply((Matrix) m1, m2));
    }

    /**
     * Multiplies two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Product of two matrices
     */
    @Nonnull
    public static Matrix3 multiply(@Nonnull Matrix3 m1, @Nonnull Matrix3 m2) {
        return new Matrix3(multiply((Matrix) m1, m2));
    }

    /**
     * Multiplies two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Product of two matrices
     */
    @Nonnull
    public static Matrix4 multiply(@Nonnull Matrix4 m1, @Nonnull Matrix4 m2) {
        return new Matrix4(multiply((Matrix) m1, m2));
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param m Matrix to multiply
     * @param v Vector to multiply
     * @return Product of matrix-vector multiplication
     * @throws ArithmeticException If the vector's length is different from ths matrix's column count
     */
    @Nonnull
    public static double[] multiply(@Nonnull Matrix m, @Nonnull double[] v) throws ArithmeticException {
        if (m.columns() != v.length) {
            throw new ArithmeticException("Incompatible matrix-vector combination for multiplication.");
        }

        final double[] result = new double[v.length];

        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.columns(); c++) {
                result[c] += m.get(r, c) * v[c];
            }
        }

        return result;
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param m Matrix to multiply
     * @param v Vector to multiply
     * @return Product of matrix-vector multiplication
     */
    @Nonnull
    public static Vector2 multiply(@Nonnull Matrix2 m, @Nonnull Vector2 v) {
        final double[] result = multiply(m, new double[]{v.x(), v.y()});
        return new Vector2(result[0], result[1]);
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param m Matrix to multiply
     * @param v Vector to multiply
     * @return Product of matrix-vector multiplication
     */
    @Nonnull
    public static Vector3 multiply(@Nonnull Matrix3 m, @Nonnull Vector3 v) {
        final double[] result = multiply(m, new double[]{v.x(), v.y(), v.z()});
        return new Vector3(result[0], result[1], result[2]);
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param m Matrix to multiply
     * @param v Vector to multiply
     * @return Product of matrix-vector multiplication
     */
    @Nonnull
    public static Vector4 multiply(@Nonnull Matrix4 m, @Nonnull Vector4 v) {
        final double[] result = multiply(m, new double[]{v.w(), v.x(), v.y(), v.z()});
        return new Vector4(result[0], result[1], result[2], result[3]);
    }
}
