pipeline {
    parameters {
            string(name: 'TEST_KEYS', defaultValue: '', description: 'The keys of the tests imported and executed')
            choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: 'The environment used')
            choice(name: 'BROWSER', defaultValue: ['chrome', 'edge', 'firefox'], description: 'The browser used for the tests')
            booleanParam(name: 'IS_HEADLESS', defaultValue: false, description: 'Define if we run in headless')
    }

    environment {
        TOKEN = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6MDMxYzNhY2QtNzIwZi00MDViLThmMzQtODRlZDBjZmQwNGU4IiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NDM3NTg2MDQsImV4cCI6MTc0Mzg0NTAwNCwiYXVkIjoiNjIwNUZCQTA0QUI0NDE3RDhCOTYwRTk5RTU1RkNDMzUiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiI2MjA1RkJBMDRBQjQ0MTdEOEI5NjBFOTlFNTVGQ0MzNSJ9.TsaE29tEG8hzYb6SA9__-Qibr-wq79DZzDKklT7OgNE'
        PATH_CUCUMBER_FILE = 'target/cucumber.json'
        PATH_ZIP = "features.zip"
        PATH_EXPORT = "src/test/resources/features/distant"
        BRWSR = BROWSER
        HL = IS_HEADLESS
    }
    agent any

    parameters {
        string(name: 'TEST_KEYS', defaultValue: '', description: 'The keys of the tests imported and executed')
        choice(name: 'ENV', choices: ['dev', 'staging', 'prod'], description: 'The environment used')
        choice(name: 'BROWSER', defaultValue: ['chrome', 'edge', 'firefox'], description: 'The browser used for the tests')
        booleanParam(name: 'IS_HEADLESS', defaultValue: false, description: 'Define if we run in headless')
    }

    stages {
        stage('Init') {
            steps {
                script {
                    bat """
                        curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer %TOKEN%" -o %PATH_ZIP% "https://xray.cloud.getxray.app/api/v2/export/cucumber?keys=%TEST_KEYS%"
                        mkdir -p %PATH_EXPORT%
                        unzip -o %PATH_ZIP% -d %PATH_EXPORT%
                    """
                }
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