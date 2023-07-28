package civitas.celestis.math.matrix;

import civitas.celestis.math.Numbers;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

/**
 * <h2>ArrayMatrix</h2>
 * <p>A matrix with a custom size.</p>
 */
public class ArrayMatrix implements Matrix {
    //
    // Constructors
    //

    /**
     * Creates a new matrix with specified size.
     *
     * @param rows    Number of rows
     * @param columns Number of columns
     */
    public ArrayMatrix(@Nonnegative int rows, @Nonnegative int columns) {
        this.values = new double[rows][columns];
    }

    /**
     * Creates a new matrix with specified size.
     *
     * @param size Size of matrix
     */
    public ArrayMatrix(@Nonnull Index size) {
        this(size.row(), size.column());
    }

    /**
     * Creates a new matrix from provided values.
     *
     * @param values Values to use
     */
    public ArrayMatrix(@Nonnull double[][] values) {
        this.values = values;

        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns(); c++) {
                Numbers.requireFinite(values[r][c]);
            }
        }
    }

    /**
     * Creates a new matrix from another.
     *
     * @param other Matrix to copy
     */
    public ArrayMatrix(@Nonnull Matrix other) {
        this.values = new double[other.rows()][other.columns()];

        for (int r = 0; r < other.rows(); r++) {
            for (int c = 0; c < other.columns(); c++) {
                values[r][c] = other.get(r, c);
            }
        }
    }

    //
    // Variables
    //
    @Nonnull
    private final double[][] values;

    //
    // Getters
    //

    @Nonnull
    @Override
    public List<Double> values() {
        final List<Double> list = new ArrayList<>();

        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns(); c++) {
                list.add(values[r][c]);
            }
        }

        return list;
    }

    @Nonnull
    @Override
    public double[] array() {
        final double[] array = new double[size().area()];

        int i = 0;
        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns(); c++) {
                array[i] = values[r][c];
                i++;
            }
        }

        return array;
    }

    @Override
    public double get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    @Override
    public double get(@Nonnull Index i) throws IndexOutOfBoundsException {
        return values[i.row()][i.column()];
    }

    @Nonnull
    @Override
    public Pointer ptr(int r, int c) throws IndexOutOfBoundsException {
        final double v = get(r, c); // Cache current value for consistency
        return new Pointer() {
            @Override
            public double get() {
                return v;
            }

            @Override
            public void getAndUpdate(@Nonnull UnaryOperator<Double> operation) {
                ArrayMatrix.this.set(r, c, operation.apply(v));
            }

            @Override
            public void set(double v) {
                ArrayMatrix.this.set(r, c, v);
            }
        };
    }

    @Nonnull
    @Override
    public Pointer ptr(@Nonnull Index i) throws IndexOutOfBoundsException {
        return ptr(i.row(), i.column());
    }

    @Override
    public void set(int r, int c, double v) throws IndexOutOfBoundsException {
        values[r][c] = Numbers.requireFinite(v);
    }

    @Override
    public void set(@Nonnull Index i, double v) throws IndexOutOfBoundsException {
        values[i.row()][i.column()] = Numbers.requireFinite(v);
    }

    @Override
    public void fill(double v) {
        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns(); c++) {
                values[r][c] = Numbers.requireFinite(v);
            }
        }
    }

    //
    // Size
    //

    @Override
    public int rows() {
        return values.length;
    }

    @Override
    public int columns() {
        try {
            return values[0].length;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    @Nonnull
    @Override
    public Index size() {
        return new Index(rows(), columns());
    }

    //
    // Util
    //

    @Nonnull
    @Override
    public ArrayMatrix resize(int rows, int columns) {
        final ArrayMatrix result = new ArrayMatrix(rows, columns);

        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns; c++) {
                try {
                    result.set(r, c, get(r, c));
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return result;
    }

    @Nonnull
    @Override
    public Matrix resize(@Nonnull Index size) {
        return resize(size.row(), size.column());
    }

    @Override
    public void forEach(@Nonnull BiConsumer<Index, Double> action) {
        for (int r = 0; r < rows(); r++) {
            for (int c = 0; c < columns(); c++) {
                action.accept(new Index(r, c), get(r, c));
            }
        }
    }

    @Override
    public Iterator<Double> iterator() {
        return values().iterator();
    }

    //
    // Serialization
    //

    /**
     * Parses a string to a matrix.
     *
     * @param s String to parse
     * @return Parsed matrix
     * @throws NumberFormatException When given string is not parsable to a matrix
     */
    @Nonnull
    static Matrix parseMatrix(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("ArrayMatrix{rows={")) throw new NumberFormatException("Given string is not a matrix.");

        final String[] strings = s
                .replaceAll("ArrayMatrix\\{rows=\\{", "")
                .replaceAll("}}", "")
                .replaceAll("\\[", "")
                .split("], ");

        final double[][] values;
        try {
            values = new double
                    [strings.length]
                    [strings.length > 0 ? strings[0].split("=")[1].split(", ").length : 0];
        } catch (IndexOutOfBoundsException e) {
            throw new NumberFormatException("Given string is not a matrix.");
        }

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a matrix.");

            final String[] numbers = split[1].replaceAll("]", "").split(", ");
            final int r = Integer.parseInt(split[0]);
            final double[] row = new double[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                row[i] = Double.parseDouble(numbers[i]);
            }

            values[r] = row;
        });

        return new ArrayMatrix(values);
    }

    /**
     * Converts this matrix to a string.
     *
     * @return Stringified matrix
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder builder = new StringBuilder("ArrayMatrix{rows={");

        for (int r = 0; r < rows(); r++) {
            builder.append(r).append("=").append(Arrays.toString(values[r]));

            if (r < rows() - 1) builder.append(", ");
        }

        return builder.append("}}").toString();
    }
}
