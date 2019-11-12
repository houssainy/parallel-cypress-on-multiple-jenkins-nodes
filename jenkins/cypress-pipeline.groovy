def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3


node {
  stage('Build') {
    sh 'ifconfig'
    doDynamicParallelSteps()
  }
}

def doDynamicParallelSteps(){
  def tests = [:]
  for (int i = 0; i < numberOfInstances; i++) {
    tests["${f}"] = {
      node {
        stage("${f}") {
          sh 'ifconfig'
          echo '${f}'
        }
      }
    }
  }
  parallel tests
}
