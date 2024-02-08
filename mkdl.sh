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
git rebase main

if [ ! -d "./daily_log/"$DIR ]; then
    mkdir "./daily_log/$DIR"
    echo "made: '"$DIR"' directory"
fi

if [ ! -f "./daily_log/"$DIR"/"$TITLE ]; then
    touch "./daily_log/$DIR/$TITLE"
    echo "created: '"$TITLE"' file"
    echo "Today I Learned "$LOG_NAME""  | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "---" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "### 알고리즘 문제풀이" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "- " | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "### 자바의신 II" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "- " | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "### Q & A" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "- " | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "### 더 알면 좋은 내용" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "- " | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'
    echo "" | tee -a  ./daily_log/$DIR/$TITLE > '/dev/null'

fi
#
git add "./daily_log/$DIR/$TITLE"
git commit -m "daily_log_$LOG_NAME"
