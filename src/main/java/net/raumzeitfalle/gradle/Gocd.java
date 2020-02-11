package net.raumzeitfalle.gradle;

import java.util.Map;

public class Gocd {
	
	private static final String API_TOKEN = "GO_PIPELINE_COUNTER";
	
	private final Map<String,String> environment;
	
	public Gocd() {
		this(System.getenv());
	}
	
	public Gocd(Map<String,String> env) {
		this.environment = env;
	}
	
	boolean isAutomatedBuild() {
		return this.environment.containsKey(API_TOKEN) 
		&& !"".equalsIgnoreCase(getPipelineCount());
	}

	public String getPipelineCount() {
		return this.environment.getOrDefault(API_TOKEN,"");
	}
	
}
