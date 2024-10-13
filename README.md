# Summary
This pipeline automates the entire process of building, testing, and deploying a Java application, managing secrets securely, 
controlling traffic with Istio, and supporting multiple environments like dev and prod. It ensures that the application 
is correctly built and deployed in a Minikube Kubernetes environment with the proper configuration for each stage of deployment.

# Evaluating DevOps GPT

As part of the evaluation process for DevOps GPT, I will utilize a series of prompts that explore a wide range of DevOps practices, including continuous integration/continuous deployment (CI/CD), containerization, Kubernetes orchestration, and advanced traffic management using Istio. These prompts were designed to test DevOps GPT's capabilities in generating functional, production-ready scripts, managing Kubernetes environments, and securely handling sensitive information such as credentials via Kubernetes Secrets.

**The goal of this evaluation is to assess DevOps GPT’s:**

- Technical Accuracy: How accurately DevOps GPT generates correct, production-grade code snippets.
- Completeness: The ability to provide detailed and complete responses that cover all necessary steps in the context of DevOps operations.
- Security Awareness: The attention to security best practices, particularly when dealing with sensitive information like database credentials or access tokens.
- Modularity and Scalability: The ability to build reusable, modular pipelines that support multi-environment configurations (e.g., development, staging, production).
- Automation and Orchestration: How well the generated solutions integrate automated builds, tests, and deployments with Kubernetes orchestration and Istio traffic management.

Below is a summary of all the prompts I have used in the evaluation process:

**Summary of Prompts**

- CI/CD Pipeline Setup with Java, Docker, and Kubernetes: Requested a GitHub Actions CI/CD pipeline that builds a Java application using Maven, runs unit tests, builds a Docker image, pushes it to a Docker registry, and deploys it to a Kubernetes cluster.
Using Helm for Kubernetes Deployment:
- Asked for a pipeline revision to deploy the Java application using Helm to manage Kubernetes resources, and requested the generated Kubernetes manifests to be saved as separate YAML files.
- Deploying to Minikube: Modified the pipeline to deploy to a Minikube environment instead of a full Kubernetes cluster.
- Installing Minikube in the Pipeline: Requested Minikube installation and startup steps to be integrated directly into the pipeline.
- Using medyagh/setup-minikube@latest for Minikube Setup: Refined the pipeline by asking DevOps GPT to use the medyagh/setup-minikube@latest action for installing and configuring Minikube.
- Switching to Java 17: Updated the pipeline to use Java 17 (LTS) for the application build.
- Adding Istio for Traffic Management and Monitoring: Extended the pipeline to install Istio and deploy the necessary resources for traffic management, such as Istio Ingress Gateway, VirtualService, and DestinationRule.
- Separating Istio Resources into Files: Asked for the separation of Istio resources (Gateway, VirtualService, and DestinationRule) into individual YAML files to enhance maintainability and version control.
- Adding Multi-Environment Support: Requested the pipeline to support multiple environments (dev and prod) with environment-specific Helm values, secrets, and Istio configurations.
- Limiting Destination Rule by IP Range: Asked for the Istio Authorization Policy to restrict access to the application by specific IP ranges and explained how this integrates into the Istio traffic management system.
- Adding Kubernetes Secrets Management: Integrated Kubernetes Secrets management to securely handle sensitive credentials (like database credentials) within the pipeline, and explained how to dynamically create Kubernetes secrets using GitHub Secrets.
    
# Requirements to run the application locally

**For building and running the application you need:**

* JDK 17
* Maven 3

**Running the application locally**

mvn spring-boot:run
