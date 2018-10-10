package path.traversal.command;

import javax.xml.bind.ValidationException;

import javassist.NotFoundException;
import path.traversal.directory.Directory;
import path.traversal.directory.DirectoryFactory;
import path.traversal.directory.DirectoryUtils;
import path.traversal.directory.Exception.DirectoryAlreadyExistsException;

public class MakeDirectory implements Command {


	public static final String COMMAND_NAME = "mkdir";
	private Directory workingDirectory;

	public void run(String input) throws NotFoundException {

		String[] arguments = input.split(" ");

		if (arguments.length < 2) {
			throw new NotFoundException(input + ": " + "Argument Not Found");
		}

		for (int i = 1; i < arguments.length; i++) {

			String directoryPathToCreate = arguments[i];

			Directory parentDirectory = DirectoryUtils.getParentDirectory(directoryPathToCreate, workingDirectory);

			String output;
			try {
				createDirectoryPath(directoryPathToCreate, parentDirectory);
				output = CommandConstants.SUCCESS_PREFIX + ": " + directoryPathToCreate + " " + "created";
				System.out.println(output);
			} catch (ValidationException e) {
				output = CommandConstants.ERROR_PREFIX + ": " + e.getMessage();
				System.err.println(output);
			} catch (DirectoryAlreadyExistsException e) {
				output = CommandConstants.ERROR_PREFIX + ": " + e.getMessage();
				System.err.println(output);				
			}

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

	private void createDirectoryPath(String directoryPathToCreate, Directory parentDirectory) throws ValidationException, DirectoryAlreadyExistsException {
		if (directoryPathToCreate.charAt(0) == '/') {
			directoryPathToCreate = directoryPathToCreate.substring(1);
		}
		String[] directoryNamesInPath = directoryPathToCreate.split("/");
		DirectoryUtils.validateDirectoryNames(directoryNamesInPath);
		boolean createFlag = false;

		for (String directoryName : directoryNamesInPath) {
			if (parentDirectory.hasDirectory(directoryName)) {
				continue;
			} else {
				parentDirectory.addSubDirectory(DirectoryFactory.createDirectory(directoryName, parentDirectory));
				createFlag = true;
			}
		}
		if (createFlag == false) {
			throw new DirectoryAlreadyExistsException(directoryPathToCreate + ": " + "Directory already exists");
		}

	}

}
