pipeline {

    agent { any { image 'maven:3.6.3' } }
    stages {
        stage('test') {
            sh 'mvn test'
        }
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
