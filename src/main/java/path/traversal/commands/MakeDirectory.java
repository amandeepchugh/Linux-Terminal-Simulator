package path.traversal.commands;

import javassist.NotFoundException;
import path.traversal.directory.Directory;
import path.traversal.directory.DirectoryFactory;

public class MakeDirectory implements Command {


	public static final String COMMAND_NAME = "mkdir";
	private Directory workingDirectory;
	
	public void run(String input) throws NotFoundException {
		//TODO: parse argument
		//parse argument, check if path is absolute or relative, then take action accordingly
		String[] arguments = input.split(" ");
		
		if (arguments.length < 2) {
			throw new NotFoundException(input + ": " + "Argument Not Found");
		}
		
		for (int i = 1; i < arguments.length; i++) {
			
			String directoryPathToCreate = arguments[i];
			
			validatePath(directoryPathToCreate);
			
			boolean createFlag = false;
			if (isPathAbsolute(directoryPathToCreate)) {
				
				Directory transitDirectory = workingDirectory;
				while ( ! transitDirectory.getParent().equals(transitDirectory) ) {
					transitDirectory = transitDirectory.getParent();
				}
				Directory rootDirectory = transitDirectory;
				
				createFlag  = createDirectoryPath(directoryPathToCreate, rootDirectory);
				
			} else {
				
				createFlag  = createDirectoryPath(directoryPathToCreate, workingDirectory);
				
			}
			String output;
			if (createFlag == false) {
				 output = CommandConstants.ERROR_PREFIX + ": " + directoryPathToCreate + " " + "directory already exists";
				System.err.println(output);
			} else {
				output = CommandConstants.SUCCESS_PREFIX + ": " + directoryPathToCreate + " " + "created";
				System.out.println(output);
			}
		}
		
	}

	private void validatePath(String directoryPathToCreate) {
		
		//TODO: later, path validation
		
	}

	private boolean createDirectoryPath(String directoryPathToCreate, Directory parentDirectory) {
		if (directoryPathToCreate.charAt(0) == '/') {
			directoryPathToCreate = directoryPathToCreate.substring(1);
		}
		String[] directoryNamesInPath = directoryPathToCreate.split("/");
		
		boolean createFlag = false;
		for (String directoryName : directoryNamesInPath) {
			if (parentDirectory.hasDirectory(directoryName)) {
				continue;
			} else {
				parentDirectory.addSubDirectory(DirectoryFactory.createDirectory(directoryName, parentDirectory));
				createFlag = true;
			}
		}
		return createFlag;
	}

	private boolean isPathAbsolute(String string) {
		// TODO Auto-generated method stub
		return true;
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
