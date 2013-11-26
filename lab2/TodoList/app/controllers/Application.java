package controllers;

import java.sql.Connection;

import javax.sql.DataSource;

import play.*;
import play.mvc.*;
import Models.*;
import views.html.*;
import play.data.*;
import play.db.*;


public class Application extends Controller {
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	
	static Form<Task> taskForm = Form.form(Task.class);
    public static Result index() {
        return redirect(routes.Application.tasks());
    }
    public static Result tasks() {
    	return ok(views.html.index.render(Task.all(), taskForm));
    }
    
    public static Result newTask() {
    	Form<Task> filledForm = taskForm.bindFromRequest();
    	if(filledForm.hasErrors()) {
    		return badRequest(views.html.index.render(Task.all(), filledForm));
    	} else {
    	    Task.create(filledForm.get());
    	    return redirect(routes.Application.tasks());  
    	}
   }
      
    public static Result deleteTask(Long id) {
    	  Task.delete(id);
    	  return redirect(routes.Application.tasks());
    }
}