# **Build & Run AWS Stack - URL Shortener**

[//]: # ([![Build Status]&#40;https://img.shields.io/github/actions/workflow/status/buildrun-tech/buildrun-encurtador-link-fbr/ci.yml&#41;]&#40;https://github.com/buildrun-tech/buildrun-encurtador-link-fbr/actions&#41;  )
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

---

## Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Generating RSA Keys](#generating-rsa-keys)
- [Running Locally](#running-locally)
- [Contributing](#contributing)
- [License](#license)

---

## About

This project provides a cloud-native URL shortening service, allowing you to create custom or random slugs, add UTM tracking parameters, set expiration dates, and track clicks in real time. It’s designed as a multi-tenant SaaS on AWS.

---

## Features

- **Custom & Random Slugs**
- **UTM Tag Support** (`source`, `medium`, `campaign`)
- **Expiration**: set link expiry date/time
- **Atomic Click Counter** using DynamoDB
- **JWT-Protected Endpoints**

---

## Tech Stack

- **Java 21** with Spring Boot
- **AWS DynamoDB** (Enhanced Client + Atomic Counters)
- **AWS SAM** for local emulation
- **Docker** for containerized environments
- **JWT** authentication (RSA key pair)
- **Shell** scripts for local setup

---

## Prerequisites

- JDK 21 or newer
- Maven 3.x
- Docker & [AWS SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli.html)
- AWS credentials configured (has permission to access DynamoDB)

---

## Setup

```bash
git clone https://github.com/buildrun-tech/buildrun-encurtador-link-fbr.git
cd buildrun-encurtador-link-fbr/app
mvn clean install
```

## Generating RSA Keys
Generate the RSA key pair under app/src/main/resources:

```bash
cd app/src/main/resources
openssl genrsa -out app.key 2048
openssl rsa -in app.key -pubout -out app.pub
```

Ensure app.key and app.pub are present before starting the application.

## Running Locally

A convenience script is provided to spin up the local environment using Docker and AWS SAM:

### 1. First Step
```bash
cd app/docker/local
./start_local.sh
```

This script will:

- Start a LocalStack with DynamoDB Service
- Wire up the environment so that all AWS calls target the local emulator

### 2. Second Step

```bash
cd app/docker/local
./run_local.sh
```

The script will

- Build SAM Docker images for the SAM Lambda
- Execute SAM Local Start API
- Optionally you can uncomment the "Debug" section to enable Debug Mode
  - For that, you should use the "Remote JVM Debug" inside the IDE, using the configs:
    - Debugger Mode: Attach to remote JVM
    - Transport: Socket
    - Host: localhost
    - Port: 5005
    - Command Line: -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

After running, your http endpoints will be available at http://localhost:3000 (or the port specified in the script).

## Contributing

Please read CONTRIBUTING.md for guidelines on reporting issues and submitting pull requests:

1. Fork the repository
2. Create a feature branch: git checkout -b feature/awesome-feature
3. Commit your changes: git commit -m "Add awesome feature"
4. Push to your branch: git push origin feature/awesome-feature
5. Open a Pull Request

## License
Distributed under the MIT License. See LICENSE for details.


"user_id" -> {AttributeValue@12457} "AttributeValue(S=5d3bff48-4a9a-447f-8bf6-8bab3918e422)"
"link_id" -> {AttributeValue@12458} "AttributeValue(S=fbr3)"