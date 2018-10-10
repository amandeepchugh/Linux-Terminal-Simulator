package path.traversal.directory;

import path.traversal.SoftLink;

public class DirectoryFactory {

	

	public static Directory getFreshRootDirectory() {

		Directory freshRootDirectory = new Directory();
		freshRootDirectory.setRoot(true);
		freshRootDirectory.setPath(DirectoryConstants.ROOT_PATH);
		freshRootDirectory.setName("");// TODO: might need to revisit again for the name

		addParentSoftLink(freshRootDirectory, freshRootDirectory);

		return freshRootDirectory;

	}

	public static Directory createDirectory(String directoryName, Directory parentDirectory) {
		
		Directory directory = new Directory();
		directory.setName(directoryName);
		if (parentDirectory.isRoot()) {
			directory.setPath("/" + directoryName);
		} else {
			directory.setPath(parentDirectory.getPath() + "/" + directoryName);
		}
		addParentSoftLink(directory, parentDirectory);
		
		return directory;
		
	}

	private static void addParentSoftLink(Directory directory, Directory parentDirectory) {

		SoftLink parent = new SoftLink();
		parent.setName(DirectoryConstants.SOFT_LINK_PARENT_NAME);
		parent.setDirectoryReference(parentDirectory);
		directory.addSoftLink(parent);

	}



}
