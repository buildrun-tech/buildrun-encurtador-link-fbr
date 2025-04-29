docker compose up -d

# await 15s

# Create Table
aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "sa-east-1" \
  --table-name "tb_users" \
  --attribute-definitions \
    "AttributeName=user_id,AttributeType=S" \
  --key-schema \
    "AttributeName=user_id,KeyType=HASH" \
  --provisioned-throughput \
      "ReadCapacityUnits=5,WriteCapacityUnits=5"