pipeline {
  agent any
  stages {
    stage('build image') {
      steps {
        sh '''[ ! -z $(docker images -q android)] || docker image build -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .

'''
      }
    }

    stage('run unit tests') {
      steps {
        sh 'docker run android'
      }
    }

  }
  post {
    always {
      sh 'docker rm android'
      deleteDir()
    }

  }
}