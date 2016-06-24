curlId=$(curl --unix-socket /var/run/system-docker.sock -s -H "Content-Type: application/json" -X POST -d  '{"AttachStdin": false, "AttachStdout": true, "AttachStderr": true, "Tty": true, "Cmd": ["python", "/tmp/Api-Invokers/createCluster.py", "10.10.30.235", "10.10.30.236" ] }' http:/containers/console/exec | jq '.Id' | tr -d '\"')
command="curl --unix-socket /var/run/system-docker.sock -s -H \"Content-Type: application/json\" -X POST -d '{\"Detach\": false, \"Tty\": true }' http:/exec/$curlId/start"
#echo $command
result=$(eval $command)
echo $result

