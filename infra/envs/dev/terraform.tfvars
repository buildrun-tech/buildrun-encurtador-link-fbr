env = "dev"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

env_vars = {
  "SECRET_NAME"= "dev-encurtador-links-saas-jwt-secret"
}