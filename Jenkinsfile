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
           checkoutGit('https://github.com/nagarjunacse02/Youtube-app.git', 'master') 
        }
    }
    stage ('Build Artifacts') {
      steps { 
        buildCode()
      }
    }
    stage ('Build docker image') {
            steps {
                script {
                    buildImage()
                }
            }
        }
    stage('Trivy Scan') {
            steps {
                imageVulScan()
            }
		} 
  }
}
        
