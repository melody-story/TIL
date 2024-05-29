
# 제한 시간 2초
# 0<=N<=23 시간 복잡도 하루는 몇초? -> 60*60*24=>86400초 약 9만개 이므로 O(N)으로 풀 수 있음.

#시, 분, 초

N =  int(input())
cnt = 0
for hour in range(N+1):
    for min in range(60):
        for sec in range(60):
            if (str(3) in str(hour) or str(3) in str(min) or str(3) in str(sec)):
                cnt+=1
print(cnt)
                
    
 

