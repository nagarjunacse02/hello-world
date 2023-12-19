
@Library('Jenkins_shared_library') _

pipeline {
  agent any
  parameters {
        choice(name: 'action', choices: 'create\ndelete', description: 'Select create or destroy.')
        
        string(name: 'DOCKER_HUB_USERNAME', defaultValue: 'nagarjunacse02', description: 'Docker Hub Username')
        string(name: 'IMAGE_NAME', defaultValue: 'youtube', description: 'Docker Image Name')
    }
    tools{
        jdk 'jdk17'
        nodejs 'node16'
    }
    environment {
        SCANNER_HOME=tool 'sonar-scanner'
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
    when { expression { params.action == 'delete'}}    
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
    when { expression { params.action == 'delete'}}
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
    stage('Trivy iamge'){
    when { expression { params.action == 'create'}}    
        steps{
            trivyImage()
        }
    }
  }
}
