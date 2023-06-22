pipeline {
	agent any
	tools {
		maven 'maven-3.8.5'
	}
	stages {		
		stage('Project Checkout') {
			steps{
			 git  branch:'master', url: 'https://github.com/AkshataMullya/Springtomcat.git'	
		}
		}
		stage('Build Package') {
			steps{
		   		sh 'mvn clean package'
			}
}
	}
}
