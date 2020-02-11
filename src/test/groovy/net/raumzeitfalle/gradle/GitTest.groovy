package net.raumzeitfalle.gradle;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class GitTest extends Specification {

	/*
	 * TODO: Use JGit to create empty repository w/o tags
	 */
	def "noTagsExisting"() {
		
		Git git = new Git();
		
		when:
			def response = git.describe()
						
		then:
			"fatal: No names found, cannot describe anything.".equalsIgnoreCase(response)
	}

	/*
	 * TODO: Use JGit to create repository with dummy data and tags
	 */
	def "versionNumberTagExists"() {
		
		Git git = new Git();
		
		when:
			def response = git.describe()
						
		then:
			"0.0.1-0-gd0a11e0".equalsIgnoreCase(response)
	}
	
	/*
	 * TODO: Figure out how to perform 'git describe' using JGit 
	 */
	def "gitCommandNotFound"() {
		
		Git git = new Git();
		
		when:
			def response = git.describe()
						
		then:
			response.startsWith("Invocation of 'git' failed.")
	}
			
}