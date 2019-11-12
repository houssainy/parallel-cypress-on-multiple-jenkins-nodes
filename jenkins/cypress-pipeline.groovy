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

def getBuildStages() {
  def buildStages = [:]
  for (int i = 1; i <= 3; i++) {
    int currentIndex = i

    buildStages["Agent - ${currentIndex}"] = {
      node("${cypressLabel}${currentIndex}") {
        stage("Agent - ${currentIndex}") {
          sh "cd \${WORKSPACE}"
          sh "meteor npm install -g yarn"
          sh "export PATHABLE_NEXT_HOME=\${WORKSPACE}"
          sh "./scripts/db/reimport.sh staging test-data datasets"
          sh "./scripts/db/fix-community-domains.sh staging"
        }
      }
    }
  }

  return buildStages
}

pipeline {
  agent none

  stages {
    stage("Build") {
      steps {
        script {
          parallel getBuildStages()
        }
      }
    }
  }
}

