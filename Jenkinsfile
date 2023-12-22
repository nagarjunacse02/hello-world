@Library('Jenkins_shared_library') _
def configUtils = loadConfig()

pipeline {
  agent any
  parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'Select create or destroy.')
        
        string(name: 'DOCKER_HUB_USERNAME', defaultValue: 'nagarjunacse02', description: 'Docker Hub Username')
        string(name: 'IMAGE_NAME', defaultValue: 'youtube1.1', description: 'Docker Image Name')
        string(name: 'JFROG_ARTIFACTORY', description: 'Jfrog Artifactory', defaultValue:'fisdemo1.jfrog.io/demo-fis-docker-docker-local')
    }
    tools{
        jdk 'jdk17'
        nodejs 'node16'
    }
    environment {
        SCANNER_HOME=tool 'sonar-scanner'
        URL_WEBHOOK = credentials("URL_WEBHOOK")
    }
  stages {
    stage ('clean workspace') {
      steps {
        cleanWorkspace()
      }
    }
    stage ('source code checkout') {
        steps { 
           checkoutGit('https://github.com/nagarjunacse02/Youtube-app.git', 'main') 
        }
    }
    stage('sonarqube Analysis'){
    when { expression { params.action == 'create'}}    
        steps{
            sonarqubeAnalysis()
        }
    }
    stage('sonarqube QualitGate'){
    when { expression { params.action == 'delete'}}    
        steps{
            script{
                def credentialsId = 'sonar-token'
                qualityGate(credentialsId)
            }
        }
    }
    stage('Npm install'){
    when { expression { params.action == 'create'}}    
        steps{
            npmInstall()
        }
    }
    stage('Trivy FS Scan') { 
    when { expression { params.action == 'create'}}
        steps { 
            trivyFileScan()
        }
    }
    stage('Docker Build'){
    when { expression { params.action == 'create'}}    
        steps{
            script{
                def dockerHubUsername = params.DOCKER_HUB_USERNAME
                def imageName = params.IMAGE_NAME
                   
                dockerBuild(dockerHubUsername, imageName)
            }
        }
    }
    
    stage('Push artifacts into artifactory') {
        steps {
            script {
                def jfrogArtifactory = params.JFROG_ARTIFACTORY
                def imageName = params.IMAGE_NAME
                artifactsUpload(jfrogArtifactory, imageName)
            }
         }
    }
    
    stage('Trivy image scan'){
    when { expression { params.action == 'create'}}    
        steps{
            trivyImage()
        }
    }
    
    /*stage ('Notifications') {
        steps {
            steps {
                office365ConnectorSend webhookUrl: '${WEBHOOK_URL}',
                message: 'Code is deployed',
                status: 'Success'            
            }
        }
    }*/
  }
  
  /*options {
        office365ConnectorWebhooks([
            [name: "Office 365", url: "${URL_WEBHOOK}", notifyBackToNormal: true, notifyFailure: true, notifyRepeatedFailure: true, notifySuccess: true, notifyAborted: true]
        ])
    }*/
    post {
        success {
            script {
                def config = configUtils.loadNotificationConfigFromYaml()
                def teamAWebhook = config.teamAWebhook
                echo "Success! Team A Webhook URL: ${teamAWebhook}"
            //    sendTeamsNotification(teamAWebhook, "Success Message")
                sendTeamsNotification('${URL_WEBHOOK}', "Build Successful", "00FF00")
                
                }
            }

        failure {
            script {
    
                def config = configUtils.loadNotificationConfigFromYaml()
                def teamAWebhook = config.teamAWebhook
                echo "Failure! Team A Webhook URL: ${teamAWebhook}"
                sendTeamsNotification('${URL_WEBHOOK}', "Build Failed", "FF0000")
            }
        } 
    }        

  }    
def sendTeamsNotification(webhookUrl, message, color) {
    def currentBuildUrl = "${env.BUILD_URL}"
    def buildNumber = "${env.BUILD_NUMBER}"
    def jobName = "${env.JOB_NAME}"
    def payload = """
    {
        "themeColor": "FF0000",
        "title": "${message}",
        "text": "Build Number: ${buildNumber}\nJob Name: ${jobName}\nBuild Details: ${currentBuildUrl}"
    }
    """
    sh "curl -X POST -H 'Content-Type: application/json' -d '${payload}' ${URL_WEBHOOK}"
}
