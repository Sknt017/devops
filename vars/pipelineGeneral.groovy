@Library('lb_buildartefacto')
@Library('lb_analisissonarqube')
def pipelineGeneral(){
    pipeline {
    agent any
    stages{
        stage('Construir app'){
            steps{
                lb_buildartefacto.clone()
                lb_buildartefacto.install()
            }
        }
        stage('Analisis Sonarqube'){
            steps{
                lb_analisissonarqube.testCoverage()
                lb_analisissonarqube.analisisSonar() 
            }
        }
    }
}
}