pipeline {
    agent any
    tools {nodejs "nodejs"}

    stages {
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            steps {
              sh 'ifconfig'
              sh 'sleep 10'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

