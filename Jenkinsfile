pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Upload') {
            steps {
                sh 'aws s3 cp build/libs/application.jar s3://jenkins-deploy-application2/application.jar --region us-east-1'
            }
        }

        stage('Deploy') {
            steps {
                sh 'aws elasticbeanstalk create-application-version --region us-east-1 --application-name WhatapProject --version-label ${BUILD_TAG} --source-bundle S3Bucket="jenkins-deploy-application2”,S3Key="application.jar"'
                sh 'aws elasticbeanstalk update-environment --region us-east-1 --environment-name Whatapproject-env --version-label ${BUILD_TAG}'
            }
        }
    }
}

