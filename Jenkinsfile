pipeline {

    agent { any { image 'maven:3.6.3' } }
    stages {
        stage('test') {
            steps {
                sh 'mvn test'
            }

        }
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
