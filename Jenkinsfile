node('master'){
	
	stage('Code Checkout'){
		// checkout changelog: false, poll: false, scm: scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHubCreds', url: 'https://github.com/anujdevopslearn/MavenBuild']])
		checkout scm
	}
	
	stage('Build Automation'){
		sh """
			ls -lart
			mvn clean install -Dmaven.test.skip=true
			ls -lart target
		"""
	}
	
	// stage('Code Scan'){
	// 	withSonarQubeEnv(credentialsId: 'SonarQubeCreds') {
	// 		sh "${sonarHome}/bin/sonar-scanner"
	// 	}
		
	// }

	stage('Archive Artifacts'){
		archiverArtifacts artifacts: 'target/*.war'
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
