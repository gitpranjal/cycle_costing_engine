package cycle_costing_engine;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class cycle {
	
	public cycle(String order_date, String base_date, int unit_inflation_factor,  HashMap<String, String>part_costs, String output_filepath)
	{
		this.order_date = order_date;
		this.cycle_cost = 0;
		
		int base_year = Integer.parseInt(base_date.split("-")[2]); 
		int base_month = Integer.parseInt(base_date.split("-")[1]);
		
		int order_year = Integer.parseInt(order_date.split("-")[2]); 
		int order_month = Integer.parseInt(order_date.split("-")[1]);
		
		int inflation_factor = ((order_month - base_month)/12 + (order_year - base_year))*unit_inflation_factor;
		
		for(String s: part_costs.keySet())
		{
			if(s.toLowerCase().equals("id") || s.toLowerCase().contains("date"))
				continue;
			
			cycle_cost += Integer.parseInt(part_costs.get(s))*inflation_factor;
		}
		
		String output = String.format("Cycle with id %s created on %s with cost %s \n", part_costs.get("id"), this.order_date, this.cycle_cost);
		
		
		try {
		    Files.write(Paths.get(output_filepath), output.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		   System.out.println("Exception in writing the line : "+output+" with exception: "+e);
		}
		
	}
	
	
	String order_date;
	int cycle_cost;
}
