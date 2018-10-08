package path.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javassist.NotFoundException;
import path.traversal.directory.DirectoryFactory;

public class TerminalSimulator {

	private CommandExecutor commandExecutor;

	public static void main(String[] args) throws IOException {

		TerminalSimulator terminalSimulator = new TerminalSimulator();
		terminalSimulator.start();

	}

	private void start() throws IOException {
		//TODO: add logging if time permits
		commandExecutor = new CommandExecutor(DirectoryFactory.getFreshRootDirectory());

		while(true) {

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String input = bufferedReader.readLine().trim();

			if (input.isEmpty()) {
				continue;
			}

			String output;

			try {

				commandExecutor.execute(input);

			} catch (NotFoundException e) {

				output = e.getMessage();
				System.err.println(output);

			}
			
		}

	}

}
