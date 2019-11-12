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
  for (int i = 0; i < 3; i++) {
    tests["${i}"] = {
      node {
        stage("${i}") {
          sh 'ifconfig'
          echo '${i}'
        }
      }
    }
  }
  parallel tests
}
