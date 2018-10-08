package path.traversal.directory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javassist.NotFoundException;
import path.traversal.SoftLink;

public class Directory {
	
	private String name;
	private List<Directory> subDirectories;
	private List<SoftLink> softlinks;
	private String path;
	private boolean isRoot;
	
	public Directory() {
		subDirectories = new LinkedList<>();
		softlinks = new ArrayList<>();
	}
	
	public List<Directory> getSubDirectories() {
		return subDirectories;
	}
	public void setSubDirectories(List<Directory> subDirectories) {
		this.subDirectories = subDirectories;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SoftLink> getSoftlinks() {
		return softlinks;
	}
	public void setSoftlinks(List<SoftLink> softlinks) {
		this.softlinks = softlinks;
	}
	
	public void addSubDirectory(Directory directoryToAdd) {
		this.subDirectories.add(directoryToAdd);
	}
	public void addSoftLink(SoftLink softLinkToAdd) {
		this.softlinks.add(softLinkToAdd);
	}

	public Directory getParent() throws NotFoundException {
		for (SoftLink softLink : softlinks) {
			if (softLink.getName() == DirectoryConstants.SOFT_LINK_PARENT_NAME) {
				return softLink.getDirectoryReference();
			}
		}
		throw new NotFoundException("Directory parent not found");
	}

	public boolean hasDirectory(String directoryName) {
		for (Directory subDirectory :  subDirectories) {
			if (subDirectory.getName().equals(directoryName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public Directory getSubDirectory(String directoryName) throws NotFoundException {
		for (Directory subDirectory :  subDirectories) {
			if (subDirectory.getName().equals(directoryName)) {
				return subDirectory;
			}
		}
		throw new NotFoundException(directoryName + ": " + "Directory not found");
	}

}
