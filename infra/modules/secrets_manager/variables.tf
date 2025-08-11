variable "secret_name" {
  type        = string
  description = "Name of the secret"
}

variable "description" {
  type        = string
  default     = ""
  description = "Description of the secret"
}

variable "tags" {
  type        = map(string)
  default     = {}
  description = "Tags for the secret"
}