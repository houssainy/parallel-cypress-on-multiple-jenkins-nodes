def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3

def prepareOneBuildStages(String name) {
 return {
   stage("Build ${name}") {
     sh 'ifconfig'
     sh 'date'
   }
 }
}

def prepareBuildStages() {
  def buildStages = [:]
  for (int i = 0; i < numberOfInstances; i++) {
    def name = "Agent - ${i}"
    buildStages.put(name, prepareOneBuildStages(name))
  }

  return buildStages
}

pipeline {
  agent none

  stages {
    stage("Parallel stages") {
      parallel prepareBuildStages()
    }
  }
}

