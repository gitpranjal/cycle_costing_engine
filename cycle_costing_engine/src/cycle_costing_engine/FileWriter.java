package cycle_costing_engine;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
//import java.util.*;

public class FileWriter {
	
	public static void main(String[] args)
	{
		String filepath = "src/cycle_costing_engine/cycle_list.txt";
		for(int i =0; i< 1000; i++)
		{
			
			String json_string = String.format("{\"wheels\": \"%d\", \"chains\": \"%d\",\"frame\":\"%d\",  \"handle\":\"%d\", \"id\":\"%d\"}\n", 250, 100, 550, 90, i+1);
			try {
			    Files.write(Paths.get(filepath), json_string.getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			   System.out.println("Exception in writing the line : "+json_string+" with exception: "+e);
			}
		}
	}

}
