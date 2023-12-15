@Library('Jenkins_shared_library') _

pipeline {
  agent any
  stages {
    stage ('clean workspace') {
      steps {
        cleanWorkspace()
      }
    }
    stage ('source code checkout') {
        steps { 
           checkoutGit('https://github.com/nagarjunacse02/hello-world.git', 'master') 
        }
    }
    stage ('Build') {
      steps { 
        buildCode()
      }
    }
  }
}
        
