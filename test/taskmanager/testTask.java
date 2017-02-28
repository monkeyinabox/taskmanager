package taskmanager;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Test;

public class testTask{
	@Test
	public void testGetDescription() {
		Task t = new Task("Test", LocalDate.of(2017, Month.DECEMBER, 17));
		assertEquals("Test",t.getDescription());	
	}
	@Test
	public void testGetSetStatus() {
		Task t = new Task("Test", LocalDate.of(2017, Month.DECEMBER, 17));
		assertFalse(t.getStatus());
		t.setStatus(true);
		assertTrue(t.getStatus());
	}
	@Test
	public void testSelectByDescription() {		
		Tasklist tl = new Tasklist();
		tl.add(new Task("T1", LocalDate.of(2017, Month.DECEMBER, 17)));
		tl.add(new Task("T2", LocalDate.of(2017, Month.NOVEMBER, 17)));
		assertTrue(tl.equals(tl.getByDescription("T")));
		assertFalse(tl.equals(tl.getByDescription("T2")));
	}
	@Test
	public void testEquals() {		
		Tasklist tl0 = new Tasklist();
		tl0.add(new Task("T2", LocalDate.of(2013, Month.DECEMBER, 17)));
		tl0.add(new Task("T5", LocalDate.of(2017, Month.NOVEMBER, 17)));
		tl0.add(new Task("T6", LocalDate.of(2015, Month.NOVEMBER, 17)));
		
		Tasklist tl1 = new Tasklist();
		tl1.add(new Task("T4", LocalDate.of(2013, Month.DECEMBER, 17)));
		tl1.add(new Task("T5", LocalDate.of(2017, Month.NOVEMBER, 17)));
		tl1.add(new Task("T6", LocalDate.of(2015, Month.NOVEMBER, 17)));
		tl0.sortByDate();
		assertTrue(tl0.equals(tl0));
		assertFalse(tl0.equals(tl1));
	}
	@Test
	public void testInterval(){
		Tasklist tl = new Tasklist();
		tl.add(new Task("IT", LocalDate.now().minusDays(1)));
		tl.tasks.get(0).setInterval(7);
		assertEquals(LocalDate.now().plusDays(6), tl.tasks.get(0).getDate());
	}
}


