docker compose up -d

# await 15s
echo "Waiting for 15 seconds..."
sleep 15
echo "Done waiting."

# Create Table
aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "sa-east-1" \
  --table-name "user_entity" \
  --attribute-definitions \
    "AttributeName=user_id,AttributeType=S" \
  --key-schema \
    "AttributeName=user_id,KeyType=HASH" \
  --provisioned-throughput \
      "ReadCapacityUnits=5,WriteCapacityUnits=5"