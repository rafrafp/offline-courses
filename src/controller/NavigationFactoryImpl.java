package controller;

import java.util.HashMap;

public  class NavigationFactoryImpl implements NavigationFactory {
	
	
	private HashMap<String, Controller> controllersMap = new HashMap<String, Controller>();
	
    //factory method
	@Override
	public  Controller getController(String controllerName) {
		
		return controllersMap.get(controllerName);
		
	}
	
	public void addController(String controllerName,Controller controller) {
		controllersMap.put(controllerName, controller);
	}
}
