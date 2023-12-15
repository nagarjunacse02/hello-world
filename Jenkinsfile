@Library('Jenkins_shared_library') _
def COLOR_MAP = [
    'FAILURE' : 'danger',
    'SUCCESS' : 'good'
]
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
  }
    post {
    always {
        echo 'Slack Notifications'
        slackSend (
            channel: '#jenkins_pipeline_notifications', 
            color: COLOR_MAP[currentBuild.currentResult],
            message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} \n build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
        )
    }
    }
}
        
