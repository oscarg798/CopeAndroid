pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        sh './gradlew clean'
      }
    }

    stage('Compile') {
      steps {
        sh 'export ANDROID_HOME=/usr/lib/android-sdk && ./gradlew compileDebugKotlin --stacktrace  '
      }
    }

    stage('Tests') {
      steps {
        sh './gradlew testDebugUnitTest --stacktrace'
      }
    }

  }
}