env = "dev"
region = "us-east-2"

app_prefix = "encurtador-links-saas"

lambda_handler = "tech.buildrun.StreamLambdaHandler::handleRequest"

acm_domain_name_arn = "arn:aws:acm:us-east-2:311141562939:certificate/99fbcd92-6e3a-40a5-94bd-904e2ec0169f"
domain_name = "fbr.buildrun.link"

env_vars = {
  "ENV"="dev"
  "FF_CREATE_USERS"="false"
  "SECRET_NAME"="dev-encurtador-links-saas-jwt-secret"
  "SPRING_PROFILES_ACTIVE"="dev"
  "JAVA_TOOL_OPTIONS"="-Dnetworkaddress.cache.ttl=5 -Dnetworkaddress.cache.negative.ttl=0"
}