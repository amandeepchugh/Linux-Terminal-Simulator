package path.traversal.command;

import javax.xml.bind.ValidationException;

import javassist.NotFoundException;
import path.traversal.directory.Directory;
import path.traversal.directory.DirectoryUtils;

public class ChangeDirectory implements Command {


	public static final String COMMAND_NAME = "cd";
	private Directory workingDirectory;

	public void run(String input) throws NotFoundException  {

		String[] arguments = input.split(" ");

		if (arguments.length != 2) {
			throw new NotFoundException(input + ": " + "Argument Not Found");
		}

		String directoryPathToChange = arguments[1];

		
		Directory parentDirectory = DirectoryUtils.getParentDirectory(directoryPathToChange, workingDirectory);

		String output;
		Directory changedDirectory;
		try {

			changedDirectory  = changeDirectory(directoryPathToChange, parentDirectory);
			setWorkingDirectory(changedDirectory);
			output = CommandConstants.SUCCESS_PREFIX + ": " + directoryPathToChange + " " + "changed";
			System.out.println(output);

		} catch (NotFoundException | ValidationException e) {
	
			output = CommandConstants.ERROR_PREFIX + ": " + e.getMessage();
			System.err.println(output);

		}

	}

	private Directory changeDirectory(String directoryPathToChange, Directory parentDirectory) throws NotFoundException, ValidationException {
		//TODO: the method design/names needs to be better.
		if (directoryPathToChange.equals("/")) {
			return parentDirectory;
		}
		
		if (directoryPathToChange.charAt(0) == '/') {
			directoryPathToChange = directoryPathToChange.substring(1);
		}
		String[] directoryNamesInPath = directoryPathToChange.split("/");		
		DirectoryUtils.validateDirectoryNames(directoryNamesInPath);

		Directory transitDirectory = parentDirectory;
		for (String directoryName : directoryNamesInPath) {
			transitDirectory = transitDirectory.getSubDirectory(directoryName);
			continue;

		}
		return transitDirectory; 
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
