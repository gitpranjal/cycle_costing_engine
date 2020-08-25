package cycle_costing_engine;
import java.util.*;
import java.io.*;


public class cycles_list {
	ArrayList<HashMap<String, String>>cycle_list = new ArrayList<>();
	
	public HashMap<String, String>json_to_hashmap(String json_string)
	{
		HashMap<String, String>result = new HashMap<>();
		
		int i =0;
		boolean string_quote_open = false;
		boolean string_quote_closed = true;
		boolean colon_encountered = false;
		String current_key = "";
		String current_value = "";
		
		
		while(i<json_string.length())
		{	
			if(json_string.charAt(i) == ',' || i == json_string.length()-1)
			{
				result.put(current_key, current_value);
				string_quote_open = false;
				string_quote_closed = true;
				colon_encountered = false;
				current_key = "";
				current_value = "";
			}
			
			else if(json_string.charAt(i) == '"' && string_quote_closed && !colon_encountered)
			{
				string_quote_closed = false;
				string_quote_open = true;
				current_key = "";
			}
			else if(json_string.charAt(i) == '"' && string_quote_open && !colon_encountered)
			{
				string_quote_closed = true;
				string_quote_open = false;
	
			}
			
			else if(json_string.charAt(i) == ':')
			{
				colon_encountered = true;
				current_value = "";
			}
			else if(json_string.charAt(i) == '"' && string_quote_closed && colon_encountered)
			{
				string_quote_closed = false;
				string_quote_open = true;
				current_value = "";
			}
			else if(string_quote_open && !colon_encountered && json_string.charAt(i)!= '"')
				current_key += json_string.charAt(i);
			
			else if(string_quote_open && colon_encountered && json_string.charAt(i)!= '"')
				current_value += json_string.charAt(i);
			
			
//			
//			System.out.println("String_quote_open: "+string_quote_open+" , colon_encountered: "+colon_encountered+", "+json_string.charAt(i));
//			System.out.println("Key: "+current_key);
//			System.out.println("Value: "+current_value);
			i++;
				
		}
		return result;
	}
	public cycles_list(String file_path)
	{

	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(file_path);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String line = null;
	        while((line = bufferedReader.readLine()) != null) {
//	        	System.out.println("Line: "+line);
	            HashMap<String, String>map = this.json_to_hashmap(line);
	            this.cycle_list.add(map);
//	            System.out.println("Map: "+map+" added");
	            
	        }
	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + file_path + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + file_path + "'");                  
	        
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	    }
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println(json_to_hashmap("{\"ab\":\"cd\", \"pq\":\"rs\", \"grave\":\"burial\"}"));
//	}

}
