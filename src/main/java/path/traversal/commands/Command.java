package path.traversal.commands;

import javassist.NotFoundException;
import path.traversal.directory.Directory;

public interface Command {
	
	void run(String input) throws NotFoundException;
	
	String getCommandName();

	void setWorkingDirectory(Directory workingDirectory);

	Directory getWorkingDirectory();
	
}
