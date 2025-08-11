env = "dev"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

env_vars = {
  "AWS_REGION"= "us-east-2"
  "SECRET_NAME"= "dev-encurtador-links-saas-jwt-secret"
}