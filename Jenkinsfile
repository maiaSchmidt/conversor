// Declarative //
pipeline {
 
  agent any
 
  stages {
 
    stage('Build') {
 
      steps {
 
        sh 'mvn -f ./RestConversor/pom.xml clean install'
 
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
