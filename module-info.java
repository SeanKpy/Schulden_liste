module Schulden {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;

	opens schulden.list to javafx.controls, javafx.base, javafx.graphics;
}