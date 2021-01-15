pipeline {
    agent {
        docker {
          label 'linux'
          image 'maven:3.6.3'
        }
    }
    stages {
        stage('test') {
            steps {
              sh 'mvn test -Dtest=RESTTest'
            }
        }
        stage('build') {
            steps {
              sh 'mvn --version'
            }
        }
    }
}
