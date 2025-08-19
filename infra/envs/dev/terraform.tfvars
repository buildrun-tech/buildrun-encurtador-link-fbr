env = "dev"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

env_vars = {
  "ENV"="dev"
  "SECRET_NAME"="dev-encurtador-links-saas-jwt-secret"
  "SPRING_PROFILES_ACTIVE"="dev"
  "JAVA_TOOL_OPTIONS"="-Dnetworkaddress.cache.ttl=5 -Dnetworkaddress.cache.negative.ttl=0"
}