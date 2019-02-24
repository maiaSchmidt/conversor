// Declarative //
pipeline {
 
  agent any
 
  stages {
 
    stage('Build') {
 
      steps {
 
        sh 'mvn -f ./RestConversor/pom.xml clean install'
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
 
      }
 
    }
 
    stage('Test') {
 
      steps {
 
        echo 'Testing..'
 
      }
 
    }
 
    stage('Deploy') {
 
      steps {
 
        echo 'Deploying....'
 
      }
 
    }
 
  }
}
