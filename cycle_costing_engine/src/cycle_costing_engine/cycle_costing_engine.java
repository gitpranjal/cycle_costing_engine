package cycle_costing_engine;
import cycle_costing_engine.cycle;
import cycle_costing_engine.cycles_list;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;


class Multithreading extends Thread 
{  
	Multithreading(ArrayBlockingQueue<HashMap<String, String>> queue, cycles_list cycles, String threadType)
	{
		this.queue = queue;
		this.threadType = threadType;
		this.cycles = cycles;
		
	}
	
	ArrayBlockingQueue<HashMap<String, String>> queue;
	cycles_list cycles;
	String threadType;
	String order_date = "22-7-2020";
	String base_date ="22-7-2018";
	int inflation_factor = 2;
	String output_filepath = "/home/pranjal/Desktop/output.txt";
	
	@Override
    public void run() 
    { 
		if(threadType.equals("Insertion"))
		{
			for (HashMap<String, String>map : cycles.cycle_list)
			{
				try {
					queue.put(map);
					System.out.println("Inserted cycle: "+map+" into the queue");
					Thread.sleep(10);
				}
				catch(Exception e)
				{
					System.out.println("Couldn't insert map "+map+" into the queue with exception: "+e);
				}
				
				
			}
		}
		
		else
		{	
			while(true)
			{
				try {
					HashMap<String, String>map = queue.take();
					System.out.println("Queue is non empty with starting element: "+map);
					cycle current_cycle = new cycle(this.order_date, this.base_date,this.inflation_factor,  map, this.output_filepath);
					
				}
				catch(Exception e)
				{
					System.out.println("Couldn't retrieve the first element of the queue with exception: "+e);
				}
			}
		}
    } 
} 


public class cycle_costing_engine {
	
	public static void main(String[] args)
	{

        
		
		int capacity = 1000;
		ArrayBlockingQueue<HashMap<String, String>> queue = new ArrayBlockingQueue<>(capacity);
		String input_filepath = "/home/pranjal/Desktop/cycle_list.txt";
		
		cycles_list cycles = new cycles_list(input_filepath);
		
		Multithreading insertion_thread = new Multithreading(queue, cycles, "Insertion");
		Multithreading output_thread = new Multithreading(queue, cycles, "Output");
		
		insertion_thread.start();
		output_thread.start();
		
		
		
		
	}

}
