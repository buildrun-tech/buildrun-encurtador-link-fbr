locals {
  name_prefix = "${var.env}-${var.app_prefix}"
}

module "iam_lambda" {
  source       = "./modules/iam_lambda"
  name_prefix  = local.name_prefix
  dynamodb_arn = [
    module.dynamodb_tb_users.table_arn
  ]
  secret_arn   = module.secret_jwt.secret_arn
}

module "lambda" {
  source      = "./modules/lambda"
  name        = "${local.name_prefix}-lambda"
  role_arn    = module.iam_lambda.role_arn
  handler     = var.lambda_handler
  runtime     = var.lambda_runtime
  s3_bucket   = var.lambda_s3_bucket
  s3_key      = var.lambda_s3_key
  memory_size = var.lambda_memory
  timeout     = var.lambda_timeout
  environment = var.env_vars
}

module "secret_jwt" {
  source = "./modules/secrets_manager"
  secret_name = "${local.name_prefix}-jwt-secret"
  description = "Secret Manager that stores private and public jwt keys"
}

module "api" {
  source            = "./modules/api_gateway"
  name_prefix       = local.name_prefix
  lambda_arn        = module.lambda.aws_lambda_alias_snapstart.arn
  lambda_invoke_arn = module.lambda.aws_lambda_alias_snapstart.invoke_arn
  timeout_ms        = 29000
}

module "dynamodb_tb_users" {
  source = "./modules/dynamodb"

  table_name   = "${var.env}_tb_users"
  hash_key     = "user_id"

  attributes = [
    {
      name = "user_id"
      type = "S"
    },
    {
      name = "email"
      type = "S"
    }
  ]

  global_secondary_indexes = [
    {
      name               = "email-index"
      hash_key           = "email"
      range_key          = null
      projection_type    = "INCLUDE"
      non_key_attributes = ["user_id", "password"]
      read_capacity      = 1
      write_capacity     = 1
    }
  ]
}

module "dynamodb_user_links" {
  source = "./modules/dynamodb"

  table_name   = "${var.env}_tb_user_links"
  hash_key     = "link_id"

  attributes = [
    {
      name = "link_id"
      type = "S"
    },
    {
      name = "user_id"
      type = "S"
    }
  ]

  global_secondary_indexes = [
    {
      name               = "fk-user-index"
      hash_key           = "user_id"
      range_key          = "link_id"
      projection_type    = "ALL"
      non_key_attributes = []   # not used for "ALL"
      read_capacity      = 1
      write_capacity     = 1
    }
  ]
}

module "dynamodb_links_analytics" {
  source = "./modules/dynamodb"

  table_name   = "${var.env}_tb_links_analytics"
  hash_key     = "link_id"
  range_key    = "date"

  attributes = [
    {
      name = "link_id"
      type = "S"
    },
    {
      name = "date"
      type = "S"
    }
  ]
}

