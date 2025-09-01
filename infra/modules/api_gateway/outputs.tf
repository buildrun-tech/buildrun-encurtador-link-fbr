output "invoke_url" {
  value = aws_apigatewayv2_stage.default.invoke_url
}

output "cname_apigtw" {
  value       = aws_apigatewayv2_domain_name.this.domain_name_configuration[0].target_domain_name
  description = "Aponte o CNAME do seu provedor para este valor"
}