env = "prod"
region = "us-east-2"

lambda_memory = 1024

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

acm_domain_name_arn = ""
domain_name = ""

env_vars = {
  "ENV"="prod"
  "FF_CREATE_USERS"="false"
  "SECRET_NAME"="prod-encurtador-links-saas-jwt-secret"
  "SPRING_PROFILES_ACTIVE"="prod"
  "JAVA_TOOL_OPTIONS"="-Dnetworkaddress.cache.ttl=5 -Dnetworkaddress.cache.negative.ttl=0 -Xshare:on -XX:+TieredCompilation -XX:TieredStopAtLevel=1"
}