pipeline {
    environnement {
        TOKEN = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6MDMxYzNhY2QtNzIwZi00MDViLThmMzQtODRlZDBjZmQwNGU4IiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NDM2ODYzNzgsImV4cCI6MTc0Mzc3Mjc3OCwiYXVkIjoiNjIwNUZCQTA0QUI0NDE3RDhCOTYwRTk5RTU1RkNDMzUiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiI2MjA1RkJBMDRBQjQ0MTdEOEI5NjBFOTlFNTVGQ0MzNSJ9.0rRFesEhBpmz8K6IGDA125v-BWugTEMFFCGtsP-SYBI'
        PATH_CUCUMBER_FILE = 'target/cucumber.json'
    }
    agent any
    stages {
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