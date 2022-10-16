package testing;

import java.util.*;
import java.io.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemsManagerTest 
{
    
    
    @Test
    public void AddItemTest()
    {
        assertEquals(true, AddTestInf());
    }


public static boolean AddTestInf()
    {
    	boolean flag = false;
	String title = "Car";
    	int price = 67000;
    	int amount = 4;
    	String path = "ServletImages/Car";
    	
    	int k1 = 0;
    	int k2 = 0;
    	int k3 = 0;
    	int k4 = 0;
    
    	ItemsManager it = new ItemsManager();
    	it.Add(title, price, amount);
    	LinkedHashMap<Integer, String> items = it.getItems();
        LinkedHashMap<Integer, Integer> itemsNum = it.getItemsNum();
        LinkedHashMap<Integer, Integer> prices = it.getItemsPrices();
        LinkedHashMap<Integer, String> file_paths = it.getFilePaths();
        
        for(int key : items.keySet())
        	 k1 = key;
        	 
        for(int key : itemsNum.keySet())
        	 k2 = key;
        	 
        for(int key : prices.keySet())
        	 k3 = key;
        	 
        for(int key : file_paths.keySet())
        	 k4 = key;
        
        if(items.size() == 1 && itemsNum.size() == 1 && prices.size() == 1 && file_paths.size() == 1 && k1 == 0 && k2 == 0 && k3 == 0 && k4 == 0){
        	if(items.get(k1).equals(title) && itemsNum.get(k2) == amount && prices.get(k2) == price && file_paths.get(k4).equals(path)){
        		flag = true;
        	}
        }
        
        
        title = "Phone";
        price = 6700;
    	amount = 10;
    	path = "ServletImages/Phone";
    	
    	it.Add(title, price, amount);
    	items = it.getItems();
        itemsNum = it.getItemsNum();
        prices = it.getItemsPrices();
        file_paths = it.getFilePaths();
        
        for(int key : items.keySet())
        	 k1 = key;
        	 
        for(int key : itemsNum.keySet())
        	 k2 = key;
        	 
        for(int key : prices.keySet())
        	 k3 = key;
        	 
        for(int key : file_paths.keySet())
        	 k4 = key;
        
        if(items.size() == 2 && itemsNum.size() == 2 && prices.size() == 2 && file_paths.size() == 2 && k1 == 1 && k2 == 1 && k3 == 1 && k4 == 1){
        	if(items.get(k1).equals(title) && itemsNum.get(k2) == amount && prices.get(k2) == price && file_paths.get(k4).equals(path)){
        		flag = true;
        	}
        	
        	else
        		flag = false;
        }
        
        else
        	flag = false;
        	
        if(flag)
        	return true;
    	
        
        return false;
        	
    }
    
    @Test
    public void DeleteItemTest()
    {
	try{    
	    	String title = "Car";
	    	int price = 67000;
	    	int amount = 4;
	    	FileInputStream fs = new FileInputStream(new File("/home/ilya/Downloads/TV.jpeg"));
	    	ItemsManager it = new ItemsManager();
	    	it.Add(title, price, amount);
	    	it.SaveImage(fs, title);
	    	it.delete(0);
	    	LinkedHashMap<Integer, String> items = it.getItems();
		LinkedHashMap<Integer, Integer> itemsNum = it.getItemsNum();
		LinkedHashMap<Integer, Integer> prices = it.getItemsPrices();
		LinkedHashMap<Integer, String> file_paths = it.getFilePaths();
		File f = new File("/home/ilya/Desktop/apache-tomcat-9.0.65/webapps/CourseServlet/ServletImages/" + title);

		assertEquals(false, f.exists());
		assertEquals(false, items.containsKey(0));
		assertEquals(false, itemsNum.containsKey(0));
		assertEquals(false, prices.containsKey(0));
		assertEquals(false, file_paths.containsKey(0));
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    @Test
    public void ReadandWriteFileTest()
    {
    	assertEquals(true, ReadandWriteFileTestInf());
    }
    
    public static boolean ReadandWriteFileTestInf(){
    
    	try{
	    	boolean flag = true;
	    	
	    	ItemsManager it = new ItemsManager();
	    	String title = "Car";
	    	int price = 67000;
	    	int amount = 4;
	    	String path = "ServletImages/Car";
	    	it.Add(title, price, amount);
	    	String title2 = "Phone";
		int price2 = 6700;
	    	int amount2 = 10;
	    	String path2 = "ServletImages/Phone";
	    	it.Add(title2, price2, amount2);
	    	it.writeFile();
	    	it.readFile();
	    	LinkedHashMap<Integer, String> items = it.getItems();
		LinkedHashMap<Integer, Integer> itemsNum = it.getItemsNum();
		LinkedHashMap<Integer, Integer> prices = it.getItemsPrices();
		LinkedHashMap<Integer, String> file_paths = it.getFilePaths();
		
		int [] k1 = new int[2];
		int [] k2 = new int[2];
		int [] k3 = new int[2];
		int [] k4 = new int[2];
		
		int k = 0;
		
		for(int key : items.keySet()){
			 k1[k] = key;
			 k++;
		}
			 
		k = 0; 
		for(int key : itemsNum.keySet()){
			 k2[k] = key;
			 k++;
	       }
	       
		k = 0;
		for(int key : prices.keySet()){
			 k3[k] = key;
			 k++;
		}
			
		k = 0;	 
		for(int key : file_paths.keySet()){
			 k4[k] = key;
			 k++;
		}
		
		
			if(!(items.size() == 2 && itemsNum.size() == 2 && prices.size() == 2 && file_paths.size() == 2 && k1[0] == 0 && k2[0] == 0 && k3[0] == 0 && k4[0] == 0))
				flag = false;
			if(!(items.get(k1[0]).equals(title) && itemsNum.get(k2[0]) == amount && prices.get(k2[0]) == price && file_paths.get(k4[0]).equals(path)))
					flag = false;
				
			
			
			if(!(items.size() == 2 && itemsNum.size() == 2 && prices.size() == 2 && file_paths.size() == 2 && k1[1] == 1 && k2[1] == 1 && k3[1] == 1 && k4[1] == 1))
				flag = false;
				
			if(!(items.get(k1[1]).equals(title2) && itemsNum.get(k2[1]) == amount2 && prices.get(k2[1]) == price2 && file_paths.get(k4[1]).equals(path2)))
					flag = false;
				
				
	
			
				
		if(flag)
			return true;
        	
       }
       catch(Exception e){
       		e.printStackTrace();
       } 
        	
        return false;
    
    }
    
    
    @Test
    public void SaveImageTest()
    {
    	try{
	    	String title = "TV";
	    	FileInputStream fs = new FileInputStream(new File("/home/ilya/Downloads/TV.jpeg"));
	    	ItemsManager it = new ItemsManager();
	    	it.SaveImage(fs, title);
	    	File f = new File("/home/ilya/Desktop/apache-tomcat-9.0.65/webapps/CourseServlet/ServletImages/" + title);
	    	assertEquals(true, f.exists());
	    	File file = new File("/home/ilya/Desktop/apache-tomcat-9.0.65/webapps/CourseServlet/ServletImages/" + title);
		file.delete();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
   
    
    
}
