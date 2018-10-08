package path.traversal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import javassist.NotFoundException;
import path.traversal.commands.Command;
import path.traversal.commands.CommandConstants;
import path.traversal.directory.Directory;

public class CommandExecutor {

	
	private Map<String, Object> commands;
	private Directory workingDirectory;

	public CommandExecutor(Directory workingDirectory) {

		this.workingDirectory = workingDirectory;

		this.commands = new HashMap<String, Object>();
		Reflections reflections = new Reflections(CommandConstants.COMMANDS_PACKAGE);    
		Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

		for (Class<? extends Command> commandClass : classes) {

			try {
				Command command = commandClass.newInstance();
				this.commands.put(command.getCommandName(), command);

			} catch (InstantiationException | IllegalAccessException e ) {
				// TODO log
				e.printStackTrace();
			} 

		}

	}
	public void execute(String input) throws NotFoundException {

		String strCommand = extractCommand(input);

		if (!commands.containsKey(strCommand)) {
			
			throw new NotFoundException(strCommand + ": " + "Command Not Found");
			
		}

		Command command = (Command) commands.get(strCommand);
		command.setWorkingDirectory(workingDirectory);
		command.run(input);
		workingDirectory = command.getWorkingDirectory();

	}
	private String extractCommand(String input) {
		return input.split(" ")[0]; 
	}

}

