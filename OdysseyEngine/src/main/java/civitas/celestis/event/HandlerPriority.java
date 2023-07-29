package civitas.celestis.event;

/**
 * <h2>HandlerPriority</h2>
 * <p>The priority of an {@link EventHandler}.</p>
 */
public enum HandlerPriority {
    /**
     * Called first.
     * <ul>
     *     <li>Comes after: none</li>
     *     <li>Comes before: {@link HandlerPriority#PRE_EARLY}</li>
     * </ul>
     */
    PREEMPTIVE,

    /**
     * Called second.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#PREEMPTIVE}</li>
     *     <li>Comes before: {@link HandlerPriority#EARLY}</li>
     * </ul>
     */
    PRE_EARLY,

    /**
     * Called third.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#PRE_EARLY}</li>
     *     <li>Comes before: {@link HandlerPriority#POST_EARLY}</li>
     * </ul>
     */
    EARLY,

    /**
     * Called fourth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#EARLY}</li>
     *     <li>Comes before: {@link HandlerPriority#PRE_NORMAL}</li>
     * </ul>
     */
    POST_EARLY,

    /**
     * Called fifth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#POST_EARLY}</li>
     *     <li>Comes before: {@link HandlerPriority#NORMAL}</li>
     * </ul>
     */
    PRE_NORMAL,

    /**
     * Called sixth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#PRE_NORMAL}</li>
     *     <li>Comes before: {@link HandlerPriority#POST_NORMAL}</li>
     * </ul>
     */
    NORMAL,

    /**
     * Called seventh.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#NORMAL}</li>
     *     <li>Comes before: {@link HandlerPriority#PRE_LATE}</li>
     * </ul>
     */
    POST_NORMAL,

    /**
     * Called eighth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#POST_NORMAL}</li>
     *     <li>Comes before: {@link HandlerPriority#LATE}</li>
     * </ul>.
     */
    PRE_LATE,

    /**
     * Called ninth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#PRE_LATE}</li>
     *     <li>Comes before: {@link HandlerPriority#POST_LATE}</li>
     * </ul>
     */
    LATE,

    /**
     * Called tenth.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#LATE}</li>
     *     <li>Comes before: {@link HandlerPriority#MONITOR}</li>
     * </ul>
     */
    POST_LATE,

    /**
     * Called eleventh.
     * <ul>
     *     <li>Comes after: {@link HandlerPriority#POST_LATE}</li>
     *     <li>Comes before: none</li>
     * </ul>
     */
    MONITOR
}
