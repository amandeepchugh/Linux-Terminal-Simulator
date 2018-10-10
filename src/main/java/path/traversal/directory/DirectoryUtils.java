package path.traversal.directory;

import javax.xml.bind.ValidationException;

import javassist.NotFoundException;

public class DirectoryUtils {

	public static boolean isPathAbsolute(String path) {

		if (path.charAt(0) == '/') {
			return true;
		} else {
			return false;
		}

	}

	public static void validateDirectoryNames(String[] directoryNames) throws ValidationException {

		for (String directoryName : directoryNames) {
			if (!directoryName.matches("[A-Za-z0-9]+")) {
				throw new ValidationException(directoryName + ": " + "only alphanumeric directory names allowed");
			}
		}

	}
	public static Directory getParentDirectory(String directoryPathToChange, Directory workingDirectory)
			throws NotFoundException {
		Directory transitDirectory = workingDirectory;
		if (DirectoryUtils.isPathAbsolute(directoryPathToChange)) {

			while ( ! transitDirectory.getParent().equals(transitDirectory) ) {
				transitDirectory = transitDirectory.getParent();
			}

		}
		return transitDirectory;
	}
}
