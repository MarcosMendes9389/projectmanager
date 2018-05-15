package br.com.projectmanager.process;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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

public class ImportFileCSV {

	

	@Autowired
	private UserService userservice;

	@Autowired
	private ProjectService projectservice;
	
	@Autowired
	private UserProjectService userprojectservice;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyyy");

	private CSVParser csvParser;

	
	public ImportFileCSV(ProjectService projectservice, UserService userservice, UserProjectService userprojectservice){
		this.projectservice = projectservice;
		this.userservice = userservice;
		this.userprojectservice = userprojectservice;
	}
	
	
	public void run(Reader reader){
        try {
		
                csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        
        
                for (CSVRecord csvRecord : csvParser) {
                	
                	if(csvRecord.get(0).startsWith("#")){
                		continue;
                	}
                	
                	//Project
                    String projectname = csvRecord.get(0);

                    if(projectservice.getByNmae(projectname) == null){
                    	
                    	
                		java.sql.Date dateStart = null, dateEnd = null;
                    		dateStart = new java.sql.Date(sdf.parse(csvRecord.get(1)).getTime());  
                    		dateEnd = new java.sql.Date(sdf.parse(csvRecord.get(2)).getTime());                      	
                    	
                    	
                    	Project newProject = new Project(projectname, dateStart,dateEnd,false);
                		projectservice.save(newProject);
                		
                    	
                    }
                    
                    //user Manager Project
                	String userPMNmae = csvRecord.get(3);
                	if(userservice.getByNmae(userPMNmae) == null){
                		
        	        	userservice.save(new User(userPMNmae, csvRecord.get(4),csvRecord.get(5)));
        	        	
        	        	//Associate user project
                		userprojectservice.save( new UserProject(userservice.getByNmae(userPMNmae).getId(), projectservice.getByNmae(projectname).getId(), Role.manager));
                		

                	}
                	
                
                	
                
                	//user employee
                	String useremployee = csvRecord.get(6);
                	if(userservice.getByNmae(useremployee) == null){
                		
        	        	userservice.save(new User(useremployee, csvRecord.get(7),csvRecord.get(9)));
        	        	
        	        	//Associate user project
                		userprojectservice.save(new UserProject(userservice.getByNmae(useremployee).getId(), projectservice.getByNmae(projectname).getId(), Role.employee));
                		
                	}
                
                
                	
                }
            } catch (IOException e ) {
    			e.printStackTrace();
    		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        	
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        }
	
	
}
