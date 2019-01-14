module javafx.collections {
    exports org.phoenicis.javafx.collections;
    opens org.phoenicis.javafx.collections;
    opens org.phoenicis.javafx.collections.change;
    requires com.google.common;
    requires javafx.base;
    requires slf4j.api;
}