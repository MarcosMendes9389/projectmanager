package br.com.projectmanager.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.projectmanager.model.Project;
import br.com.projectmanager.model.Role;
import br.com.projectmanager.model.User;
import br.com.projectmanager.model.UserProject;
import br.com.projectmanager.service.ProjectService;
import br.com.projectmanager.service.UserProjectService;
import br.com.projectmanager.service.UserService;

/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

public class ImportItemProcessor implements
		ItemProcessor<ImportCSV, ImportCSV> {

	@Autowired
	private UserService userservice;

	@Autowired
	private ProjectService projectservice;
	
	
	@Autowired
	private UserProjectService userprojectservice;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyyy");

	
	public ImportCSV process(final ImportCSV importcsv) throws Exception {
		
    	//Project
        String projectname = importcsv.getProjectname();

        if(projectname.startsWith("#")) return importcsv;
        
        if(projectservice.getByNmae(projectname) == null){
        	
        	java.sql.Date dateStart = null, dateEnd = null;
        	dateStart = new java.sql.Date(sdf.parse(importcsv.getPlannedstart()).getTime());  
        	dateEnd = new java.sql.Date(sdf.parse(importcsv.getPlannedend()).getTime());                      	
        	
        	Project newProject = new Project(projectname, dateStart,dateEnd,false);
    		projectservice.save(newProject);
        }
        
        //user Manager Project
    	String userPMNmae = importcsv.getPm();
    	if(userservice.getByNmae(userPMNmae) == null){
    		
        	userservice.save(new User(userPMNmae, importcsv.getPmemail(),importcsv.getPmskills()));
        	//Associate user project
    		userprojectservice.save( new UserProject(userservice.getByNmae(userPMNmae).getId(), projectservice.getByNmae(projectname).getId(), Role.manager));
    		
    	}
    	
    	//user employee
    	String useremployee = importcsv.getEmployeename();
    	if(userservice.getByNmae(useremployee) == null){
    		
        	userservice.save(new User(useremployee, importcsv.getEmployeeemail(),importcsv.getEmployeeskills()));
        	//Associate user project
    		userprojectservice.save(new UserProject(userservice.getByNmae(useremployee).getId(), projectservice.getByNmae(projectname).getId(), Role.employee));
    		
    	}
    
		return importcsv;
	}


}