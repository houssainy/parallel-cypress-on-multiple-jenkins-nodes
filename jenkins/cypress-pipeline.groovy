def meteorRunner = "meteor"
def cypressLabel = "cypress"

def cypressStages = [:]

for (i = 0; i < 3; i++) {
  cypressStages["Agen - ${i}"] = {
    stage("Agent - ${i}") {
      agent { label cypressLabel }
      steps {
        sh "cd \${WORKSPACE}"
        sh "export PATHABLE_NEXT_HOME=\${WORKSPACE}"
        sh "./scripts/db/reimport.sh staging test-data datasets"
        sh "./scripts/db/fix-community-domains.sh staging"
        sh "${meteorRunner} yarn cypress:run"
      }
    }
  }
}


System.out.println("HELLOOOOOOOOOOOOOOOOOoo")

System.out.println(cypressLabel)


System.out.println(cypressStages)
