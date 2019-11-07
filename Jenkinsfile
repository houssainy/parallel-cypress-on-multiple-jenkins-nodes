pipeline {
    agent none
    stages {
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            agent {
              label "master"
            }
            steps {
              sh 'ifconfig'
              sh 'npm i yarn -g'
              sh 'rm -rf node_modules/'
              sh 'yarn install'
              sh 'yarn start &'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            agent {
              docker {
                image 'node:8.16.2-alpine'
              }
              label "Asus_node"
            }
            steps {
              sh 'ifconfig'
              sh 'npm i yarn -g'
              sh 'rm -rf node_modules/'
              sh 'yarn install'
              sh 'yarn start &'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

