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
              label "Asus_node"
            }
            steps {
              sh 'echo $PATH'
              sh 'export PATH=$PATH:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin'
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

