@Library('lb_buildartefacto')_
@Library('lb_analisissonarqube')_
def pipelineGeneral() {
    pipeline {
        agent any
        stages {
            stage('Preparar') {
                steps {
                    script {
                        echo "Preparando el entorno..."
                        //body()
                    }
                }
            }
            stage('Construir') {
                steps {
                    script {
                        echo "Construyendo el proyecto..."
                    }
                }
            }
            stage('Pruebas') {
                steps {
                    script {
                        echo "Ejecutando pruebas..."
                    }
                }
            }
            stage('Desplegar') {
                steps {
                    script {
                        echo "Desplegando la aplicación..."
                    }
                }
            }
        }
    }
}
