LOG_NAME=$(TZ=Asia/Seoul date +'%Y%m%d')
FILE_EXT=".md"
TITLE=$LOG_NAME$FILE_EXT
CURRENT_MONTH=$(TZ=Asia/Seoul date +'%Y%m')
DIR="Y${CURRENT_MONTH}"
echo $DIR

#!/bin/bash
CURRENT_PATH=$(dirname $(realpath $0))
echo $CURRENT_PATH

#git remote update
git checkout feature/dailyLog

if [ ! -d "./daily_log/"$DIR ]; then
    mkdir "./daily_log/$DIR"
    echo "made: '"$DIR"' directory"
fi

if [ ! -f "./daily_log/"$DIR"/"$TITLE ]; then
    touch "./daily_log/$DIR/$TITLE"
    echo "created: '"$TITLE"' file"
fi
#
git add "./daily_log/$DIR/$TITLE"
git commit -m "daily_log_$LOG_NAME"
