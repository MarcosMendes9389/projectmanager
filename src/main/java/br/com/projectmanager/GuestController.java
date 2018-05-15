package br.com.projectmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.projectmanager.model.Project;
import br.com.projectmanager.model.Role;
import br.com.projectmanager.model.User;
import br.com.projectmanager.model.UserProject;
import br.com.projectmanager.process.ImportFileCSV;
import br.com.projectmanager.service.ProjectService;
import br.com.projectmanager.service.RoleService;
import br.com.projectmanager.service.UserProjectService;
import br.com.projectmanager.service.UserService;
import br.com.projectmanager.util.UserRoleUtil;


/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Controller
public class GuestController {

	
	public static MultipartFile file;
	
	@Autowired
	private UserService userservice;

	@Autowired
	private ProjectService projectservice;
	
	@Autowired
	private RoleService roleservice;
	
	@Autowired
	private UserProjectService userprojectservice;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}

	
//User	
	
	@RequestMapping("/usermenu")
	public String userMenu(){
		return "usermenu";
	}

	

	@RequestMapping("/adduser")
	public String addUser(){
		return "adduser";
	}
	

	@RequestMapping("/userlist")
	public String userList(Model model){
		Iterable<User> users = userservice.getAll();
		model.addAttribute("users",users);
		return "userlist";
	}
	
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String saveUser(@RequestParam("name") String name, @RequestParam("email") String email, 
			@RequestParam("skills") String skills){
		
		User newUser = new User(name, email, skills);
		userservice.save(newUser);
		
		return "usermenu";
	}

		
	
	
//Project	
	
	@RequestMapping("/projectmenu")
	public String projectPenu(){
		return "projectmenu";
	}
	
	
	@RequestMapping("/addproject")
	public String addProject(){
		return "addproject";
	}
	
	
	@RequestMapping("/projectlist")
	public String projectList(Model model){
		
		//Projects	
		Iterable<Project> projects = projectservice.getAll();
		model.addAttribute("projects",projects);

		
		//All User Projects
		ArrayList<UserRoleUtil> userroletutils = new ArrayList<UserRoleUtil>() ;
		
		for(UserProject projectuser: userprojectservice.getAll()){
			
			UserRoleUtil userroleutil = new UserRoleUtil();

			userroleutil.setRolename(roleservice.getById(projectuser.getRole_id()).getName());
			userroleutil.setUsername(userservice.getById(projectuser.getUser_id()).getName());
			
			userroletutils.add(userroleutil);
		}
		
		model.addAttribute("userroletutils",userroletutils);
		
			
		return "projectlist";
	}
	
	
	@RequestMapping(value = "/saveproject", method = RequestMethod.POST)
	public String saveProject(@RequestParam("name") String name, @RequestParam("plannedstart") Date plannedstart, 
			@RequestParam("plannedend") Date plannedend){
		
		Project newProject = new Project(name, plannedstart, plannedend, false);
		projectservice.save(newProject);
		
		return "projectmenu";
	}

	

	@RequestMapping(value = "/projecttofile")
	public String projectToFile(Model model){
		
		ArrayList<Project> projectlist = new ArrayList<Project>();
		
		for(Project project: projectservice.getAll()){
			if(!project.isFiled()){
				projectlist.add(project);
			}
			
		}		
		model.addAttribute("projects",projectlist);
			
		return "projecttofile";
	}

	
	

	@RequestMapping(value = "/saveprojecttofile", method = RequestMethod.POST)
	public String projectToFile(@RequestParam("projectselect")Project project){
		
		project.setFiled(true);
		projectservice.save(project);
		
		return "projectmenu";
	}

	
	
	
	
//User Project
	
	@RequestMapping(value = "/userproject")
	public String userProject(Model model){
		
		Iterable<Project> projects = projectservice.getAll();
		model.addAttribute("projects",projects);

		Iterable<User> users = userservice.getAll();
		model.addAttribute("users",users);
		
		Iterable<Role> roles = roleservice.getAll();
		model.addAttribute("roles",roles);

		
		return "userproject";
	}

	
	

	@RequestMapping(value = "/saveuserproject", method = RequestMethod.POST)
	public String saveUserProject(@RequestParam("projectselect")Project project, @RequestParam("userselect")User user,
			@RequestParam("roleselect")Role role, Model model){
		
		
		for(UserProject userproject:  userprojectservice.getByProjectId(project.getId())){
			if(userproject.getUser_id() == user.getId()){
				model.addAttribute("errormsg","Usuário "+user.getName()+ " já associado ao "+project.getName());
				return "erroruserproject";
			}
			
			if(role.getId()== Role.manager && userproject.getRole_id() == Role.manager){
				model.addAttribute("errormsg","Projeto "+project.getName()+" já possui Gerente");
				return "erroruserproject";
			}
			
		}

		
		UserProject newUserProject = new UserProject(user.getId(), project.getId(), role.getId());
		userprojectservice.save(newUserProject);
		
		return "index";
	}

	


	@RequestMapping(value = "/userprojectlist", method = RequestMethod.POST)
	public String userProjectList(@RequestParam("project")Long id, Model model){
		
		ArrayList<UserRoleUtil> userroles = new ArrayList<UserRoleUtil>();
		

		for(UserProject userproject:  userprojectservice.getAll()){
			if(id == userproject.getProject_id()){
				
				UserRoleUtil userroleutil = new UserRoleUtil();
				
				User user = userservice.getById(userproject.getUser_id()); 
				userroleutil.setUsername(user.getName());
				
				Role role = roleservice.getById(userproject.getRole_id()); 
				userroleutil.setRolename(role.getName());
				
				userroles.add(userroleutil);
			}
			
		}		
		
		
		model.addAttribute("userroles",userroles);
		
		return "userprojectlist";
	}

	
	
	
	
//Import
	@RequestMapping("/importfile")
	public String importFile(){
		return "importfile";
	}

	@RequestMapping("/importfilebatch")
	public String importFileBatch(){
		return "importfilebatch";
	}

	
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;
	

	@RequestMapping(value = "/importcsvfilebatch", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public String importJobBatch() throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException  {
	
		
        JobParameters jobParameters = new JobParametersBuilder().addLong(
				"time", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(processJob, jobParameters);
				
		return "index";
	}



	@RequestMapping(value = "/importcsvfile", method = RequestMethod.POST)
	public String importCSVFile(@RequestParam("file")MultipartFile file){
		
		ImportFileCSV importfile = new ImportFileCSV(projectservice,userservice,userprojectservice);
		
		BufferedReader br = null;
		
			try {
				br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		importfile.run(br);
		
		return "index";
	}

	

	
	
}
