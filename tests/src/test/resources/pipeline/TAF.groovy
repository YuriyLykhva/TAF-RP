package pipeline
pipeline {
    agent any
    triggers {
        cron('H 8 * * *')
    }
    stages {
        stage('Cleanup') {
            steps {
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
                script {
                    currentBuild.displayName = "This is a build #${BUILD_NUMBER}"
                    currentBuild.description = "Here is some useful description"
                }
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
            }
        }
    }
}
