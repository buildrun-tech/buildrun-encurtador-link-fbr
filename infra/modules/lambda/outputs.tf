output "arn"        {
  value = aws_lambda_function.this.arn
}

output "invoke_arn" {
  value = aws_lambda_function.this.invoke_arn
}

output "name"       {
  value = aws_lambda_function.this.function_name
}

output "aws_lambda_alias_snapstart" {
  value = aws_lambda_alias.snapstart
}