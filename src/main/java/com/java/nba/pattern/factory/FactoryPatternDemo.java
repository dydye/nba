package com.java.nba.pattern.factory;

/**
 * @author daiyun
 * @date 2019-4-21
 */
public class FactoryPatternDemo {

	public static void main(String[] args) {


		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape1 = shapeFactory.getShape("circle");
		shape1.draw();
		Shape shape2 = shapeFactory.getShape("rectangle");
		shape2.draw();
	}

}
