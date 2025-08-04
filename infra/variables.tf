variable "env" {
  type = string
}

variable "region" {
  type = string
}

variable "app_prefix" {
  type = string
}

variable "lambda_s3_bucket" {
  type = string
  description = "DO NOT USE - PIPELINE WILL FILL DYNAMICALLY"
}

variable "lambda_s3_key" {
  type = string
  description = "DO NOT USE - PIPELINE WILL FILL DYNAMICALLY"
}

variable "lambda_memory" {
  type    = number
  default = 512
}

variable "lambda_timeout" {
  type    = number
  default = 30
}

variable "lambda_handler" {
  type    = string
  default = "org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"
}

variable "lambda_runtime" {
  type    = string
  default = "java21"
}

variable "billing_mode" {
  type    = string
  default = "PAY_PER_REQUEST"
}

variable "env_vars" {
  type    = map(string)
  default = {}
}