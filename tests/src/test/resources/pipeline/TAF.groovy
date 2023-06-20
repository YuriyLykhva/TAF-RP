package pipeline
pipeline {
    agent any
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
        stage('Sonar') {
            steps {
                bat 'mvn clean verify sonar:sonar -D sonar.projectKey=TAF -D sonar.token=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'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        /*stage('Install') {
            steps {
                bat 'mvn install'
            }
        }*/
        stage('Allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'tests/target/allure-results']]
            }
        }
    }
}
