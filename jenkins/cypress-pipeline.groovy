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
    tests["stage ${i}"] = {
      node("cypress1") {
        String name = "stage ${i}"
        stage(name) {
          sh 'ifconfig'
          echo "${name}"
        }
      }
    }
  }
  parallel tests
}
