pipeline {
    agent any
    environment {
        GIT_REPO_NAME = 'hello-world'
        GITHUB_BRANCH = 'master'
        GIT_USER_NAME = 'nagarjunacse02'
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
        stage ('Build docker image') {
            steps {
                script {
                    sh 'docker build -t nagarjunacse02/test-image .'
                    sh 'docker images'
                }
            }
        }
        stage ('Push image to Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u nagarjunacse02 -p ${dockerhubpwd}'
                    sh 'docker push nagarjunacse02/test-image'
                    }
                }
            }
        }
        stage('Update Deployment File in GitHub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'github_token', variable: 'GITHUB_TOKEN')]) {
                        // Determine the image name dynamically based on your versioning strategy
                        NEW_IMAGE_NAME = "nagarjunacse02/test-image:latest"
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
