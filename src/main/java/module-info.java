module com.cgvsu.rasterizationfxapp {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.cgvsu.rasterizationfxapp to javafx.fxml;
    exports com.cgvsu.rasterizationfxapp;


    opens com.cgvsu.rasterization.model;
    opens com.cgvsu.rasterization.algorithm;
    opens com.cgvsu.rasterization.render;


    exports com.cgvsu.rasterization.model;
    exports com.cgvsu.rasterization.algorithm;
    exports com.cgvsu.rasterization.render;
}