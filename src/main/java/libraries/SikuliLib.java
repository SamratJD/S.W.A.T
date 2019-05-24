package libraries;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import utilities.TestBase;;

public class SikuliLib extends TestBase{

	Screen screen = new Screen();
	Pattern obj;
	
	/**
	 * @author Samrat
	 * @category Sikuli Function
	 * @param path
	 * @param fileName
	 */
	public void clickObject(String path , String fileName){
		 obj= new Pattern(path + "\\" + fileName);
		 try {
			screen.wait(obj,10);
			screen.click(obj);
		} catch (FindFailed e) {
			log.error("Error while trying to click on Object " + e.getMessage());
			e.printStackTrace();
		}
		 log.info("Click successful");
	}
	
	/**
	 * @author Samrat
	 * @category Sikuli Function
	 * @param path
	 * @param fileName
	 */
	public void doubleClickObject(String path , String fileName){
		 obj= new Pattern(path + "\\" + fileName);
		 try {
			screen.wait(obj,10);
			screen.doubleClick(obj);
		} catch (FindFailed e) {
			log.error("Error while trying to double click on Object " + e.getMessage());
			e.printStackTrace();
		}
		 log.info("Double Click successful");
	}
	
	/**
	 * @author Samrat
	 * @category Sikuli Function
	 * @param path
	 * @param fileName
	 */
	public void rightClickObject(String path , String fileName){
		 obj= new Pattern(path + "\\" + fileName);
		 try {
			screen.wait(obj,10);
			screen.rightClick(obj);
		} catch (FindFailed e) {
			log.error("Error while trying to right click on Object " + e.getMessage());
			e.printStackTrace();
		}
		 log.info("Right Click successful");
	}
	
	/**
	 * @author Samrat
	 * @category Sikuli Function
	 * @param path
	 * @param fileName
	 * @param text
	 */
	public void typeObject(String path , String fileName , String text){
		obj= new Pattern(path + "\\" + fileName);
		try {
			screen.wait(obj,10);
			screen.type(obj,text);
		} catch (FindFailed e) {
			log.error("Error while trying to enter text on Object " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Text has been entered successfully");
	}
	
	/**
	 * @author Samrat
	 * @category Sikuli Function
	 * @param path
	 * @param fileName
	 */
	public boolean objectExists(String path , String fileName){
		obj= new Pattern(path + "\\" + fileName);
		if(screen.exists(obj,10) != null){
			log.info("Object has been found on page");
			return true;
		}
		else{
			log.error("Object has not been found on page");
			return false;
		}
	}
	
}
