package path.traversal;

import path.traversal.directory.Directory;

public class SoftLink {
	
	private String name;
	private Directory directoryReference;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Directory getDirectoryReference() {
		return directoryReference;
	}
	public void setDirectoryReference(Directory directoryReference) {
		this.directoryReference = directoryReference;
	}

}
