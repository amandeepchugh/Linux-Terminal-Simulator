package path.traversal.command;

import path.traversal.directory.Directory;

public class PresentWorkingDirectory implements Command {

	public static final String COMMAND_NAME = "pwd";
	private Directory workingDirectory;
	
	public void run(String input) {
		
		System.out.println(workingDirectory.getPath());
		
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public Directory getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(Directory workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

}
