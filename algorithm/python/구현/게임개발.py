N,M = map(int,input().split())
A,B,d = map(int,input().split())

map_array = [[0]*M for _ in range(N)]
for i in range(N):
    list = input().split()
    for j in range(M):
        map_array[i][j]=int(list[j])

dx = [-1,0,1,0]
dy = [0,1,0,-1]
lx = [0,-1,0,1]
ly = [-1,0,1,0]
rotate = [3,0,1,2]
cnt=1
try_cnt =0
while(True):
    d=rotate[d]
    x = A+lx[d]
    y = B+ly[d]
    if(try_cnt == 4):
        if(map_array[A][B-1]!=1):
            B = B-1
        else:
            break
        try_cnt =0
    if (x<0 or x >=N or y<0 or y >=M or map_array[x][y]!=0):
        try_cnt+=1
        continue
    A=x
    B=y
    map_array[A][B] = 1
    cnt+=1
    try_cnt =0
    
print(cnt)
        
    
