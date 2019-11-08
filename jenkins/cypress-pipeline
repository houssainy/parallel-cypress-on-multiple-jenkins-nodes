pipeline {
    agent none
    tools {nodejs "nodejs"}

    stages {
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            agent any
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            agent any
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

