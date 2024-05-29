N, M = map(int, input().split())
graph = []
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(N):
    graph.append(list(map(int, input())))


def dfs(graph, x, y):
    if x < 0 or x >= N or y < 0 or y >= M:
        return 0
    if graph[x][y] == 0:
        graph[x][y] = 1
        for i in range(4):
            dfs(graph, x + dx[i], y + dy[i])
        return 1
    return 0


icecream_cnt = 0
for i in range(N):
    for j in range(M):
        if graph[i][j] == 0:
            icecream_cnt += dfs(graph, i, j)
print(icecream_cnt)
