package net.raumzeitfalle.gradle;

import java.util.Map;

public class GitGocdVersion {
	
	private final Git git;
	
	private final Gocd gocd;
	
	public GitGocdVersion() {
		this(System.getenv());
	}
	
	public GitGocdVersion(Map<String,String> env) {
		this.git = new Git();
		this.gocd = new Gocd(env);
		
	}
	
	public String buildVersionString() {
		
		String description = this.git.describe();
		String buildNumber = this.gocd.getPipelineCount();
		
		// TODO: Move this into Git class
		String[] scmVer = description.split("-");
		
		String mainVersion 	= scmVer[0];
		String patch 		= scmVer[1];
		String commitId 	= scmVer[2];
		
		String version = mainVersion + "." + patch + "-" + commitId + "." + buildNumber;
		if (this.gocd.isAutomatedBuild()) {
			version =  mainVersion + "." + patch + "." + buildNumber;
		}
		
		System.out.println("Version to build: " + version);
		return version;
	}
	
}
