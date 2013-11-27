package Models;


import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity 
public class Task extends Model {

	@Id
	@Constraints.Min(10)
	public Long id;

	@Constraints.Required
	public String label;

	@Constraints.Required
	public String project;

	@Constraints.Required
	public int priority;

	public boolean done;

	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date dueDate = new Date();

	public static Finder<Long,Task> find = new Finder<Long,Task>(
			Long.class, Task.class
			); 
	public static List<Task> all() {
		List<Task> bdList = find.all();
		Comparator<Task> comp = new Comparator<Task>(){
			@Override
			public int compare(Task t1, Task t2){
				return t1.priority - t2.priority;
			}
		};
		Collections.sort(bdList, comp);
		return bdList;
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}


	public static void updateStatus(Long id) {
		Task task = (Task) find.ref(id);
		task.setDone(! task.isDone());
		task.update();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long newId) {
		id = newId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String newLabel) {
		label = newLabel;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String newProject) {
		project = newProject;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int newPriority) {
		priority = newPriority;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean d) {
		done = d;
	}

}