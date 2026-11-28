pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:/usr/local/src/maven/bin:${env.PATH}"
        IMAGE = 'gestion-livres'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE}:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                                  usernameVariable: 'DH_USER',
                                                  passwordVariable: 'DH_PASS')]) {
                    sh '''
                        echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
                        docker tag ${IMAGE}:${BUILD_NUMBER} $DH_USER/${IMAGE}:${BUILD_NUMBER}
                        docker tag ${IMAGE}:${BUILD_NUMBER} $DH_USER/${IMAGE}:latest
                        docker push $DH_USER/${IMAGE}:${BUILD_NUMBER}
                        docker push $DH_USER/${IMAGE}:latest
                    '''
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
        }
    }
}
