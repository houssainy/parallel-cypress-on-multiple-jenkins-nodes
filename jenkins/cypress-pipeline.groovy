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
              sh 'rm -r node_modules/'
              sh 'yarn install'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            agent any
            steps {
              sh 'ifconfig'
              sh 'rm -r node_modules/'
              sh 'yarn install'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

