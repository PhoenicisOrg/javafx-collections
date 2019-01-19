# phoenicis-javafx-collections
[![Build Status](https://travis-ci.com/PhoenicisOrg/javafx-collections.svg?branch=master)](https://travis-ci.com/PhoenicisOrg/javafx-collections)
[![JitPack](https://jitpack.io/v/PhoenicisOrg/javafx-collections.svg)](https://jitpack.io/#PhoenicisOrg/javafx-collections)

The `phoenicis-javafx-collections` library consists of a set of additional observable collections for JavaFX.

## `MappedList`
The `MappedList<B, A>` maps the values of type `A` contained inside an `ObservableList<A>` into an `ObservableList<B>` with valued of type `B`.  

In addition the `MappedList<B, A>` supports changing the provided mapping function at runtime by changing the `mapper` property of the `MappedList<B, A>`.
In case no mapping function is specified, i.e. the mapping function is set to `null`, the `MappedList<B, A>` acts as an empty list.
 
### Examples
```java
ObservableList<String> source = FXCollections.observableArrayList("A", "B", "C");

// MappedList<Label, String> mappedList = new MappedList(source, string -> new Label(string));
ObservableList<Label> mappedList = new MappedList(source, string -> new Label(string));

// mappedList now contains: [new Label("A"), new Label("B"), new Label("C")]
```

## `ConcatenatedList`
The `ConcatenatedList<A>` concatenates multiple `ObservableList<A>` into a single `ObservableList<A>` by retaining the initial ordering of the input lists.

To make it easier to create a `ConcatenatedList<A>` for different use case scenarios, the `ConcatenatedList<A>` class provides a number of support `create` functions:  

* `createPrefixList(ObservableList<? extends A>, A...)`
* `createSuffixList(ObservableList<? extends A>, A...)`
* `create(ObservableList<? extends A>...)`
* `create(List<? extends A>...)`
* `create(ObservableList<? extends List<? extends A>> list)`

### Examples
A `ConcatenatedList<String>` concatenating three `ObservableList`s:

```java
ObservableList<String> source1 = FXCollections.observableArrayList("1", "2");
ObservableList<String> source2 = FXCollections.observableArrayList();
ObservableList<String> source3 = FXCollections.observableArrayList("21", "22");

// ConcatenatedList<String> concatenatedList = ConcatenatedList.create(source1, source2, source3);
ObservableList<String> concatenatedList = ConcatenatedList.create(source1, source2, source3);

// concatenatedList now contains: ["1", "2", "21", "22"]
```

A `ConcatenatedList<String>` consisting of a number of prefix objects and an `ObservableList`:
 
```java
ObservableList<String> source = FXCollections.observableArrayList("1", "2");

// ConcatenatedList<String> prefixList = ConcatenatedList.createPrefixList(source, "test");
ObservableList<String> prefixList = ConcatenatedList.createPrefixList(source, "test");

// prefixList now contains: ["test", "1", "2"]
```

A `ConcatenatedList<String>` consisting of an `ObservableList` and a number of suffix objects:
 
```java
ObservableList<String> source = FXCollections.observableArrayList("1", "2");

// ConcatenatedList<String> suffixList = ConcatenatedList.createSuffixList(source, "test");
ObservableList<String> suffixList = ConcatenatedList.createSuffixList(source, "test");

// suffixList now contains: ["1", "2", "test"]
```
