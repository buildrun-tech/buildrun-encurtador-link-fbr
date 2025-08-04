variable "name_prefix"       {
  type = string
}

variable "lambda_arn"        {
  type = string
}

variable "lambda_invoke_arn" {
  type = string
}

variable "timeout_ms" {
  type = number
  default = 29000
}