pipeline {
    agent any
    stages {
      stage('Install yarn') {
        steps {
            sh 'npm i yarn -g'
        }
      }
      stage('Install packages') {
        steps {
          sh 'rm -rf node_modules/'
          sh 'yarn install'
        }
      }
      stage('run server') {
        steps {
          sh 'yarn start &'
        }
      }
      stage('Parallel stages') {
        parallel {
          stage('Run A') {
            agent {
              label "master"
            }
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
          stage('Run B') {
            agent {
              label "Asus_node"
            }
            steps {
              sh 'ifconfig'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

