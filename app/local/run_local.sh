##  INSTRUCTIONS ##
#export JAVA_HOME=<point to your jdk folder>
#export M2_HOME=<point to your maven folder>

echo "Detecting environment variables..."
echo "Java Home: $JAVA_HOME"
echo "Maven Home: $M2_HOME"

echo "Updating path..."
export PATH=$JAVA_HOME/bin:$PATH
export PATH=$M2_HOME/bin:$PATH

echo "Building..."
sam build

echo "Running..."
sam local start-api \
  --docker-network sam-local-net \
  --warm-containers EAGER

# UNCOMMENT BELOW FOR DEBUG MODE
#echo "Running in DEBUG MODE..."
#sam local start-api \
#  --port 3000 \
#  --docker-network sam-local-net \
#  --debug-port 5005 \
#  --debug-args "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005"
#  --warm-containers EAGER
