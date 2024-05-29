# 프로그래머스 미로탈출 https://school.programmers.co.kr/learn/courses/30/lessons/159993?language=python3

# sx,sy부터 ex,ey까지 거리 구하는 함수 만들기

# S위치, E위치, L위치 구하기 O(n)

# 14:49 - 16:41

'''
 주의하기! 
 1. len() 함수는 사용을 주의하자. 전역변수를 사용
 2. 이차 행렬을 만들 때는, 리스트를 복사하는 것이 아님을 주의하자.
    잘못된 예) visited = [[0] * len(graph)] * len(graph)
    올바른 예) visited = [[0] * m for _ in range(n)]
'''
from collections import deque


def solution(graph):
    global n
    global m
    n = len(graph)
    m = len(graph[0])
    for i in range(n):
        if 'S' in graph[i]:
            S_x, S_y = (i, graph[i].index('S'))
        if 'E' in graph[i]:
            E_x, E_y = (i, graph[i].index('E'))
        if 'L' in graph[i]:
            L_x, L_y = (i, graph[i].index('L'))
    a = bfs(graph, S_x, S_y, L_x, L_y)
    b = bfs(graph, L_x, L_y, E_x, E_y)
    if a <= 0 or b <= 0:
        return -1
    return a + b


def bfs(graph, sx, sy, ex, ey):
    # visited = [[0] * len(graph)] * len(graph)
    visited = [[0] * m for _ in range(n)]
    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]
    q = deque([(sx, sy)])
    visited[sx][sy] = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if graph[nx][ny] == 'X' and visited[nx][ny] > 0:
                continue
            if graph[nx][ny] != 'X' and visited[nx][ny] == 0:
                q.append((nx, ny))
                visited[nx][ny] = visited[x][y] + 1
            if nx == ex and ny == ey:
                return visited[ex][ey] - 1
    return -1
