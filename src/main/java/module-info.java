/**
 * Module-info
 */
module com.github.samikoogithub.harjoitustyo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.samikoogithub.harjoitustyo to javafx.fxml;
    exports com.github.samikoogithub.harjoitustyo;
}