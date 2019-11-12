def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      agent {label cypressLabel}

      steps {
        script {
            def buildStages = [:]
            for (int i = 0; i < numberOfInstances; i++) {
              buildStages["Agent - ${i}"] = {
                stage("${i}") {
                  steps {
                    sh 'date'
                  }
                }
              }
            }

            parallel buildStages
        }
      }
    }
  }
}

