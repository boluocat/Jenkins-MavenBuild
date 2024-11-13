node(){
	
	stage('Code Checkout'){
		checkout scm
	}
	
	stage('Build Automation'){
		bat '''
			dir
                        mvn clean install -Dmaven.test.skip=true
			dir
		'''
	}

	stage('Test Cases Execution'){
		// bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test'
	}


	stage('Code Scan'){
		// withSonarQubeEnv(credentialsId: 'SonarQubeCreds') {
		// 	sh "${sonarHome}/bin/sonar-scanner"
		// }
	}

	stage('Archive Artifacts'){
		// archiverArtifacts artifacts: 'target/*.war'
	}
	
	stage('Code Deployment'){
		deploy adapters: [tomcat9(credentialsId: 'TomcatCreds', path: '', url: 'http://34.55.246.245:8080/')], contextPath: 'test_app', onFailure: false, war: 'target/*.war'
	}

	stage('Notification'){
		emailtext(
			subject: "Job Completed",
			body: "Jenkins Pipeline Job for Maven Build got completed",
			to: "boluocat@outlook.com"
			)
	}
}
