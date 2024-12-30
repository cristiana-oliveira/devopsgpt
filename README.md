# Summary
This pipeline automates the entire process of building, testing, and deploying a Java application, managing secrets securely, 
controlling traffic with Istio, and supporting multiple environments like dev and prod. It ensures that the application 
is correctly built and deployed in a Minikube Kubernetes environment with the proper configuration for each stage of deployment.

# Evaluating DevOps GPT

To evaluate DevOps GPT, I used a series of prompts covering various DevOps practices, including CI/CD, containerization, Kubernetes orchestration, and advanced traffic management with Istio. These prompts are designed to assess its ability to generate functional, production-ready scripts, manage Kubernetes environments, and securely handle sensitive data, such as credentials, using Kubernetes Secrets.

**The goal of this evaluation is to assess DevOps GPT’s:**

- Technical Accuracy: How accurately DevOps GPT generates correct, production-grade code snippets.
- Completeness: The ability to provide detailed and complete responses that cover all necessary steps in the context of DevOps operations.
- Security Awareness: The attention to security best practices, particularly when dealing with sensitive information like database credentials or access tokens.
- Modularity and Scalability: The ability to build reusable, modular pipelines that support multi-environment configurations (e.g., development, staging, production).
- Automation and Orchestration: How well the generated solutions integrate automated builds, tests, and deployments with Kubernetes orchestration and Istio traffic management.

**The expectations are:**

DevOps GPT should deliver a robust, modular, and scalable pipeline that works seamlessly in local development (Minikube) and production Kubernetes environments.
These criteria will serve as the basis for evaluating DevOps GPT’s performance, accuracy, and applicability in real-world DevOps workflows. The goal of this survey is to gather expert feedback from experienced engineers on how effectively DevOps GPT has implemented a pipeline in accordance with industry best practices and standards.Running the application is optional. Experts may review the code without the need to execute it.

Below is a summary of all the prompts I have used in the evaluation process:

**Summary of Prompts**

- CI/CD Pipeline Setup with Java, Docker, and Kubernetes: Requested a GitHub Actions CI/CD pipeline that builds a Java application using Maven, runs unit tests, builds a Docker image, pushes it to a Docker registry, and deploys it to a Kubernetes cluster.
- Using Helm for Kubernetes Deployment: Asked for a pipeline revision to deploy the Java application using Helm to manage Kubernetes resources, and requested the generated Kubernetes manifests to be saved as separate YAML files.
- Deploying to Minikube: Modified the pipeline to deploy to a Minikube environment instead of a full Kubernetes cluster.
- Installing Minikube in the Pipeline: Requested Minikube installation and startup steps to be integrated directly into the pipeline.
- Using medyagh/setup-minikube@latest for Minikube Setup: Refined the pipeline by asking DevOps GPT to use the medyagh/setup-minikube@latest action for installing and configuring Minikube.
- Switching to Java 17: Updated the pipeline to use Java 17 (LTS) for the application build.
- Adding Istio for Traffic Management and Monitoring: Extended the pipeline to install Istio and deploy the necessary resources for traffic management, such as Istio Ingress Gateway, VirtualService, and DestinationRule.
- Separating Istio Resources into Files: Asked for the separation of Istio resources (Gateway, VirtualService, and DestinationRule) into individual YAML files to enhance maintainability and version control.
- Adding Multi-Environment Support: Requested the pipeline to support multiple environments (dev and prod) with environment-specific Helm values, secrets, and Istio configurations.
- Limiting Destination Rule by IP Range: Asked for the Istio Authorization Policy to restrict access to the application by specific IP ranges and explained how this integrates into the Istio traffic management system.
- Adding Kubernetes Secrets Management: Integrated Kubernetes Secrets management to securely handle sensitive credentials (like database credentials) within the pipeline, and explained how to dynamically create Kubernetes secrets using GitHub Secrets.
    
# Requirements to run the java application locally

**For building and running the java application you need:**

* JDK 17
* Maven 3

**Running the java application locally**

mvn spring-boot:run

# Testing the pipeline on Your Local Minikube Environment

**For testing the pipeline you will need:**

* Install Minikube : https://minikube.sigs.k8s.io/docs/start/?arch=%2Fwindows%2Fx86-64%2Fstable%2F.exe+download
* Install Docker : https://docs.docker.com/engine/install/
* Install Helm : https://helm.sh/docs/intro/install/
* Install Kubectl : https://kubernetes.io/docs/tasks/tools/

**Installing the application in Minikube**
1. Start minikube using docker driver
   ```
   minikube start --driver=docker
   ```
3. To ensure that Docker builds are available in Minikube’s Docker environment, configure Docker to use Minikube’s Docker daemon:
   ```
   eval $(minikube docker-env)
   ```
4. Clone the repository
   ```
   git clone https://github.com/cristiana-oliveira/devopsgpt.git
   cd devopsgpt
   ```
6. You’ll build your Docker image locally and load it into the Minikube environment:
   ```
   docker build -t devopsgpt:local .
   ```
8. Create kubernetes Secrets
   ```
   kubectl create secret generic devopsgpt-secret \
    --from-literal=DB_USERNAME=${{ secrets.DB_USERNAME }} \
    --from-literal=DB_PASSWORD=${{ secrets.DB_PASSWORD }} \
    -n default --dry-run=client -o yaml | kubectl apply -f -
   ```
9. Deploy to minikube using helm
   ```
   # Deploy to Minikube with the 'dev' environment configuration
   helm upgrade --install devopsgpt ./helm/devopsgpt --values ./helm/devopsgpt/values-dev.yaml \
    --set image.repository=devopsgpt --set image.tag=local
   ```
10. Install Istio
   ```
   helm repo add istio https://istio-release.storage.googleapis.com/charts
   helm repo update
   helm install istio-base istio/base -n istio-system --create-namespace
   helm install istiod istio/istiod -n istio-system
   helm install istio-ingress istio/gateway -n istio-system
   kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.23/samples/addons/prometheus.yaml
   kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.23/samples/addons/kiali.yaml
   ```
11. Label default namespace for sidecar-injection
    ```
    kubectl label namespace default istio-injection=enabled
    ```
12. Apply Istio resources
    ```
    kubectl apply -f istio/gateway.yaml
    kubectl apply -f istio/virtualservice.yaml
    kubectl apply -f istio/destinationrule.yaml
    kubectl apply -f istio/authorizationpolicy.yaml
    ```
13. Verify the deployment
    ```
    kubectl rollout status deployment/devopsgpt
    ```
14. Access the application
    ```
    minikube service list
    ```
This command will give you the URL to test the applicaton. Then you add /app/home. e.g.  http://192.168.59.103:32484/app/home
You can also vizualize the deployment using 
   ```
   minikube dashboard
   ```
Or you can access Kiali monitoring toll :
   ```
   kubectl port-forward svc/kiali 20001:20001 -n istio-system
   ```
Then use localhost:20001 to access the Dashboard


