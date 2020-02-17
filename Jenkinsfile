pipeline {
  agent any
  stages {
    stage('build image') {
      steps {
        sh 'docker image build  -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .'
      }
    }

    stage('run unit tests') {
      steps {
        sh 'cd /App'
        sh 'docker run android /bin/bash `./gradlew test --stacktrace`'
      }
    }
  }
}