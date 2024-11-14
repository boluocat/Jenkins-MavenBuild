pipeline {
    agent  any
    
    environment {
        // Define envirnoment variable
        USER_NAME = 'WAWA'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo 'Perparing to Checkout'
                }

                // Checkout the code from SCM (Git)
                git branch: 'master', url :'https://github.com/boluocat/Jenkins-MavenBuild.git'
            }
        }

        stage('Run Script') {
            steps {
                script {
                    echo 'Running script'
                    bat """
                    dir
                    python helloworld.py ${env.USER_NAME}
                    """
                }    
            }
        }
    }

    post {
        always {
            // Actions to perform regardless of pipeline success/failure
            echo 'Cleaning up ...'
            cleanWs()
        }

        success {
            // Actions to perform on pipeline success
            echo 'Pipeline succeeded'
        }

        failure {
            // Action to perform on pipeline failure
            echo 'Pipeline failed'
        }
    }
}
