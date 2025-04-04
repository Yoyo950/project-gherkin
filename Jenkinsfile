pipeline {
    environment {
        TOKEN = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6MDMxYzNhY2QtNzIwZi00MDViLThmMzQtODRlZDBjZmQwNGU4IiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NDM3NTg2MDQsImV4cCI6MTc0Mzg0NTAwNCwiYXVkIjoiNjIwNUZCQTA0QUI0NDE3RDhCOTYwRTk5RTU1RkNDMzUiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiI2MjA1RkJBMDRBQjQ0MTdEOEI5NjBFOTlFNTVGQ0MzNSJ9.TsaE29tEG8hzYb6SA9__-Qibr-wq79DZzDKklT7OgNE'
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
                sh 'unzip -o '+ PATH_ZIP + ' -d ' + PATH_EXPORT
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