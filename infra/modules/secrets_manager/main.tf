resource "aws_secretsmanager_secret" "this" {
  name        = var.secret_name
  description = var.description
  tags        = var.tags
}

resource "aws_secretsmanager_secret_version" "this" {
  secret_id     = aws_secretsmanager_secret.this.id
  secret_string = jsonencode({})
}