pipeline {
	agent any
	tools {
		maven 'maven3.8.5'
	}
	stages {		
		stage('Project Checkout')
			git  branch:'master', url: 'https://github.com/AkshataMullya/Springtomcat.git'	
		}
		stage('Build Package') {
		   'maven clean package'
			}
}
}
