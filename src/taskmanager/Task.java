package taskmanager;

import java.time.LocalDate;

public class Task {
	
	private String description;
	private LocalDate date;
	private boolean status;
	private int interval = 0; // 0 => no reoccurrances
			
	public Task (String de, LocalDate da){
		this.status = false;
		this.date = da;
		this.description = de;	
	}

	public String getDescription() {
		return description;
	}
	
	public LocalDate getDate() {
		if (date.isBefore(LocalDate.now()) && interval != 0 ) date = date.plusDays(interval);
		return date;
	}

	public Boolean getStatus(){
		return status;
	}
	
	public void setStatus(Boolean s) {
		this.status = s;
	}

	public void setInterval(int i){
		this.interval = i;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Task)) return false;
		Task p = (Task)obj;
		return ((status == p.status) && (description == p.description) && (date.compareTo(p.date)==0));		
	}

	@Override
	public String toString() {
		return "Task [description=" + description + ", date=" + date + ", status=" + status + "]";
	}
}