env = "prod"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

env_vars = {
  "ENV"="prod"
  "AWS_REGION"="us-east-2"
  "SECRET_NAME"="prod-encurtador-links-saas-jwt-secret"
  "SPRING_PROFILES_ACTIVE"="prod"
  "JAVA_TOOL_OPTIONS"="-Dnetworkaddress.cache.ttl=5 -Dnetworkaddress.cache.negative.ttl=0"
}