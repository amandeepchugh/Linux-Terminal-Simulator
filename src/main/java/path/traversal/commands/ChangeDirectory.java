package path.traversal.commands;

import javassist.NotFoundException;
import path.traversal.directory.Directory;
import path.traversal.directory.DirectoryFactory;

public class ChangeDirectory implements Command {


	public static final String COMMAND_NAME = "cd";
	private Directory workingDirectory;

	public void run(String input) throws NotFoundException  {
		
		String[] arguments = input.split(" ");

		if (arguments.length != 2) {
			throw new NotFoundException(input + ": " + "Argument Not Found");
		}

		String directoryPathToChange = arguments[1];
		validatePath(directoryPathToChange);

		Directory parentDirectory;
		Directory changedDirectory;
		if (isPathAbsolute(directoryPathToChange)) {

			Directory transitDirectory = workingDirectory;
			while ( ! transitDirectory.getParent().equals(transitDirectory) ) {
				transitDirectory = transitDirectory.getParent();
			}
			parentDirectory = transitDirectory;
			

		} else {

			parentDirectory = workingDirectory;

		}
		String output;
		try {
			
			changedDirectory  = changeDirectory(directoryPathToChange, parentDirectory);
			setWorkingDirectory(changedDirectory);
			output = CommandConstants.SUCCESS_PREFIX + ": " + directoryPathToChange + " " + "changed";
			System.out.println(output);
			
		} catch (NotFoundException e) {
			
			output = CommandConstants.ERROR_PREFIX + ": " + directoryPathToChange + " " + "directory not found";
			System.err.println(output);
			
		}

	}

	private void validatePath(String directoryPathToCreate) {

		//TODO: later, path validation
		//TODO: move to directoryUtils

	}

	private Directory changeDirectory(String directoryPathToChange, Directory parentDirectory) throws NotFoundException {
		//TODO
		if (directoryPathToChange.charAt(0) == '/') {
			directoryPathToChange = directoryPathToChange.substring(1);
		}
		String[] directoryNamesInPath = directoryPathToChange.split("/");

		Directory transitDirectory = parentDirectory;
		for (String directoryName : directoryNamesInPath) {
				transitDirectory = transitDirectory.getSubDirectory(directoryName);
				continue;
			 
		}
		return transitDirectory; 
	}

	private boolean isPathAbsolute(String string) {
		// TODO Auto-generated method stub
		//TODO: move to directoryUtils
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
