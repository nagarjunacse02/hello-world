def call() {
  sh 'trivy image nagarjunacse02/test-image.${BUILD_ID}  --output report.html || true'
}
