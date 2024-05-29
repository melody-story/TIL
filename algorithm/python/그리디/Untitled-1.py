
N = int(input())
keyArray = input().split()
x,y = 1,1
dx=[0,-1,0,1] # 우R, 상U, 좌L, 하D
dy=[1,0,-1,0]
move_types = ['R','U','L','D']
for key in keyArray:
    for i in range(len(move_types)):
        if key == move_types[i] :
            xx = x+dx[i]
            yy = y+dy[i]
            if 0 < xx and xx <= N and 0< yy and yy <= N:
                x = xx
                y = yy
print(x, y)