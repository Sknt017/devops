import org.devops.LbAnalisissonarqube
import org.devops.LbBuildArtefacto

def call() {
    pipeline {
        agent any
        stages {
            stage('SonarQube Analysis') {
                steps {
                    script {
                        org.devops.LbAnalisissonarqube.analizarCodigo(this)
                    }
                }
            }
            stage('Build Artifact') {
                steps {
                    script {
                        org.devops.LbBuildArtefacto.construirArtefacto(this)
                    }
                }
            }
        }
    }
}
