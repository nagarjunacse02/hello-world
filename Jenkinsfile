pipeline {
    agent any
    environment {
        GIT_REPO_NAME = 'hello-world'
        GITHUB_BRANCH = 'master'
        GIT_USER_NAME = 'nagarjunacse02'
        PATH = "$PATH:/usr/share/maven"
        ACR_USERNAME = 'nagarjunacse02'
        
    }
    stages {
        stage ('Checkout') {
            steps {
                git credentialsId: 'github', url: 'https://github.com/nagarjunacse02/hello-world.git'
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        /*stage ('Server'){
            steps {
               rtServer (
                 id: "Artifactory",
                 url: 'http://20.122.98.107:8082/ui/admin/repositories',
                 username: 'jfrog_un',
                  password: 'jfrog_pwd',
                  bypassProxy: true,
                   timeout: 300
                        )
            }
        }
        stage('Upload'){
            steps{
                rtUpload (
                 serverId:"Artifactory",
                  spec: '''{
                   "files": [
                      {
                      "pattern": "*.war",
                      "target": "slk_poc-libs-release-local"
                      }
                            ]
                           }''',
                        )
            }
        }
        stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "Artifactory"
                )
            }
        }*/
        
        stage('Push artifacts into artifactory') {
            steps {
              rtUpload (
                serverId: 'Artifactory',
                spec: '''{
                      "files": [
                        {
                          "pattern": "*.war",
                           "target": "slk_poc-libs-snapshot-local"
                        }
                    ]
                }'''
              )
            }
        }
        
        /*stage ('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube-10.3') {
                sh 'mvn sonar:sonar'
                }
            }
        }*/
        stage ('Build docker image') {
            steps {
                script {
                    sh 'docker build -t nagarjunacse02/test-image.${BUILD_ID} .'
                    sh 'docker images'
                }
            }
        }
        stage ('Push image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u nagarjunacse02 -p ${dockerhubpwd}'
                    sh 'docker push nagarjunacse02/test-image.${BUILD_ID}'
                    sh "docker tag nagarjunacse02/test-image.${BUILD_ID} nagarjunacse02.azurecr.io/test-image.${BUILD_ID}:latest"
                    }
                }
            }
        }
        
        stage('Push Docker image to Azure Container Registry') {
            steps {
                script {
                    // Azure Container Registry login ${env.ACR_SERVER}
                    withCredentials([usernamePassword(credentialsId: 'your_acr_credentials_id', usernameVariable: 'ACR_USERNAME', passwordVariable: 'ACR_PASSWORD')]) {
                        sh "docker login nagarjunacse02.azurecr.io -u ${ACR_USERNAME} -p ${ACR_PASSWORD}"
                    }
                    sh 'docker images'
                    // Tag the Docker image for Azure Container Registry
                    sh "docker tag nagarjunacse02/test-image.${BUILD_ID} nagarjunacse02.azurecr.io/test-image.${BUILD_ID}:latest"
 
                    // Push the Docker image to Azure Container Registry
                    sh "docker push nagarjunacse02.azurecr.io/test-image.${BUILD_ID}:latest"
                    //sh "az acr repository list --name nagarjunacse02 --resource-group aks_cluster_RG"
                }
            }
        }
        
        
        stage('Update Deployment File in GitHub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'github_token', variable: 'GITHUB_TOKEN')]) {
                        // Determine the image name dynamically based on your versioning strategy
                        NEW_IMAGE_NAME = "nagarjunacse02.azurecr.io/test-image.${BUILD_ID}:latest"
                        // Replace the image name in the deployment.yaml file
                        sh "sed -i 's|image: .*|image: $NEW_IMAGE_NAME|' regapp-deploy.yml"
                        // Commit and push changes
                        sh 'git add regapp-deploy.yml'
                        sh "git commit -m 'Update Docker image in deployment file'"
                        sh "git push https://${GITHUB_TOKEN}@github.com/${GIT_USER_NAME}/${GIT_REPO_NAME} HEAD:master"
                    }
                }
            }
        }
    }
    //Job Status check
    post {
        success {
            echo 'Build successfully!'
        }

        failure {
            echo 'Build Failed'
        }
    }
}
