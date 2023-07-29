package civitas.celestis.util;

import javax.annotation.Nonnegative;

/**
 * <h2>Counter</h2>
 * <p>A simple object which iterates through a given range.</p>
 */
public final class Counter {
    /**
     * Creates a new counter.
     *
     * @param limit Upper limit of counter
     */
    public Counter(@Nonnegative int limit) {
        this(0, limit);
    }

    /**
     * Creates a new counter with given iterator and limit.
     *
     * @param i     Iterator to start at
     * @param limit Limit of counter
     * @throws IllegalArgumentException When {@code i >= limit}
     */
    public Counter(@Nonnegative int i, @Nonnegative int limit) throws IllegalArgumentException {
        if (i >= limit) {
            throw new IllegalArgumentException("Iterator cannot be equal to or greater than the limit.");
        }

        this.i = i;
        this.limit = limit;
    }

    @Nonnegative
    private int i;
    @Nonnegative
    private final int limit;

    /**
     * Gets the current counter, and does not increment it.
     *
     * @return Current iterator
     */
    @Nonnegative
    public int get() {
        return i;
    }

    /**
     * Caches the current counter, increments it, then returns the cached value.
     *
     * @return Current iterator (before incrementing)
     */
    @Nonnegative
    public int current() {
        final int it = i;
        iterate();

        return i;
    }

    /**
     * Iterates the counter, then returns it.
     *
     * @return Iterated counter
     */
    @Nonnegative
    public int next() {
        return iterate();
    }

    /**
     * Gets the upper limit of this counter.
     *
     * @return Upper limit
     */
    @Nonnegative
    public int limit() {
        return limit;
    }

    /**
     * Internal method for looping.
     *
     * @return Incremented counter
     */
    private int iterate() {
        if (i == limit - 1) {
            i = 0;
            return i;
        } else {
            return i++;
        }
    }
}
