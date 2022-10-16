package testing;

import java.util.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ModelTest 
{
    
    @Test
    public void TestUserCheck()
    {
    	try{
        AdminList l = new AdminList();
        l.readFile();
        assertEquals(true, l.CheckUser("Ilya", "1234"));
        assertEquals(false, l.CheckUser("Vasya", "228358"));
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    
    @Test
    public void TestFileRead()
    {
        assertEquals(true, ReadTestInf());
    }
    
    public static boolean ReadTestInf(){
    try{
    String k = "";
    AdminList l = new AdminList();
    l.readFile();
    HashMap<String, String> users = l.getUsers();
    for(String key : users.keySet())
    	 k = key;
    if(users.size() == 1 && k.equals("Ilya") && users.get("Ilya").equals("1234"))
    	return true;
    
    }
    
    catch(Exception e){
    	e.printStackTrace();
    }
    
    return false;
    }
    
    
    
}
