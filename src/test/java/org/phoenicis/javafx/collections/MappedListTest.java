package org.phoenicis.javafx.collections;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MappedListTest {
    @Test
    public void testListCreation() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);
    }

    @Test
    public void testListAdd() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);

        observableList.add(0);

        assertEquals(Arrays.asList("3", "7", "1", "5", "0"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5", "0"), actual);
    }

    @Test
    public void testListRemove() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);

        observableList.remove(2);

        assertEquals(Arrays.asList("3", "7", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "5"), actual);
    }

    @Test
    public void testListUpdate() {
        ObservableList<Integer> observableList = FXCollections.observableList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);

        observableList.set(2, 4);

        assertEquals(Arrays.asList("3", "7", "4", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "4", "5"), actual);
    }

    @Test
    public void testListPermutation() {
        SortedList<Integer> sortedList = FXCollections.observableList(Arrays.asList(3, 7, 1, 5))
                .sorted(Comparator.naturalOrder());
        MappedList<String, Integer> mappedList = new MappedList<>(sortedList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("1", "3", "5", "7"), mappedList);
        assertEquals(Arrays.asList("1", "3", "5", "7"), actual);

        sortedList.comparatorProperty().set(Comparator.comparing(String::valueOf).reversed());

        assertEquals(Arrays.asList("7", "5", "3", "1"), mappedList);
        assertEquals(Arrays.asList("7", "5", "3", "1"), actual);
    }

    @Test
    public void testMapperChange() {
        ObservableList<Integer> observableList = FXCollections.observableList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);

        mappedList.setMapper(i -> i + "!");

        assertEquals(Arrays.asList("3!", "7!", "1!", "5!"), mappedList);
        assertEquals(Arrays.asList("3!", "7!", "1!", "5!"), actual);
    }

    @Test
    public void testMapperChangeToNull() {
        ObservableList<Integer> observableList = FXCollections.observableList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);

        mappedList.setMapper(null);

        assertEquals(Collections.emptyList(), mappedList);
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void testMapperChangeFromNull() {
        ObservableList<Integer> observableList = FXCollections.observableList(Arrays.asList(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Collections.emptyList(), mappedList);
        assertEquals(Collections.emptyList(), actual);

        mappedList.setMapper(String::valueOf);

        assertEquals(Arrays.asList("3", "7", "1", "5"), mappedList);
        assertEquals(Arrays.asList("3", "7", "1", "5"), actual);
    }
}
