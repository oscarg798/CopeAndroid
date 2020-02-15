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
        sh './gradlew compileDebugKotlin '
      }
    }

    stage('Tests') {
      steps {
        sh './gradlew testDebugUnitTest --stacktrace'
      }
    }

  }
}