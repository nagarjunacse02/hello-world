def call() {
    sh 'trivy image nagarjunacse02/youtube:latest > trivyimage.txt'
}
