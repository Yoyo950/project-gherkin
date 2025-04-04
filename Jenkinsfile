pipeline {
    environment {
        JIRA_IDS = 'JIRA_IDS'
        withCredentials([usernamePassword(credentialsId: JIRA_IDS, usernameVariable: 'JIRA_USER', passwordVariable: 'JIRA_PSWD')])
        TOKEN = bat(script: 'curl -H "Content-Type: application/json" -X POST --data "{ \"client_id\": \"%JIRA_USER%\",\"client_secret\": \"%JIRA_PSWD%\" }"  https://xray.cloud.getxray.app/api/v2/authenticate',returnStdout: true)
        PATH_CUCUMBER_FILE = 'target/cucumber.json'
        PATH_ZIP = "features.zip"
        PATH_EXPORT = "src/test/resources/features/distant"
        KEYS = 'POEI25-599;POEI25-600;POEI25-601;POEI25-602;POEI25-603;POEI25-604;POEI25-605'
    }
    agent any
    stages {
        stage('Init') {
            steps {
                sh 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer '+ TOKEN +'" -o '+ PATH_ZIP +' "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys='+KEYS+'"'
                sh 'mkdir -p ' + PATH_EXPORT
                sh 'unzip '+ PATH_ZIP + ' -d ' + PATH_EXPORT
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            cucumber fileIncludePattern: 'target/cucumber.json'
            sh 'curl -H "Content-Type: application/json" -X POST -H "Authorization: Bearer '+ TOKEN +'"  --data @"' + PATH_CUCUMBER_FILE + '" https://xray.cloud.getxray.app/api/v2/import/execution/cucumber'
        }
    }
}