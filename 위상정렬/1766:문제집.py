import sys
input = sys.stdin.readline
import heapq

n, m = map(int, input().split())
tree = [[] for _ in range(n + 1)]
inDegree = [0 for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    tree[a].append(b)
    inDegree[b] += 1

q = []
for i in range(1, n + 1):
    if inDegree[i] == 0:
        heapq.heappush(q, i)

result = []
while q:
    a = heapq.heappop(q)
    result.append(a)
    for i in tree[a]:
        inDegree[i] -= 1
        if inDegree[i] == 0:
            heapq.heappush(q, i)

print(*result)

# 시간초
# import sys
# input = sys.stdin.readline과

# n, m = map(int,input().split())
# pro_couple = []


# for _ in range(m):
#     a, b = input().split()
#     pro_couple.append([int(a),int(b)])

# answer = []
# for i in range(1,n+1):
#     for pro in pro_couple:
#         if i == pro[1]:
#             print(pro[0], end=' ')
#             answer.append(pro[0])
#             break
#     if i not in answer:
#         print(i, end = ' ')