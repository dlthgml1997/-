# https://programmers.co.kr/learn/courses/30/lessons/43165#

## 1번째 풀이 (애스터리스크(*), product)
from itertools import product

def solution(numbers, target):
    case = [[i, -i] for i in numbers]
    result = list(map(sum, product(case)))
    print(*case)
    return result.count(target)

## 2번째 풀이 (재귀)
def dfs(numbers, target, size, total):
    global answer
    if size == len(numbers):
        if total == target:
            answer += 1
        return


    dfs(numbers, target, size+1, total+numbers[size])
    dfs(numbers, target, size+1, total-numbers[size])

def solution(numbers, target):
    global answer
    answer =0
    dfs(numbers,target,1, numbers[0])
    dfs(numbers,target,1, -1 * numbers[0])
    return answer