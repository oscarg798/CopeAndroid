FROM openjdk:8

ARG GRADLE_VERSION=5.4.1
ARG ANDROID_API_LEVEL=28
ARG ANDROID_BUILD_TOOLS_LEVEL=28.0.3
#ARG EMULATOR_NAME='test'  

COPY entrypoint.sh /usr/local/bin/
ADD . /App/project/

WORKDIR /App

ENV GRADLE_HOME=/opt/gradle/gradle-$GRADLE_VERSION
ENV ANDROID_HOME=/opt/android
ENV ANDROID_SDK_ROOT=/opt/android
ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/tools/bin:${ANDROID_HOME}/platform-tools

RUN apt update && \ 
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    mkdir /opt/gradlew && \
    /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle wrapper --gradle-version ${GRADLE_VERSION} --distribution-type all -p /opt/gradlew && \
    /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle wrapper -p /opt/gradlew && \
    wget 'https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip' -P /tmp  && \
    unzip -d /opt/android /tmp/sdk-tools-linux-4333796.zip && \
    yes Y | /opt/android/tools/bin/sdkmanager --install "platform-tools" "system-images;android-${ANDROID_API_LEVEL};google_apis;x86" "platforms;android-${ANDROID_API_LEVEL}" "build-tools;${ANDROID_BUILD_TOOLS_LEVEL}" && \
    yes Y | /opt/android/tools/bin/sdkmanager --licenses 

RUN ln -s /usr/local/bin/entrypoint.sh /

ENTRYPOINT ["entrypoint.sh"]