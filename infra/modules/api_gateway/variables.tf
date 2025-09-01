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

variable "domain_name" {
  type = string
}

variable "acm_domain_name_arn" {
  type = string
}