variable "name_prefix" {
  type = string
}

variable "dynamodb_arn" {
  type = list(string)
}

variable "secret_arn" {
  type = string
}