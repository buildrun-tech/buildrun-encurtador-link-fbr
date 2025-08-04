env = "prod"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

env_vars = {
  "VARIABLE"= "TESTE"
}