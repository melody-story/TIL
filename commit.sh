git remote update


#git rebase main

git add .
git commit -m "$1"

git checkout main

git merge feature/dailyLog --no-ff squash
