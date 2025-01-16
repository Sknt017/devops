import org.devops.LbAnalisissonarqube
import org.devops.LbBuildArtefacto

def call() {
    def build = new org.devops.LbBuildArtefacto()
    def sonar = new org.devops.LbAnalisissonarqube()
    pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: "${GIT_URL_1}"
            }
        }
        stage('Build') {
            steps {
                script{
                    build.construirArtefacto(this,'mvn clean')
                    build.construirArtefacto(this,'mvn compile')
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    build.construirArtefacto(this, 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report')
                }
            }
        }
        stage('Package') {
            steps {
                script {
                    build.construirArtefacto(this, 'mvn package')
                }
            }
            post{
                always{
                    junit 'target/surefire-reports/**/*.xml'
                }
                success{
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                }
            }
        }
        stage('Code Analisys') {
            steps {
                script {
                    def scannerHome = tool 'SonarqubeScanner'
                    withSonarQubeEnv('ServerSonarqube') {
                        sh sonar.analizarCodigo(sonarTool: scannerHome)
                    }
                }
            }
        }
    }
}

}
