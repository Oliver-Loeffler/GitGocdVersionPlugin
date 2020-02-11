package net.raumzeitfalle.gradle;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class GocdTest extends Specification {

	def "isNotAutomated_whenCounterIsEmpty"() {
		
		when:
			Map<String,String> env = new HashMap();
			env.putAt("GO_PIPELINE_COUNTER", "")
		
			Gocd gocd = new Gocd(env);
			def isAutomated = gocd.isAutomatedBuild();
			def pipelineCount = gocd.getPipelineCount();
						
		then:
			isAutomated == false;
			"".equalsIgnoreCase(pipelineCount)
	}
	
	def "isNotAutomated_whenApiTokenNotSet"() {
		
		when:
			Map<String,String> env = new HashMap();
		
			Gocd gocd = new Gocd(env);
			def isAutomated = gocd.isAutomatedBuild();
			def pipelineCount = gocd.getPipelineCount();
			
		then:
			isAutomated == false;
			"".equalsIgnoreCase(pipelineCount)
		
	}
	
	def "isAutomated_whenApiTokenIsSet"() {
		
		when:
			Map<String,String> env = new HashMap();
			env.putAt("GO_PIPELINE_COUNTER", "42")
			
			Gocd gocd = new Gocd(env);
			def isAutomated = gocd.isAutomatedBuild();
			def pipelineCount = gocd.getPipelineCount();
			
		then:
			isAutomated == true;
			"42".equalsIgnoreCase(pipelineCount)
		
	}
		
}