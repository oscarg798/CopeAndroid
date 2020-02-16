pipeline {
  agent {
        docker { dockerfile true  }
  }
  stages {
    stage('Clean') {
      steps {
        sh 'docker image build  -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .'
        sh 'docker run -ti -v ${PWD}:/tmp android /bin/bash "`./gradlew test --stacktrace`"'
      }
    }

  }
}