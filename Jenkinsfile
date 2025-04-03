pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Publish Cucumber reports'){
            steps {
                publishCucumberReports fileIncludePattern: 'target/cucumber.json'
            }
        }
    }
}