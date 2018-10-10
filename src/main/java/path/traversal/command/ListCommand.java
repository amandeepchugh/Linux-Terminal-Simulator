package path.traversal.command;

import path.traversal.directory.Directory;

public class ListCommand implements Command {

	public static final String COMMAND_NAME = "ls";
	private Directory workingDirectory;
	
	public void run(String input) {
		
		for (Directory directories : workingDirectory.getSubDirectories()) {
			System.out.println(directories.getName());
		}
		
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
