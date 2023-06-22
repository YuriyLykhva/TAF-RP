package pipeline
pipeline {
    agent any
    parameters {
        booleanParam(name: "USE_REMOTE_WEB_DRIVER", defaultValue: true, description: "Should we use REMOTE_WEB_DRIVER instead of run build locally?")
        string(name: "WAIT_TIMEOUT_SECONDS", defaultValue: "15", trim: true, description: "Enter timeout value in sec")
        choice(name: "ENVIRONMENT", choices: ["Prod", "Staging", "Dev"], description: "Choose the environment")
    }
    triggers {
        cron('H 8 * * *')
    }
    stages {
        stage('Cleanup') {
            steps {
                script {
                    currentBuild.displayName = "${ENVIRONMENT} #${BUILD_NUMBER}"
                    currentBuild.description = "Here is some useful description"
                }
                cleanWs()
                checkout scm
            }
        }
        stage('Checkout') {
            steps {
                bat 'git clone https://github.com/YuriyLykhva/TAF-RP.git'
                bat 'git submodule init'
                bat 'git submodule update'
            }
        }
        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Build') {
            steps {
                /*script {
                    currentBuild.displayName = "This is a build #${BUILD_NUMBER}"
                    currentBuild.description = "Here is some useful description"
                }*/
                bat 'mvn compile'
            }
        }
        stage('Sonar') {
            steps {
                bat 'mvn sonar:sonar -D sonar.projectKey=TAF -D sonar.token=squ_ac3cb2cfdc23c673de95ee42877f706d40b978f3'
            }
        }
        stage('Install') {
            steps {
                bat 'mvn install'
                junit 'tests/target/surefire-reports/testng-native-results/junitreports/*.xml'
            }
        }
        stage('Allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'tests/target/allure-results']]
                echo "Hello $params.USE_REMOTE_WEB_DRIVER"
            }
        }
    }
}
