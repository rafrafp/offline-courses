package controller;

public interface NavigationFactory {

	//factory method
	Controller getController(String controllerName);
	public void addController(String controllerName,Controller controller);
}