package com.java.nba.pattern.factory;

/**
 * @author daiyun
 * @date 2019-4-21
 */
public class ShapeFactory {


	public Shape getShape(String shapeType){

		if (shapeType == null){
			return null;
		}


		if (shapeType.equals("circle")){
			return new Circle();
		} else if(shapeType.equals("rectangle")){
			return new Rectangle();
		}

		return null;

	}


}
