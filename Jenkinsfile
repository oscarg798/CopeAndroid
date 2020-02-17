pipeline {
  agent any
  stages {
    stage('build image') {
      steps {
        sh 'docker build -t android .'
      }
    }
    stage('run unit tests') {
      steps {
        sh 'docker run -v ${PWD}:/tmp android'
      }
    }
  }
}