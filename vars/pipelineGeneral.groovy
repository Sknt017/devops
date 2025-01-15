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
                git url: 'https://github.com/Sknt017/termometro.git'
            }
        }
        stage('Build') {
            steps {
                script{
                //sh 'mvn clean'
                build.construirArtefacto(this,'mvn clean')
                //sh 'mvn compile'
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
                    sonar.analizarCodigo(this)
                    // withSonarQubeEnv('ServerSonarqube') {
                    //     sh "${scannerHome}/bin/sonar-scanner \
                    //         -Dsonar.projectKey=analisisTermometro \
                    //         -Dsonar.projectName=analisisTermometro \
                    //         -Dsonar.sources=src/main/java \
                    //         -Dsonar.java.binaries=target/classes \
                    //         -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    // }
                }
            }
        }
    }
}

}
