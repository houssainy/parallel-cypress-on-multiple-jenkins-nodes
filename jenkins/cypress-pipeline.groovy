def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      agent any
      steps {
        script {
            def buildStages = [:]
            for (int i = 0; i < numberOfInstances; i++) {
              buildStages["Agent - ${i}"] = {
                stage("kokokokok ${i}") {
                  sh 'ifconfig'
                  sh 'date'
                }
              }
            }

            parallel buildStages
        }
      }
    }
  }
}

