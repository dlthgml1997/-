# https://www.acmicpc.net/problem/10026

dx = [0, 0, 1, -1]
dy = [-1, 1, 0, 0]

n = int(input())
room = []
for _ in range(n):
    room.append(list(input().strip()))

def bfs(x, y, color):
    # 적록 색약 아닌 경우
    global visited
    queue = []
    queue.append([x, y])
    while queue:
        qx, qy = queue.pop(0)
        for i in range(4):
            nx = qx + dx[i]
            ny = qy + dy[i]
            if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] == 0:
                if room[nx][ny] == color:
                    visited[nx][ny] = 1
                    queue.append([nx, ny])

def bfs2(x, y, color):
    # 적록 색약인 경우
    global visited2
    queue = []
    queue.append([x,y])
    while queue:
        qx, qy = queue.pop(0)
        for i in range(4):
            nx = qx + dx[i]
            ny = qy + dy[i]
            if 0 <= nx < n and 0 <= ny < n and visited2[nx][ny] == 0:
                if room[nx][ny] == color:
                    visited2[nx][ny] = 1
                    queue.append([nx, ny])
                elif color == 'R' and room[nx][ny] == 'G':
                    visited2[nx][ny] = 1
                    queue.append([nx, ny])
                elif color == 'G' and room[nx][ny] == 'R':
                    visited2[nx][ny] = 1
                    queue.append([nx, ny])



cnt1 = 0
cnt2 = 0
visited = [[0] * n for _ in range(n)]
visited2 = [[0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if visited[i][j] == 0:
            bfs(i, j, room[i][j])
            cnt1 += 1
        if visited2[i][j] == 0:
            bfs2(i, j, room[i][j])
            cnt2 += 1


print(cnt1, cnt2)
