def cypressLabel = "cypress"

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
          def tests = [:]
          for (int i = 1; i <= 3; i++) {
            String x = i

            tests["stage ${x}"] = {
              node("cypress${x}") {
                stage("stage ${x}") {
                  sh 'ifconfig'
                }
              }
            }
          }
          parallel tests
        }
      }
    }
  }
}

