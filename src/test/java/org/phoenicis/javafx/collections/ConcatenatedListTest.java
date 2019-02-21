package org.phoenicis.javafx.collections;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ConcatenatedListTest {
    @Test
    public void testListCreation() {
        final ConcatenatedList<String> concatenatedList = ConcatenatedList
                .create(Collections.singletonList("11"), List.of("21", "22"), Collections.emptyList());
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);
    }

    @Test
    public void testListAdd() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.add(1, FXCollections.observableArrayList("01", "02"));

        assertEquals(List.of("11", "01", "02", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "01", "02", "21", "22"), actual);
    }

    @Test
    public void testListAddDuplicate() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.add(0, FXCollections.observableArrayList("21", "22"));

        assertEquals(List.of("21", "22", "11", "21", "22"), concatenatedList);
        assertEquals(List.of("21", "22", "11", "21", "22"), actual);
    }

    @Test
    public void testListRemoveFirstList() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.remove(0);

        assertEquals(List.of("21", "22"), concatenatedList);
        assertEquals(List.of("21", "22"), actual);
    }

    @Test
    public void testListRemoveMiddleList() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.remove(1);

        assertEquals(Collections.singletonList("11"), concatenatedList);
        assertEquals(Collections.singletonList("11"), actual);
    }

    @Test
    public void testListRemoveLastList() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.remove(2);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);
    }

    @Test
    public void testListRemoveDuplicateList() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList("11")));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "11"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "11"), actual);

        observableList.remove(2);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.remove(0);

        assertEquals(List.of("21", "22"), concatenatedList);
        assertEquals(List.of("21", "22"), actual);
    }

    @Test
    public void testListUpdateLastList() {
        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.set(2, FXCollections.observableArrayList("31", "32", "33"));

        assertEquals(List.of("11", "21", "22", "31", "32", "33"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31", "32", "33"), actual);
    }

    @Test
    public void testListUpdateViaFilteredList() {
        final FilteredList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList("31")))
                .filtered(value -> true);
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);

        observableList.setPredicate(value -> value.size() != 1);

        assertEquals(List.of("21", "22"), concatenatedList);
        assertEquals(List.of("21", "22"), actual);

        observableList.setPredicate(value -> true);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);
    }

    @Test
    public void testListPermutation() {
        final SortedList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.<ObservableList<String>>of(
                        FXCollections.observableArrayList("11"),
                        FXCollections.observableArrayList("21", "22"),
                        FXCollections.observableArrayList()))
                .sorted(Comparator.comparing(List::size));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);
        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22"), concatenatedList);
        assertEquals(List.of("11", "21", "22"), actual);

        observableList.comparatorProperty().set(Comparator.<List<String>, Integer>comparing(List::size).reversed());

        assertEquals(List.of("21", "22", "11"), concatenatedList);
        assertEquals(List.of("21", "22", "11"), actual);
    }

    @Test
    public void testInnerListAdd() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final ObservableList<String> list2 = FXCollections.observableArrayList("21", "22");
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3));

        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);

        list2.add("23");

        assertEquals(List.of("11", "21", "22", "23", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "23", "31"), actual);
    }

    @Test
    public void testInnerListAddWithEqualInnerLists() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final ObservableList<String> list2 = FXCollections.observableArrayList("41");
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");
        final ObservableList<String> list4 = FXCollections.observableArrayList();

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3, list4));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "41", "31"), concatenatedList);
        assertEquals(List.of("11", "41", "31"), actual);

        // list2 and list4 are equal after the add operation
        list4.add("41");

        assertEquals(List.of("11", "41", "31", "41"), concatenatedList);
        assertEquals(List.of("11", "41", "31", "41"), actual);
    }

    @Test
    public void testInnerListRemove() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final ObservableList<String> list2 = FXCollections.observableArrayList("21", "22");
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);

        list2.remove(0);

        assertEquals(List.of("11", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "22", "31"), actual);
    }

    @Test
    public void testInnerListRemoveWithEqualInnerLists() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final ObservableList<String> list2 = FXCollections.observableArrayList();
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");
        final ObservableList<String> list4 = FXCollections.observableArrayList("41");

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3, list4));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "31", "41"), concatenatedList);
        assertEquals(List.of("11", "31", "41"), actual);

        // list2 and list4 are equal after the clear operation
        list4.clear();

        assertEquals(List.of("11", "31"), concatenatedList);
        assertEquals(List.of("11", "31"), actual);
    }

    @Test
    public void testInnerListUpdate() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final ObservableList<String> list2 = FXCollections.observableArrayList("21", "22");
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);

        list2.set(0, "20");

        assertEquals(List.of("11", "20", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "20", "22", "31"), actual);
    }

    @Test
    public void testInnerListPermute() {
        final ObservableList<String> list1 = FXCollections.observableArrayList("11");
        final SortedList<String> list2 = FXCollections.observableArrayList("21", "22").sorted();
        final ObservableList<String> list3 = FXCollections.observableArrayList("31");

        final ObservableList<ObservableList<String>> observableList = FXCollections
                .observableArrayList(List.of(list1, list2, list3));
        final ConcatenatedList<String> concatenatedList = new ConcatenatedList<>(observableList);

        final List<String> actual = new ArrayList<>();

        Bindings.bindContent(actual, concatenatedList);

        assertEquals(List.of("11", "21", "22", "31"), concatenatedList);
        assertEquals(List.of("11", "21", "22", "31"), actual);

        list2.setComparator(Comparator.reverseOrder());

        assertEquals(List.of("11", "22", "21", "31"), concatenatedList);
        assertEquals(List.of("11", "22", "21", "31"), actual);
    }
}
