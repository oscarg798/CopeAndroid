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
        sh './gradlew compileDebugKotlin --stacktrace  '
      }
    }

    stage('Tests') {
      steps {
        sh './gradlew testDebugUnitTest --stacktrace'
      }
    }

  }
}