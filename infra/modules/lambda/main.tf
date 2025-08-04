resource "aws_lambda_function" "this" {
  function_name = var.name
  role          = var.role_arn
  handler       = var.handler
  runtime       = var.runtime
  s3_bucket     = var.s3_bucket
  s3_key        = var.s3_key
  memory_size   = var.memory_size
  timeout       = var.timeout

  publish       = true
  snap_start {
    apply_on = "PublishedVersions"
  }

  environment {
    variables = var.environment
  }
}

resource "aws_lambda_alias" "snapstart" {
  name             = "snapstart"
  function_name    = aws_lambda_function.this.function_name
  function_version = aws_lambda_function.this.version
}