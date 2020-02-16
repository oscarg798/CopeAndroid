pipeline {
  agent any
  stages {
    stage('build image') {
      steps {
        sh 'docker image build  -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .'
      }
    }

    stage('prepare environment'){
      steps {
        sh 'cd /tmp'
        sh 'rm -rf local.properties'
        sh 'touch local.properties && echo -e "sdk.dir=/opt/android" >> local.properties'
      }
    }

    stage('run unit tests') {
      steps {
        sh 'docker run -ti -v ${PWD}:/tmp android /bin/bash `./gradlew test --stacktrace`'
      }
    }
  }
}