def meteorRunner = "meteor"
def cypressLabel = "cypress"

def numberOfInstances = 3


node {
  stage('Build') {
    sh 'ifconfig'
  }
  stage('Run') {
    doDynamicParallelSteps()
  }
}

def doDynamicParallelSteps(){
  def tests = [:]
  for (int i = 1; i <= 3; i++) {
    tests["${i}"] = {
      node("cypress1") {
        stage("stage ${i}") {
          sh 'ifconfig'
          echo '${i}'
        }
      }
    }
  }
  parallel tests
}
