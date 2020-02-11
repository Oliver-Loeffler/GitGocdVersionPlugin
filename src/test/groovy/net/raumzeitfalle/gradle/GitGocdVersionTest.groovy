package net.raumzeitfalle.gradle;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class GitGocdVersionTest extends Specification {

	
	def "noGitTagDefined_noGocd_noComputerName"() {
		
		GitGocdVersion version = new GitGocdVersion(new HashMap());
		
		when:
			def response = version.buildVersionString()
						
		then:
			"UNVERSIONED-CommitID.LOCALBUILD.yyyyMMddHHmmss".equalsIgnoreCase(response)
	}
	
	def "noGitTagDefined_noGocd_withComputerName"() {
		
		GitGocdVersion version = new GitGocdVersion(new HashMap());
		
		when:
			def response = version.buildVersionString()
		
		then:
			"UNVERSIONED-CommitID.HOSTNAME.yyyyMMddHHmmss".equalsIgnoreCase(response)
	}
	
	def "noGitTagDefined_withinGocd"() {
		
		GitGocdVersion version = new GitGocdVersion(new HashMap());
		
		when:
			def response = version.buildVersionString()
						
		// VERSION-CommitID.Build
		then:
			"UNVERSIONED-6bbfae67.42".equalsIgnoreCase(response)
	}

	def "TagDefined_withinGocd"() {
		
		GitGocdVersion version = new GitGocdVersion(new HashMap());
		
		when:
			def response = version.buildVersionString()
						
		// PATCH = # of commits since last tag
		then:
			"TAG.PATCH.42".equalsIgnoreCase(response)
	}
			
}