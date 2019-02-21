package org.phoenicis.javafx.collections;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MappedListTest {
    @Test
    public void testListCreation() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);
    }

    @Test
    public void testListAdd() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);

        observableList.add(0);

        assertEquals(List.of("3", "7", "1", "5", "0"), mappedList);
        assertEquals(List.of("3", "7", "1", "5", "0"), actual);
    }

    @Test
    public void testListRemove() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);

        observableList.remove(2);

        assertEquals(List.of("3", "7", "5"), mappedList);
        assertEquals(List.of("3", "7", "5"), actual);
    }

    @Test
    public void testListUpdate() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);

        observableList.set(2, 4);

        assertEquals(List.of("3", "7", "4", "5"), mappedList);
        assertEquals(List.of("3", "7", "4", "5"), actual);
    }

    @Test
    public void testListPermutation() {
        SortedList<Integer> sortedList = FXCollections.observableArrayList(List.of(3, 7, 1, 5))
                .sorted(Comparator.naturalOrder());
        MappedList<String, Integer> mappedList = new MappedList<>(sortedList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("1", "3", "5", "7"), mappedList);
        assertEquals(List.of("1", "3", "5", "7"), actual);

        sortedList.comparatorProperty().set(Comparator.comparing(String::valueOf).reversed());

        assertEquals(List.of("7", "5", "3", "1"), mappedList);
        assertEquals(List.of("7", "5", "3", "1"), actual);
    }

    @Test
    public void testMapperChange() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);

        mappedList.setMapper(i -> i + "!");

        assertEquals(List.of("3!", "7!", "1!", "5!"), mappedList);
        assertEquals(List.of("3!", "7!", "1!", "5!"), actual);
    }

    @Test
    public void testMapperChangeToNull() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList, String::valueOf);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);

        mappedList.setMapper(null);

        assertEquals(Collections.emptyList(), mappedList);
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void testMapperChangeFromNull() {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(List.of(3, 7, 1, 5));
        MappedList<String, Integer> mappedList = new MappedList<>(observableList);

        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, mappedList);

        assertEquals(Collections.emptyList(), mappedList);
        assertEquals(Collections.emptyList(), actual);

        mappedList.setMapper(String::valueOf);

        assertEquals(List.of("3", "7", "1", "5"), mappedList);
        assertEquals(List.of("3", "7", "1", "5"), actual);
    }
}
