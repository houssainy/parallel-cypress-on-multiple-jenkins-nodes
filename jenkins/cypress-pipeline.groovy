def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3

def buildStages = [:]
for (int i = 0; i < numberOfInstances; i++) {
  buildStages["Agent - ${i}"] = {
    agent {label cypressLabel }

    stage("kokokokok ${i}") {
      sh 'ifconfig'
      sh 'date'
    }
  }
}

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      parallel { buildStages }
    }
  }
}

