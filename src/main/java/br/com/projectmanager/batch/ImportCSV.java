package br.com.projectmanager.batch;

/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */
public class ImportCSV {

	private String projectname;
	private String plannedstart ;
	private String plannedend; 
	private String pm ;
	private String pmemail; 
	private String pmskills ; 
	private String employeename ; 
	private String employeeemail;
	private String employeeteam;
	private String employeeskills ;
	
	public ImportCSV() {

	}

	public ImportCSV(String projectname,String	plannedstart, String   plannedend, String   pm,
					 String pmemail,String pmskills,String employeename,String  employeeemail,
					 String   employeeteam, String employeeskills) {
		
		
		this.projectname = projectname;
		this.plannedstart=plannedstart;
		this.plannedend = plannedend;
		this.pm= pm;		
		this.pmemail = pmemail;
		this.pmskills = pmskills;
		this.employeename = employeename;
		this.employeeemail = employeeemail;
		this. employeeteam =  employeeteam;
		this.employeeskills = employeeskills;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getPlannedstart() {
		return plannedstart;
	}

	public void setPlannedstart(String plannedstart) {
		this.plannedstart = plannedstart;
	}

	public String getPlannedend() {
		return plannedend;
	}

	public void setPlannedend(String plannedend) {
		this.plannedend = plannedend;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getPmemail() {
		return pmemail;
	}

	public void setPmemail(String pmemail) {
		this.pmemail = pmemail;
	}

	public String getPmskills() {
		return pmskills;
	}

	public void setPmskills(String pmskills) {
		this.pmskills = pmskills;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmployeeemail() {
		return employeeemail;
	}

	public void setEmployeeemail(String employeeemail) {
		this.employeeemail = employeeemail;
	}

	public String getEmployeeteam() {
		return employeeteam;
	}

	public void setEmployeeteam(String employeeteam) {
		this.employeeteam = employeeteam;
	}

	public String getEmployeeskills() {
		return employeeskills;
	}

	public void setEmployeeskills(String employeeskills) {
		this.employeeskills = employeeskills;
	}

	
}
