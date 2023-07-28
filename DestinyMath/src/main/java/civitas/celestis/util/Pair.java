package civitas.celestis.util;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Pair</h2>
 * <p>
 * A pair of two objects.
 * Given two objects {@code A} and {@code B}, {@code Pair{A, B} == Pair{B, A}}.
 * Pairs are useful for iterating through all possible permutations of a collection.
 * </p>
 *
 * @param first  First element of this pair
 * @param second Second element of this pair
 * @param <T>    Type of element to contain
 */
public record Pair<T>(
        @Nonnull T first,
        @Nonnull T second
) implements Iterable<T> {
    /**
     * Checks if given object is contained within this pair.
     *
     * @param object Object to check
     * @return {@code true} if either the first of second element is equal to given object
     */
    public boolean contains(@Nonnull T object) {
        return first.equals(object) || second.equals(object);
    }

    /**
     * Given an element, this returns the other element.
     *
     * @param object Element to check for
     * @return The other element
     * @throws IllegalArgumentException When given object is not a member of this pair
     */
    @Nonnull
    public T other(@Nonnull T object) throws IllegalArgumentException {
        if (first.equals(object)) return second;
        if (second.equals(object)) return first;

        throw new IllegalArgumentException("Given element is not a member of this pair.");
    }

    /**
     * Gets an iterator containing the elements of this pair.
     *
     * @return Iterator of elements
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(first, second).iterator();
    }

    /**
     * Checks for equality.
     *
     * @param obj Object to compare to
     * @return {@code true} if given object is a pair, and the contents are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Pair<?> p)) return false;
        return (Objects.equals(first, p.first) && Objects.equals(second, p.second))
                || (Objects.equals(second, p.first) && Objects.equals(first, p.second));
    }
}
