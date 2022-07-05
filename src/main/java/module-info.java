module Schulden {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;

	exports schulden.list;
	opens schulden.list to javafx.controls, javafx.base, javafx.graphics;
}