output "table_arn" {
  value = aws_dynamodb_table.this.arn
}

output "gsi" {
  value = aws_dynamodb_table.this.global_secondary_index
}