pipeline {
  node {
      //Utilizing a try block so as to make the code cleaner and send slack notification in case of any error
      try {
          //Call function to send a message to Slack
          // Global variable declaration

          // Stage, is to tell the Jenkins that this is the new process/step that needs to be executed
          stage('Checkout') {
              // Pull the code from the repo
              checkout scm
          }

          stage('Build Image') {
              // Build our docker Image
              sh("docker image build  -t android --build-arg GRADLE_VERSION=5.4.1 --build-arg ANDROID_API_LEVEL=28 --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 .")
          }

          stage('Run application test') {
              sh("docker run -ti -v ${PWD}:/tmp --name bbb android /bin/bash '`./gradlew test --stacktrace`''")
              sh("docker rm bbb")
          }
      } catch (e) {
          echo e 
          throw e
        } finally {
          echo "Acabe"
      }
  }
}