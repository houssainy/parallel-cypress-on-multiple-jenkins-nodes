String cypressLabel = "cypress"

int numberOfInstances = 3

def installDependencies = """
meteor yarn install || error=true
#Fail the build if there was an error
if [ \$error ]
then
  rm -f yarn.lock
  meteor yarn install --skip-integrity-check
fi
"""

pipeline {
  agent none

  stages {
    stage("Build") {
      steps {
        script {
          def buildStages = [:]
          for (int i = 1; i <= numberOfInstances; i++) {
            int currentIndex = i

            buildStages["Agent - ${currentIndex}"] = {
              node("${cypressLabel}${currentIndex}") {
                stage("Agent - ${currentIndex}") {
                  sh 'ifconfig'
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

