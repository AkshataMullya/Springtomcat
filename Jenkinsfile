pipeline {
	agent any
	tools {
		maven 'maven3.8.5'
	}
	stages {		
		stage('Project Checkout') {
			steps{
			 git  branch:'master', url: 'https://github.com/AkshataMullya/Springtomcat.git'	
		}
		}
		stage('Build Package') {
			steps{
		   		sh 'maven clean package'
			}
}
	}
}
