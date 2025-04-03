pipeline {
    environment {
        TOKEN = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6MDMxYzNhY2QtNzIwZi00MDViLThmMzQtODRlZDBjZmQwNGU4IiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NDM2ODYzNzgsImV4cCI6MTc0Mzc3Mjc3OCwiYXVkIjoiNjIwNUZCQTA0QUI0NDE3RDhCOTYwRTk5RTU1RkNDMzUiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiI2MjA1RkJBMDRBQjQ0MTdEOEI5NjBFOTlFNTVGQ0MzNSJ9.0rRFesEhBpmz8K6IGDA125v-BWugTEMFFCGtsP-SYBI'
        PATH_CUCUMBER_FILE = 'target/cucumber.json'
        PATH_ZIP = "features.zip"
        PATH_EXPORT = "test/resources/features/distant"
        KEYS = 'POEI25-599;POEI25-600;POEI25-601;POEI25-602;POEI25-603;POEI25-604;POEI25-605'
    }
    agent any
    stages {
        stage('Init') {
            steps {
                sh 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer '+ TOKEN +'" -o '+ PATH_ZIP +' "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys='+KEYS+'"'
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