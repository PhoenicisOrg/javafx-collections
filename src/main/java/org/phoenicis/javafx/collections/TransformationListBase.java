package org.phoenicis.javafx.collections;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;

/**
 * A base class for a {@link TransformationList}.
 * This class differs from {@link TransformationList} because it divides the {@link TransformationList#sourceChanged(ListChangeListener.Change)}
 * function call into multiple other function calls depending on the type of occured change
 *
 * @param <E> The target type of the list
 * @param <F> The source type of the list
 */
public abstract class TransformationListBase<E, F> extends TransformationList<E, F> {

    /**
     * Constructor
     *
     * @param source The source list
     */
    protected TransformationListBase(ObservableList<? extends F> source) {
        super(source);
    }

    /**
     * Performs a permutation on the list based on the given input change
     *
     * @param change The input change object from the source list
     */
    protected abstract void permute(ListChangeListener.Change<? extends F> change);

    /**
     * Performs an update on the list based on the given input change
     *
     * @param change The input change object from the source list
     */
    protected abstract void update(ListChangeListener.Change<? extends F> change);

    /**
     * Performs an add/remove operation on the list based on the given input change
     *
     * @param change The input change object from the source list
     */
    protected abstract void addRemove(ListChangeListener.Change<? extends F> change);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sourceChanged(ListChangeListener.Change<? extends F> change) {
        beginChange();
        while (change.next()) {
            if (change.wasPermutated()) {
                permute(change);
            } else if (change.wasUpdated()) {
                update(change);
            } else {
                addRemove(change);
            }
        }
        endChange();
    }

    /**
     * Fires a {@link Change} event containing all elements inside this {@link TransformationList}.
     * This method should be used directly after the {@link TransformationList} has been initialized
     */
    protected void fireInitialisationChange() {
        beginChange();
        nextAdd(0, size());
        endChange();
    }
}
