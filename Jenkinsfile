pipeline {
    agent any 
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
