package taskmanager;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasklist{
	
	public List<Task> tasks;
	
	public Tasklist(){
		tasks = new ArrayList<Task>();
	}
	
	static Comparator<Task> byStatus = (t1, t2 ) -> t1.getStatus().compareTo(t2.getStatus());
	static Comparator<Task> byDate = (t1, t2 ) -> t1.getDate().compareTo(t2.getDate());
	static Comparator<Task> byDescription = (t1, t2 ) -> t1.getDescription().compareTo(t2.getDescription());
	
	public void add(Task t){
		tasks.add(t);
	}
	
	public void get(int i){
		this.tasks.get(i);
	}
	
	public void remove(int i){
		this.tasks.remove(i);
	}
	
	// SEARCH
	public Tasklist getByDescription(String s){
		Tasklist tl = new Tasklist();
		tasks.stream().filter(t -> t.getDescription().contains(s)).forEach(t -> tl.add(t));
		return tl;
	}
	
	public Tasklist getOpen(boolean b) {
		Tasklist tl = new Tasklist();
		tasks.stream().filter(t -> t.getStatus().equals(b)).forEach(t -> tl.add(t));
		return tl;
	};
	
	public Tasklist dueUntil(LocalDate d){
		Tasklist tl = new Tasklist();
		tasks.stream().filter(t -> t.getDate().isBefore(d.plusDays(1))).forEach(t -> tl.add(t));
		return tl;
	};
	
	// SORT
	public Tasklist sortByDate(){
		tasks = tasks.stream().sorted(byDate.thenComparing(byDescription)).collect(Collectors.toList());
		return this;
	};
	
	public Tasklist sortByStatus(){
		tasks = tasks.stream().sorted(byStatus.thenComparing(byDate)).collect(Collectors.toList());
		return this;
	}
	
	// Comparator
	public boolean equals(Object obj){
		if (obj == null) return false;
		if (!(obj instanceof Tasklist)) return false;
		
		Tasklist p = (Tasklist)obj;
		// DEBUG System.out.println(p.tasks.toString()+" "+ tasks.toString());
		if ( tasks.equals(p.tasks)) return true;
		return false;
	}
	
	// Output
	public void printAll(){
		System.out.println("--------Start--------");
		tasks.stream().forEach(t -> System.out.println(tasks.indexOf(t)+": "+t.toString()));
		System.out.println("---------End---------");
	}
	
	public void writeToFile(){
		Path file = Paths.get("test.out");
		Iterator it = tasks.iterator();
		List lines = new ArrayList<String>();
		
		while ( it.hasNext()){
			lines.add(it.next().toString());
		}
		try {
			//Files.createFile(file);
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}