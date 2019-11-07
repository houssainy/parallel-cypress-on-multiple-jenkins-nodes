pipeline {
    agent none
    tools {nodejs "nodejs"}

    stages {
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
              sh 'sleep 10'
              sh "yarn test-parallel"
            }
          }
        }
      }
    }
}

