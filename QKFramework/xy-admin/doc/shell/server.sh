APP_HOME=$(cd "$(dirname "$0")"; pwd)
theFile="xy.jar"
theSrcFile="${APP_HOME}/${theFile}"

function kill_process(){
    echo "will restart xy server ..."
    for pid in `ps aux | grep java | grep xy | awk  '{print $2}'`
    do
        echo "xy's process id is $pid"
        kill -9 $pid
        sleep 1
    done
}

function deploy(){
    theDate="`date +%m%d%H`"
    cp ${theFile} ${theFile}.${theDate}
    cp ${theSrcFile} /data/webapp/ -rf
    ${theSrcFile} --spring.profiles.active=prod &
}

function pre(){
    if [ -d ${dumpPath} ] ; then
        echo "${dumpPath} is exist ."
    else
        mkdir -p ${dumpPath}
    fi

    if [ -d ${gcFilePath} ] ; then
        echo "${gcFilePath} is exist ."
    else
        mkdir -p ${gcFilePath}
    fi
}

if [ -f ${theSrcFile} ] ; then
    pre;
    kill_process;
    deploy;
else
    echo "${theSrcFile} NOT EXIST ."
fi

