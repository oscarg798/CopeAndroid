pipeline {

  agent any
  
  stages {
    stage('build image') {
      steps {
        sh '''docker images -q android
          if [ $? -eq 0 ];
          then
            echo "image found"
          else
            docker image build -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .

          fi
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
