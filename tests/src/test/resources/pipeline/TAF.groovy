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
     //           bat 'mvn clean verify sonar:sonar -D sonar.projectKey=TAF -D sonar.token=sqp_0a464b246e1825d6aef1fddee8d714f6764bde1c'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Install') {
            steps {
                bat 'mvn install'
            }
        }
        stage('Allure') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'tests/target/allure-results']]
            }
        }
    }
}
