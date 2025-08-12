variable "table_name"   {
  type = string
}

variable "billing_mode" {
  type = string
  default = "PAY_PER_REQUEST"
}

variable "hash_key"     {
  type = string
}

variable "range_key"  {
  type = string
  default = null
}

variable "attributes" {
  type = list(object({
    name = string
    type = string
  }))
}

variable "global_secondary_indexes" {
  type = list(object({
    name               = string
    hash_key           = string
    range_key          = string        # set null if no sort key
    projection_type    = string        # "ALL" | "KEYS_ONLY" | "INCLUDE"
    non_key_attributes = list(string)  # used only when projection_type="INCLUDE"
    read_capacity      = number        # only used if billing_mode="PROVISIONED"
    write_capacity     = number        # only used if billing_mode="PROVISIONED"
  }))
  default = []
}