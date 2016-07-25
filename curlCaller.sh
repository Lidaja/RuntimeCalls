#p=$1
#params=($p)
#ip=${params[0]}
#vip=${params[1]}
#num=${params[2]}
command="curl --unix-socket /var/run/system-docker.sock -H \"Content-Type: application/json\" -X POST -d '{\"AttachStdin\": false, \"AttachStdout\": true, \"AttachStderr\": true, \"Tty\": false, \"Cmd\": [ \"python\", \"/tmp/Api-Invokers/createCluster.py\", \"10.10.30.235\", \"10.10.30.234\" ] }' http:/containers/console/exec | jq '.Id' | tr -d '\"'"
exec_id=$(eval $command)
command="curl --unix-socket /var/run/system-docker.sock -H \"Content-Type: application/json\" -X POST -d '{\"Detach\": false, \"Tty\": true }' http:/exec/$exec_id/start"
result=$(eval $command)
echo $result
